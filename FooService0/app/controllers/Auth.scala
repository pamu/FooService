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

case class UserFormData(email: String)
case class AdminFormData(email: String, password: String)

object Auth extends Controller with Secured {
  
  val userLoginForm = Form(
      mapping(
          "email" -> email
          )(UserFormData.apply)(UserFormData.unapply).verifying("Username doesn't exist",
              data => {
                DAO.checkUserEmailAvailability(data.email)
              }
          )
      )
  
  def userLogin = Action { implicit request =>
	  Ok(views.html.userLogin(userLoginForm))
  }
  def userLoginPost = Action { implicit request =>
	  userLoginForm.bindFromRequest().fold(hasErrors => {
	    BadRequest(views.html.userLogin(userLoginForm))
	  }, success => {
	    Redirect(routes.Application.userCountDown).withNewSession.withSession("email" -> success.email)
	  })
  }
  val adminLoginForm = Form(
      mapping(
          "email"-> email,
          "password" -> nonEmptyText
          )(AdminFormData.apply)(AdminFormData.unapply).verifying("Email or Password is wrong !!!", data => {
        	  DAO.checkAdmin(data.email, data.password)
          	}
          )
      )
  def adminLogin = Action { implicit request =>
	  session.get("adminEmail") match {
	    case Some(x) => Redirect(routes.Application.adminHome())
	    case None => Ok(views.html.adminLogin(adminLoginForm))
	  }
  }
  def adminLoginPost = Action { implicit request =>
	  adminLoginForm.bindFromRequest().fold(errors => {
	    BadRequest(views.html.adminLogin(errors))
	  }, data => {
	    println(data)
	    Redirect(routes.Application.adminHome()).withSession("adminEmail" -> data.email)
	  })
  }
  
  val userSignupForm = Form(
      tuple(
          "email" -> email,
          "phno" -> nonEmptyText
          ).verifying("Email or phno already exists", t => {
            val exists = DAO.checkUserEmailAvailability(t._1) || DAO.checkUserPhnoAvailability("+91"+t._2)
            //val others = DAO.findSUEmailAvialability(t._1) || DAO.findSUPhnoAvialability(t._2)
            //!(exists || others)
            !(exists)
          })
  )
  def userSignup = Action { implicit request =>
    Ok(views.html.userSignup(userSignupForm)).withNewSession
  }
  def userSignupPost = Action.async { implicit request =>
    Future{
     userSignupForm.bindFromRequest().fold(errors => {
      BadRequest(views.html.userSignup(errors))
    }, data => {
      /*
    	DAO.saveSigningupUser(SigningupUser(data._1, "+91"+data._2))
    	import scala.concurrent.duration._
    	Akka.system().scheduler.scheduleOnce(2 minutes)(
    			DAO.removeSigningupUser(data._1)
    	)
    	* 
    	*/
    	Redirect(routes.Auth.userSignupCountDown()).withSession("userSignupEmail"-> data._1, "userSignupPhno" -> ("+91"+data._2))
    	}	
      ) 
    }
  }
  def userSignupCountDown =  withSigningupUser { user => implicit request =>
     DAO.insertSigningupUserEntry(SigningupUser(user.email, user.phno))
     Ok(views.html.userSignupCountDown((DAO.getPhnoFromWhiteList()), user.phno))
  }
  def userSignupRedirect = withSigningupUser { user => implicit request =>
   val call = DAO.isMissedCallInIntervalForSigningupUser(SigningupUser(user.email, user.phno), user.phno, 2)
    //DAO.removeSigningupUser(user.email)
    if(!call) {
      Redirect(routes.Auth.userSignup).withNewSession.flashing("success" -> "signup failed. retry !!!")
    }else {
      DAO.saveUser(User(user.email, user.phno))
      Redirect(routes.Auth.userSignup).withNewSession.flashing("success" -> "signup successful !!!")
    }
  }
}