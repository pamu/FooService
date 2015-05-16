// @SOURCE:/home/nagarjuna/FooService/conf/routes
// @HASH:41e353257e6fb0d429db1d88a7b59a54d7a6b417
// @DATE:Tue Jul 01 01:00:32 IST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:5
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:6
private[this] lazy val controllers_Application_foo1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("foo"))))
        

// @LINE:7
private[this] lazy val controllers_Auth_adminLogin2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin"))))
        

// @LINE:8
private[this] lazy val controllers_Auth_userLogin3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("user"))))
        

// @LINE:9
private[this] lazy val controllers_Auth_userLoginPost4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userLoginPost"))))
        

// @LINE:10
private[this] lazy val controllers_Auth_adminLoginPost5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("adminLoginPost"))))
        

// @LINE:11
private[this] lazy val controllers_Application_addDevice6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addDevice"))))
        

// @LINE:12
private[this] lazy val controllers_Application_addDevicePost7 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addDevicePost"))))
        

// @LINE:13
private[this] lazy val controllers_Application_removeDevice8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeDevice"))))
        

// @LINE:14
private[this] lazy val controllers_Application_removeDevicePost9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeDevicePost"))))
        

// @LINE:15
private[this] lazy val controllers_Application_adminLogout10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("adminLogout"))))
        

// @LINE:16
private[this] lazy val controllers_Application_adminHome11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("adminHome"))))
        

// @LINE:17
private[this] lazy val controllers_Application_registerApp12 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("register"))))
        

// @LINE:18
private[this] lazy val controllers_Application_unregisterApp13 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("unregister"))))
        

// @LINE:19
private[this] lazy val controllers_Application_userCountDown14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userCountDown"))))
        

// @LINE:20
private[this] lazy val controllers_Application_redirect15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("redirect"))))
        

// @LINE:21
private[this] lazy val controllers_Application_userLogout16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userLogout"))))
        

// @LINE:22
private[this] lazy val controllers_Application_userHome17 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userHome"))))
        

// @LINE:23
private[this] lazy val controllers_Application_events18 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("events"))))
        

// @LINE:24
private[this] lazy val controllers_Application_callTrigger19 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("callTrigger/"),DynamicPart("phno", """[^/]+""",true))))
        

// @LINE:25
private[this] lazy val controllers_Auth_userSignup20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userSignup"))))
        

// @LINE:26
private[this] lazy val controllers_Auth_userSignupPost21 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userSignupPost"))))
        

// @LINE:27
private[this] lazy val controllers_Auth_userSignupCountDown22 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userSignupCountDown"))))
        

// @LINE:28
private[this] lazy val controllers_Auth_userSignupRedirect23 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userSignupRedirect"))))
        

