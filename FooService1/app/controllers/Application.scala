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

case class AddDeviceFormData(simNumber: String, phoneNumber: String)
case class RemoveDeviceFormData(phoneNumber: String)
case class MissedCallData(simNumber: String, phno: String)

object Application extends Controller with Secured {
  val callEventsActor = Akka.system().actorOf(Props[CallEventsActor])
  val callTriggerActor = Akka.system().actorOf(Props[CallTriggerActor])
  
  def index = Action {
    Ok(views.html.index("Welcome to Foo Service"))
  }
  def registerApp = Action(parse.json) { request =>
    /**
     * log message
     */
    println("register request")
    
    (request.body \ "simId").asOpt[String].map {simId =>{
      println("simId "+simId)
      if(DAO.isSimExists(simId.trim())) {
        println("sim exists")
        if(DAO.isWhitelisted(simId)) {
          Ok(Json.toJson(Map("status" -> 0)))
        }else {
          DAO.whitelist(simId)
          Ok(Json.toJson(Map("status" -> 1)))
        }
        
      }else {
        
        println("sim: "+simId+" not registered")
        Ok(Json.toJson(Map("status" -> -1)))
      }
    } 
   }.getOrElse( BadRequest(Json.toJson(Map("status" -> -1))))
  }
  def unregisterApp = Action(parse.json) { request =>
    (request.body \ "simId").asOpt[String].map {simId =>{
      if(DAO.isSimExists(simId)) {
        if(DAO.isWhitelisted(simId)) {
          DAO.blacklist(simId)
          Ok(Json.toJson(Map("status" -> 1)))
        }else {
          Ok(Json.toJson(Map("status" -> 0)))
        }
      }else {
        BadRequest(Json.toJson(Map("status" -> -1)))
      }
    } 
   }.getOrElse( BadRequest(Json.toJson(Map("status" -> -1))))
  }
  def foo = WebSocket.async[JsValue] { implicit request =>
    implicit val missedCallDataReads: Reads[MissedCallData] = (
    (JsPath \ "simNumber").read[String] and
    (JsPath \ "phno").read[String])(MissedCallData.apply _)
    println("missed call manager websocket")
    Future {
      val iteratee = Iteratee.foreach[JsValue] {
      json =>
        {
          callEventsActor ! Call(json)
          json.validate[MissedCallData].fold(
            invalid = {
              errors => println("errors")
            },
            valid = {
              data =>
                {
                  println("got a missed call from "+data.simNumber+" phno "+data.phno)
                  val date = new java.util.Date
                  val t = new Timestamp(date.getTime)
                  val sim = DAO.isSimExists(data.simNumber)
                  if(sim){
                   DAO.saveMissedCall(MissedCall(data.simNumber, data.phno, t, None)) 
                   println("call from user logged "+data.phno)
                  }else {
                    println("call from user not logged " + data.phno)
                  }
                  callTriggerActor ! CallFrom(data.phno)
                }
            })
        }
    }
    val enumerator = Enumerator.empty[JsValue]
    (iteratee, enumerator) 
    }
  }
  
  import scala.concurrent.duration._
  implicit val timeout = Timeout(1 hours)
  
  def events = WebSocket.async[String] { implicit request =>
    val future = (callEventsActor ? Join)
    future.mapTo[(Iteratee[String, _], Enumerator[String])]
  }
  
  val addDeviceForm = Form(
      mapping(
          "simId" -> nonEmptyText,
          "phoneNumber" -> nonEmptyText
      )(AddDeviceFormData.apply)(AddDeviceFormData.unapply) verifying("Sim Id or Phno already exists", data =>
          !DAO.isAppReg(data.simNumber, data.phoneNumber)
      )
  )
  def addDevice = withAdmin { admin => implicit request =>
    Ok(views.html.addDevice(addDeviceForm))
  }
  def addDevicePost = withAdmin { admin => implicit request =>
    addDeviceForm.bindFromRequest().fold(
        formWithErrors => {
          BadRequest(views.html.addDevice(formWithErrors))
        },
        data => {
          val date = new java.util.Date
          val t = new Timestamp(date.getTime())
          DAO.regApp(AppReg(data.simNumber, "+91"+data.phoneNumber, t))
          Redirect(routes.Application.addDevice()).flashing("addSuccess" -> "addedDevice")
        }
    )
  }
  val removeDeviceForm = Form(
      mapping(
          "phoneNumber" -> nonEmptyText
      )(RemoveDeviceFormData.apply)(RemoveDeviceFormData.unapply)
  )
  def removeDevice = withAdmin { admin => implicit request =>
    Ok(views.html.removeDevice(removeDeviceForm))
  }
  def removeDevicePost = withAdmin { admin => implicit request => 
    removeDeviceForm.bindFromRequest().fold(
        formWithErrors => {
          BadRequest(views.html.removeDevice(removeDeviceForm))
        },
        data => {
          DAO.appUnReg(data.phoneNumber)
          Redirect(routes.Application.removeDevice()).flashing("removeSuccess" -> "Device removed")
        }
    )
  }
  def adminLogout = withAdmin { admin => implicit request =>
    Redirect(routes.Auth.adminLogin()).withNewSession
  }
  def adminHome = withAdmin { admin => implicit request =>
    Ok(views.html.adminHome())
  }
  def userCountDown = withUserAsync { user => implicit request =>
    DAO.insertUserEntry(user.id.get)
    val phno = DAO.getPhnoFromWhiteList();
    println(user.email+" phno user count down "+user.phno)
    Ok(views.html.userCountDown((phno, user.phno)))
  }
  
  def redirect = withUserAsync { user => implicit request =>
    val option =  DAO.getPhno(user.email) 
    val call = option match {
      case Some(phno) => DAO.isMissedCallInInterval(user.id.get, phno, 2)
      case None => false
    }
    if(!call) {
      Redirect(routes.Auth.userLogin)
    }else {
      DAO.signinOff(user.email)
      Redirect(routes.Application.userHome).withSession("callAuthed" -> user.email)
    }
  }
  
  def userLogout = withCallAuthedUser(user => implicit request => {
    Redirect(routes.Auth.userLogin).withNewSession
  })
  def userHome = withCallAuthedUser(user => implicit request => {
	  Ok(views.html.userHome())
  	}
  )
  
  def callTrigger(phno: String) = WebSocket.async[String]{ implicit request =>
    //implicit val timeout = Timeout(60 minutes)
    val future = callTriggerActor ? Send(phno)
    future.mapTo[(Iteratee[String, _], Enumerator[String])]
  }
 
 }