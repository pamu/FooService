package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import models.DAO
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import models.SigningupUser
import models.User
import play.libs.Akka
import models.SigninStatus

/**
 * case classes representing the form data
 */
case class UserFormData(email: String)
case class AdminFormData(email: String, password: String)

/**
 * Auth controller with Secured mixin
 */
object Auth extends Controller with Secured {
  
  /**
   * userLogin Form mapping
   */
  val userLoginForm = Form(
      mapping(
          "email" -> email.verifying("error.exists", email => {
            /**
             * allow only if email is available
             */
            DAO.checkUserEmailAvailability(email)
          })
          )(UserFormData.apply)(UserFormData.unapply).verifying("error.login",
              data => {
                /**
                 * check is any other person is already trying to login
                 */
                DAO.isTryingToSignin(data.email) match {
                  case Some(value) => if(value == 1) {
                    false
                  }else {
                    /**
                     * set the signin status flag up
                     */
                    DAO.signinOn(data.email)
                    true
                  }
                  case None => false
                }
              }
          )
      )
      
  /**
   * user login action renders login form on invocation
   */
  def userLogin = Action { implicit request =>
	  Ok(views.html.userLogin(userLoginForm))
  }
  
  /**
   * user login post action
   */
  def userLoginPost = Action { implicit request =>
	  userLoginForm.bindFromRequest().fold(hasErrors => {
	    BadRequest(views.html.userLogin(userLoginForm))
	  }, success => {
	    Redirect(routes.Application.userCountDown).withNewSession.withSession("email" -> success.email)
	  })
  }
  
  /**
   * admin login form mapping
   */
  val adminLoginForm = Form(
      mapping(
          "email"-> email,
          "password" -> nonEmptyText
          )(AdminFormData.apply)(AdminFormData.unapply).verifying("Email or Password is wrong !!!", data => {
        	  DAO.checkAdmin(data.email, data.password)
          	}
          )
      )
  
  /**
   * admin login action
   */
  def adminLogin = Action { implicit request =>
	  session.get("adminEmail") match {
	    case Some(x) => Redirect(routes.Application.adminHome())
	    case None => Ok(views.html.adminLogin(adminLoginForm))
	  }
  }
  
  /**
   * admin login post action
   */
  def adminLoginPost = Action { implicit request =>
	  adminLoginForm.bindFromRequest().fold(errors => {
	    BadRequest(views.html.adminLogin(errors))
	  }, data => {
	    Redirect(routes.Application.adminHome()).withSession("adminEmail" -> data.email)
	  })
  }
  
  /**
   * user signup form mapping
   */
  val userSignupForm = Form(
      tuple(
          "email" -> email,
          "phno" -> nonEmptyText
          ).verifying("Email or phno already exists", t => {
            /**
             * check if the user or phno are already available
             * if user or phno already exist stop signup process
             */
            val exists = DAO.checkUserEmailAvailability(t._1) || DAO.checkUserPhnoAvailability("+91"+t._2)
            /**
             * dude, notice the negation symbol
             */
            !(exists)
          })
  )
  
  /**
   * user signup action
   */
  def userSignup = Action { implicit request =>
    Ok(views.html.userSignup(userSignupForm)).withNewSession
  }
  
  /**
   * user signup post action
   */
  def userSignupPost = Action.async { implicit request =>
    Future{
     userSignupForm.bindFromRequest().fold(errors => {
      BadRequest(views.html.userSignup(errors))
    }, data => {
    	/**
    	 * on success redirect to user signup count down page
    	 */
    	Redirect(routes.Auth.userSignupCountDown()).withSession("userSignupEmail"-> data._1, "userSignupPhno" -> ("+91"+data._2))
    	}	
      ) 
    }
  }
  
  /**
   * user signup count down page
   */
  def userSignupCountDown =  withSigningupUser { user => implicit request =>
    /**
     * notice that i,am logging the signingup detail along with timestamp to database
     * this can be later used to provide auth
     */
     DAO.insertSigningupUserEntry(SigningupUser(user.email, user.phno))
     Ok(views.html.userSignupCountDown((DAO.getPhnoFromWhiteList()), user.phno))
  }
  
  /**
   * user signup redirect action
   */
  def userSignupRedirect = withSigningupUser { user => implicit request =>
   /**
    * check if there is a missed call in last 2 minutes till now
    */
   val call = DAO.isMissedCallInIntervalForSigningupUser(SigningupUser(user.email, user.phno), user.phno, 2)
    
   if(!call) {
      Redirect(routes.Auth.userSignup).withNewSession.flashing("success" -> "signup failed. retry !!!")
    }else {
      /**
       * save the user
       */
      val userId = DAO.saveUser(User(user.email, user.phno))
      /**
       * create the entry in signin status table
       */
      DAO.db.withTransaction(implicit tx => {
        import slick.driver.MySQLDriver.simple._
        DAO.signinStatuses += SigninStatus(userId)
      })
      Redirect(routes.Auth.userSignup).withNewSession.flashing("success" -> "signup successful !!!")
    }
  }
}