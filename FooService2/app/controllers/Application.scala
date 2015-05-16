package controllers

import play.api.mvc.{ Action, Controller }
import play.api.mvc.WebSocket
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Reads
import play.api.libs.json.JsValue
import play.api.libs.iteratee.Iteratee
import play.api.libs.functional.syntax._
import play.api.libs.json.JsPath
import models.DAO
import models.MissedCall
import play.api.libs.json.JsError
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import java.sql.Timestamp
import play.api.libs.iteratee.Concurrent
import play.api.data.Form
import play.api.data.Forms._
import models.AppReg
import play.libs.Akka
import akka.actor.Props
import akka.pattern.ask
import scala.concurrent.Promise
import models.CallEventsActor
import models.Join
import models.Call
import akka.util.Timeout
import scala.concurrent.Future
import play.api.libs.json.Json
import models.CallTriggerActor
import models.Send
import models.CallFrom
import play.api.Logger

/**
 * case classes representing the form data
 */
case class AddDeviceFormData(simNumber: String, phoneNumber: String)
case class RemoveDeviceFormData(phoneNumber: String)
case class MissedCallData(simNumber: String, phno: String)

object Application extends Controller with Secured {
  /**
   * creating actors
   */
  val callEventsActor = Akka.system().actorOf(Props[CallEventsActor])
  val callTriggerActor = Akka.system().actorOf(Props[CallTriggerActor])
  
  /**
   * index page
   */
  def index = Action {
    Ok(views.html.index("Welcome to Foo Service"))
  }
  
  /**
   * Android App makes a post request to this action for App or device registration
   */
  def registerApp = Action(parse.json) { request =>
    /**
     * log message
     */
    Logger.info("register request from App")
    
    (request.body \ "simId").asOpt[String].map {simId =>{
      
      println("simId "+simId)
      /**
       * check if this sim if registered
       */
      if(DAO.isSimExists(simId.trim())) {
        
        Logger.info(simId + " sim exists")
        
        /**
         * check if the sim is whitelisted
         */
        if(DAO.isWhitelisted(simId)) {
          
          /**
           * send the status
           */
          Ok(Json.toJson(Map("status" -> 0)))
        }else {
          
          /**
           *white list the sim 
           */
          DAO.whitelist(simId)
          
          /**
           * send the status
           */
          Ok(Json.toJson(Map("status" -> 1)))
        }
        
      }else {
        
        Logger.info("sim: "+simId+" not registered")
        
        /**
         * send the status
         */
        BadRequest(Json.toJson(Map("status" -> -1)))
      }
    } 
   }.getOrElse( BadRequest(Json.toJson(Map("status" -> -1))))
  }
  
  /**
   * Android App does a post request to this action to unregister itself
   * once the app is unregistered entry will be removed from whitelist
   */
  def unregisterApp = Action(parse.json) { request =>
    
    (request.body \ "simId").asOpt[String].map {simId =>{
      /**
       * check if sim is added by the admin
       */
      if(DAO.isSimExists(simId)) {
        /**
         * check is its whitelisted
         */
        if(DAO.isWhitelisted(simId)) {
          /**
           * remove from white list
           */
          DAO.blacklist(simId)
          /**
           * send the status
           */
          Ok(Json.toJson(Map("status" -> 1)))
        }else {
          /**
           * send the status
           */
          Ok(Json.toJson(Map("status" -> 0)))
        }
      }else {
        BadRequest(Json.toJson(Map("status" -> -1)))
      }
    } 
   }.getOrElse( BadRequest(Json.toJson(Map("status" -> -1))))
  }
  
  /**
   * foo websocket talks to the Android app over WebSockets
   * Android App continuosly sends the call information that it receives
   * logs the call information to the database along with the timestamp
   * later this is used to check is the call is done with in 2 minutes of time span
   */
  def foo = WebSocket.async[JsValue] { implicit request =>
    
    /**
     * Implicit reads
     */
    implicit val missedCallDataReads: Reads[MissedCallData] = (
    (JsPath \ "simNumber").read[String] and
    (JsPath \ "phno").read[String])(MissedCallData.apply _)
    
    /**
     * console message
     */
    Logger.info("missed call manager websocket")
    
    /**
     * WebSocket.Async excepts a future
     */
    Future {
      
      /**
       * read json messages using iteratee from the App
       */
      val iteratee = Iteratee.foreach[JsValue] {
      json =>
        {
          /**
           *Send the Call Events actor json message
           * this actor sends messages to Admin console
           */
          callEventsActor ! Call(json)
          
          /**
           * parsing json data
           */
          json.validate[MissedCallData].fold(
            invalid = {
              errors => println("errors")
            },
            valid = {
              data =>
                {
                  /**
                   * console message
                   */
                  Logger.info("got a missed call from "+data.simNumber+" phno "+data.phno)
                  
                  val date = new java.util.Date
                  val t = new Timestamp(date.getTime)
                  
                  /**
                   * check if sim is registered
                   */
                  val sim = DAO.isSimExists(data.simNumber)
                  
                  if(sim){
                    
                    /**
                     * write the missed call data to database
                     */
                    DAO.saveMissedCall(MissedCall(data.simNumber, data.phno, t, None)) 
                    /**
                     * console message
                     */
                    Logger.info("call from user logged "+data.phno)
                  
                  }else {
                    /**
                     * console message 
                     */
                    Logger.info("call from user not logged " + data.phno)
                  }
                  
                  /**
                   * 
                   */
                  callTriggerActor ! CallFrom(data.phno)
                }
            })
        }
    }
    
    /**
     *Empty enumerator as i, am not sending any message back to Android App 
     */
    val enumerator = Enumerator.empty[JsValue]
    
    /**
     * return iteratee and enumerator
     */
    (iteratee, enumerator) 
    }
  }
  
