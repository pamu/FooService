package controllers

import play.api.mvc.RequestHeader
import play.api.mvc.Results
import play.api.mvc.Result
import play.api.mvc.Security
import models.User
import play.api.mvc.BodyParser
import play.api.mvc.Request
import play.api.mvc.AnyContent
import models.DAO
import play.api.mvc.Action
import models.Admin
import play.api.mvc.Request
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import models.SigningupUser

trait Secured {
 /**
   * Retrieve the connected user email.
   */
  private def email(request: RequestHeader) = request.session.get("email")
  
  private def adminEmail(request: RequestHeader) = request.session.get("adminEmail")
  
  private def callAuth(request: RequestHeader) = request.session.get("callAuthed")
  
  private def onCallUnauthorized(request: RequestHeader) = Results.Redirect(routes.Auth.userLogin())
  
  private def userSignupEmail(request: RequestHeader) = request.session.get("userSignupEmail")
  
  private def onUserSignupEmailUnauthorized(request: RequestHeader) = Results.Redirect(routes.Auth.userSignup())
  
  def isUserSignupAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(userSignupEmail, onUserSignupEmailUnauthorized){ email =>
    Action.async(request => Future(f(email)(request)))
  }
  
  def getSigningupUser(request: RequestHeader) = request.session.get("userSignupEmail").zip(request.session.get("userSignupPhno"))
		  											.map(t => SigningupUser(t._1, t._2)).headOption
  
  def withSigningupUser(f: => SigningupUser => Request[AnyContent] => Result) = Security.Authenticated(getSigningupUser, onUserSignupEmailUnauthorized){ user =>
    Action.async(request => Future(f(user)(request)))
  }
  /**
   * Redirect to login if the user in not authorized.
   */
  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Auth.userLogin())
 
  private def onUnauthorizedAdmin(request: RequestHeader) = Results.Redirect(routes.Auth.adminLogin())
  
  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(email, onUnauthorized) { email =>
    Action(request => f(email)(request))
  }
 
   def IsCallAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(callAuth, onCallUnauthorized) { email =>
    Action.async(request => Future(f(email)(request)))
  }
 
  def IsAuthenticatedAsync(f: => String => Request[AnyContent] => Result) = Security.Authenticated(email, onUnauthorized) { email =>
    Action.async(request => Future(f(email)(request)))
  }
 
  def IsAuthenticatedAdmin(f: => String => Request[AnyContent] => Result) = Security.Authenticated(adminEmail, onUnauthorizedAdmin) { email =>
    Action(request => f(email)(request))
  }
   /**
   * This method shows how you could wrap the isAuthenticated method to also fetch your user
   * You will need to implement UserDAO.findOneByEmail
   */
  def withUser(f: User => Request[AnyContent] => Result) = IsAuthenticated{ email => implicit request =>
    DAO.findOneByEmail(email) match {
      case Some(user) => f(user)(request)
      case None => Results.Forbidden//onUnauthorized(request)
    }
  }
 
  def withCallAuthedUser(f: User => Request[AnyContent] => Result) = IsCallAuthenticated{ email => implicit request =>
    DAO.findOneByEmail(email) match {
      case Some(user) => f(user)(request)
      case None => Results.Forbidden//onUnauthorized(request)
    }
  }
  
   def withUserAsync(f: User => Request[AnyContent] => Result) = IsAuthenticatedAsync{ email => implicit request =>
    DAO.findOneByEmail(email) match {
      case Some(user) => f(user)(request)
      case None => Results.Forbidden//onUnauthorized(request)
    }
  }
 
  def withAdmin(f: Admin => Request[AnyContent] => Result) = IsAuthenticatedAdmin{ email => implicit request =>
    DAO.findOneAdminByEmail(email) match {
      case Some(admin) => f(admin)(request)
      case None => Results.Forbidden//onUnauthorized(request)
    }
  }
  /**
   * now this method works with any body parser
   */
  def IsAuthenticatedWithBodyParser[A](p: BodyParser[A])(f: => String => Request[A] => Result) = Security.Authenticated(email, onUnauthorized) { user =>
    Action(p)(request => f(user)(request))
  }
  
  /**
   * now this method works with any body parser
   */
  def withUserWithBodyParser[A](p: BodyParser[A])(f: User => Request[A] => Result) = IsAuthenticatedWithBodyParser(p) { email => implicit request => 
    DAO.findOneByEmail(email) match {
      case Some(user) => f(user)(request)
      case None => Results.Forbidden
    }
  }
}