// @LINE:31
private[this] lazy val controllers_Assets_at24 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:32
private[this] lazy val controllers_WebJarAssets_at25 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("webjars/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """foo""","""controllers.Application.foo()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin""","""controllers.Auth.adminLogin()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """user""","""controllers.Auth.userLogin()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userLoginPost""","""controllers.Auth.userLoginPost()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """adminLoginPost""","""controllers.Auth.adminLoginPost()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addDevice""","""controllers.Application.addDevice()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addDevicePost""","""controllers.Application.addDevicePost()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeDevice""","""controllers.Application.removeDevice()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeDevicePost""","""controllers.Application.removeDevicePost()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """adminLogout""","""controllers.Application.adminLogout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """adminHome""","""controllers.Application.adminHome()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """register""","""controllers.Application.registerApp()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """unregister""","""controllers.Application.unregisterApp()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userCountDown""","""controllers.Application.userCountDown()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """redirect""","""controllers.Application.redirect()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userLogout""","""controllers.Application.userLogout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userHome""","""controllers.Application.userHome()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events""","""controllers.Application.events()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """callTrigger/$phno<[^/]+>""","""controllers.Application.callTrigger(phno:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userSignup""","""controllers.Auth.userSignup()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userSignupPost""","""controllers.Auth.userSignupPost()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userSignupCountDown""","""controllers.Auth.userSignupCountDown()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userSignupRedirect""","""controllers.Auth.userSignupRedirect()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """webjars/$file<.+>""","""controllers.WebJarAssets.at(file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:5
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "", "controllers.Application", "index", Nil,"GET", """""", Routes.prefix + """"""))
   }
}
        

// @LINE:6
case controllers_Application_foo1(params) => {
   call { 
        invokeHandler(controllers.Application.foo(), HandlerDef(this, "", "controllers.Application", "foo", Nil,"GET", """""", Routes.prefix + """foo"""))
   }
}
        

// @LINE:7
case controllers_Auth_adminLogin2(params) => {
   call { 
        invokeHandler(controllers.Auth.adminLogin(), HandlerDef(this, "", "controllers.Auth", "adminLogin", Nil,"GET", """""", Routes.prefix + """admin"""))
   }
}
        

// @LINE:8
case controllers_Auth_userLogin3(params) => {
   call { 
        invokeHandler(controllers.Auth.userLogin(), HandlerDef(this, "", "controllers.Auth", "userLogin", Nil,"GET", """""", Routes.prefix + """user"""))
   }
}
        

// @LINE:9
case controllers_Auth_userLoginPost4(params) => {
   call { 
        invokeHandler(controllers.Auth.userLoginPost(), HandlerDef(this, "", "controllers.Auth", "userLoginPost", Nil,"POST", """""", Routes.prefix + """userLoginPost"""))
   }
}
        

// @LINE:10
case controllers_Auth_adminLoginPost5(params) => {
   call { 
        invokeHandler(controllers.Auth.adminLoginPost(), HandlerDef(this, "", "controllers.Auth", "adminLoginPost", Nil,"POST", """""", Routes.prefix + """adminLoginPost"""))
   }
}
        

// @LINE:11
case controllers_Application_addDevice6(params) => {
   call { 
        invokeHandler(controllers.Application.addDevice(), HandlerDef(this, "", "controllers.Application", "addDevice", Nil,"GET", """""", Routes.prefix + """addDevice"""))
   }
}
        

// @LINE:12
case controllers_Application_addDevicePost7(params) => {
   call { 
        invokeHandler(controllers.Application.addDevicePost(), HandlerDef(this, "", "controllers.Application", "addDevicePost", Nil,"POST", """""", Routes.prefix + """addDevicePost"""))
   }
}
        

// @LINE:13
case controllers_Application_removeDevice8(params) => {
   call { 
        invokeHandler(controllers.Application.removeDevice(), HandlerDef(this, "", "controllers.Application", "removeDevice", Nil,"GET", """""", Routes.prefix + """removeDevice"""))
   }
}
        

// @LINE:14
case controllers_Application_removeDevicePost9(params) => {
   call { 
        invokeHandler(controllers.Application.removeDevicePost(), HandlerDef(this, "", "controllers.Application", "removeDevicePost", Nil,"POST", """""", Routes.prefix + """removeDevicePost"""))
   }
}
        

// @LINE:15
case controllers_Application_adminLogout10(params) => {
   call { 
        invokeHandler(controllers.Application.adminLogout(), HandlerDef(this, "", "controllers.Application", "adminLogout", Nil,"GET", """""", Routes.prefix + """adminLogout"""))
   }
}
        

// @LINE:16
case controllers_Application_adminHome11(params) => {
   call { 
        invokeHandler(controllers.Application.adminHome(), HandlerDef(this, "", "controllers.Application", "adminHome", Nil,"GET", """""", Routes.prefix + """adminHome"""))
   }
}
        

// @LINE:17
case controllers_Application_registerApp12(params) => {
   call { 
        invokeHandler(controllers.Application.registerApp(), HandlerDef(this, "", "controllers.Application", "registerApp", Nil,"POST", """""", Routes.prefix + """register"""))
   }
}
        

// @LINE:18
case controllers_Application_unregisterApp13(params) => {
   call { 
        invokeHandler(controllers.Application.unregisterApp(), HandlerDef(this, "", "controllers.Application", "unregisterApp", Nil,"POST", """""", Routes.prefix + """unregister"""))
   }
}
        

// @LINE:19
case controllers_Application_userCountDown14(params) => {
   call { 
        invokeHandler(controllers.Application.userCountDown(), HandlerDef(this, "", "controllers.Application", "userCountDown", Nil,"GET", """""", Routes.prefix + """userCountDown"""))
   }
}
        

// @LINE:20
case controllers_Application_redirect15(params) => {
   call { 
        invokeHandler(controllers.Application.redirect(), HandlerDef(this, "", "controllers.Application", "redirect", Nil,"GET", """""", Routes.prefix + """redirect"""))
   }
}
        

// @LINE:21
case controllers_Application_userLogout16(params) => {
   call { 
        invokeHandler(controllers.Application.userLogout(), HandlerDef(this, "", "controllers.Application", "userLogout", Nil,"GET", """""", Routes.prefix + """userLogout"""))
   }
}
        

// @LINE:22
case controllers_Application_userHome17(params) => {
   call { 
        invokeHandler(controllers.Application.userHome(), HandlerDef(this, "", "controllers.Application", "userHome", Nil,"GET", """""", Routes.prefix + """userHome"""))
   }
}
        

// @LINE:23
case controllers_Application_events18(params) => {
   call { 
        invokeHandler(controllers.Application.events(), HandlerDef(this, "", "controllers.Application", "events", Nil,"GET", """""", Routes.prefix + """events"""))
   }
}
        

// @LINE:24
case controllers_Application_callTrigger19(params) => {
   call(params.fromPath[String]("phno", None)) { (phno) =>
        invokeHandler(controllers.Application.callTrigger(phno), HandlerDef(this, "", "controllers.Application", "callTrigger", Seq(classOf[String]),"GET", """""", Routes.prefix + """callTrigger/$phno<[^/]+>"""))
   }
}
        

// @LINE:25
case controllers_Auth_userSignup20(params) => {
   call { 
        invokeHandler(controllers.Auth.userSignup(), HandlerDef(this, "", "controllers.Auth", "userSignup", Nil,"GET", """""", Routes.prefix + """userSignup"""))
   }
}
        

// @LINE:26
case controllers_Auth_userSignupPost21(params) => {
   call { 
        invokeHandler(controllers.Auth.userSignupPost(), HandlerDef(this, "", "controllers.Auth", "userSignupPost", Nil,"POST", """""", Routes.prefix + """userSignupPost"""))
   }
}
        

// @LINE:27
case controllers_Auth_userSignupCountDown22(params) => {
   call { 
        invokeHandler(controllers.Auth.userSignupCountDown(), HandlerDef(this, "", "controllers.Auth", "userSignupCountDown", Nil,"GET", """""", Routes.prefix + """userSignupCountDown"""))
   }
}
        

// @LINE:28
case controllers_Auth_userSignupRedirect23(params) => {
   call { 
        invokeHandler(controllers.Auth.userSignupRedirect(), HandlerDef(this, "", "controllers.Auth", "userSignupRedirect", Nil,"GET", """""", Routes.prefix + """userSignupRedirect"""))
   }
}
        

// @LINE:31
case controllers_Assets_at24(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:32
case controllers_WebJarAssets_at25(params) => {
   call(params.fromPath[String]("file", None)) { (file) =>
        invokeHandler(controllers.WebJarAssets.at(file), HandlerDef(this, "", "controllers.WebJarAssets", "at", Seq(classOf[String]),"GET", """""", Routes.prefix + """webjars/$file<.+>"""))
   }
}
        
}

}
     