  /**
   * for using scala prefix notations
   */
  import scala.concurrent.duration._
  implicit val timeout = Timeout(1 hours)
  
  /**
   * Subscribe to missed call events
   */
  def events = WebSocket.async[String] { implicit request =>
    val future = (callEventsActor ? Join)
    future.mapTo[(Iteratee[String, _], Enumerator[String])]
  }
  
  /**
   * Add Device form
   */
  val addDeviceForm = Form(
      mapping(
          "simId" -> nonEmptyText,
          "phoneNumber" -> nonEmptyText
      )(AddDeviceFormData.apply)(AddDeviceFormData.unapply) verifying("Sim Id or Phno already exists", data =>
          !DAO.isAppReg(data.simNumber, data.phoneNumber)
      )
  )
  
  /**
   * add device action for displaying the add device from
   */
  def addDevice = withAdmin { admin => implicit request =>
    Ok(views.html.addDevice(addDeviceForm))
  }
  
  /**
   * add device post action
   */
  def addDevicePost = withAdmin { admin => implicit request =>
    addDeviceForm.bindFromRequest().fold(
        formWithErrors => {
          BadRequest(views.html.addDevice(formWithErrors))
        },
        data => {
          val date = new java.util.Date
          val t = new Timestamp(date.getTime())
          /**
           * add the device along with timestamp (t)
           */
          DAO.regApp(AppReg(data.simNumber, "+91"+data.phoneNumber, t))
          Redirect(routes.Application.addDevice()).flashing("addSuccess" -> "addedDevice")
        }
    )
  }
  
  /**
   * remove device form
   */
  val removeDeviceForm = Form(
      mapping(
          "phoneNumber" -> nonEmptyText
      )(RemoveDeviceFormData.apply)(RemoveDeviceFormData.unapply)
  )
  
  /**
   * remove device action
   */
  def removeDevice = withAdmin { admin => implicit request =>
    Ok(views.html.removeDevice(removeDeviceForm))
  }
  
  /**
   * remove device post action
   */
  def removeDevicePost = withAdmin { admin => implicit request => 
    removeDeviceForm.bindFromRequest().fold(
        formWithErrors => {
          BadRequest(views.html.removeDevice(removeDeviceForm))
        },
        data => {
          /**
           * unreg the app or device
           */
          DAO.appUnReg(data.phoneNumber)
          Redirect(routes.Application.removeDevice()).flashing("removeSuccess" -> "Device removed")
        }
    )
  }
  
  /**
   * admin logout action
   */
  def adminLogout = withAdmin { admin => implicit request =>
    Redirect(routes.Auth.adminLogin()).withNewSession
  }
  
  /**
   * admin home action
   */
  def adminHome = withAdmin { admin => implicit request =>
    Ok(views.html.adminHome())
  }
  
  /**
   * user signin count down
   */
  def userCountDown = withUserAsync { user => implicit request =>
    /**
     * insert the user entry (timestamp is provided by default and it is current timestamp)
     */
    DAO.insertUserEntry(user.id.get)
    /**
     * pick a phone number from the whitelist to display to the user
     */
    val phno = DAO.getPhnoFromWhiteList();
    /**
     * console message
     */
    Logger.info(user.email+" phno user count down "+user.phno)
    
    /**
     * display the user count down page sending phno of callee 
     */
    Ok(views.html.userCountDown((phno, user.phno)))
  }
  
  /**
   * this action is called from user count down
   * this action checks if a call has been logged within 2 minutes of time span and then provides access
   */
  def redirect = withUserAsync { user => implicit request =>
    /**
     * get phno of user using email
     */
    val option =  DAO.getPhno(user.email)
    
    /**
     * check if there is a call from this user's phno number within 2 minutes time span
     */
    val call = option match {
      /**
       *                                                          notice 2 minutes here
       */
      case Some(phno) => DAO.isMissedCallInInterval(user.id.get, phno, 2)
      case None => false
    }
    if(!call) {
      /**
       * set the signin status to down (0)
       */
      DAO.signinOff(user.email)
      
      /**
       * redirect to user login
       */
      Redirect(routes.Auth.userLogin)
    }else {
      
      /**
       * set the signin status to up (1)
       */
      DAO.signinOff(user.email)
      Redirect(routes.Application.userHome).withSession("callAuthed" -> user.email)
    }
  }
  
  /**
   * logout action
   * notice it redirects to login page clearing the existing session
   */
  def userLogout = withCallAuthedUser(user => implicit request => {
    Redirect(routes.Auth.userLogin).withNewSession
  })
  
  /**
   * user home action
   */
  def userHome = withCallAuthedUser(user => implicit request => {
	  Ok(views.html.userHome())
  	}
  )
  
  /**
   * Subscribe to get the events
   * used to subscribe for events such as missed call from specific phno number
   */
  def callTrigger(phno: String) = WebSocket.async[String]{ implicit request =>
    //implicit val timeout = Timeout(60 minutes)
    val future = callTriggerActor ? Send(phno)
    future.mapTo[(Iteratee[String, _], Enumerator[String])]
  }
 
 }