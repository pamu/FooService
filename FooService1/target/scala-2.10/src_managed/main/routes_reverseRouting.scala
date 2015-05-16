// @SOURCE:/home/nagarjuna/FooService/conf/routes
// @HASH:41e353257e6fb0d429db1d88a7b59a54d7a6b417
// @DATE:Mon Jun 30 14:48:20 IST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
// @LINE:5
package controllers {

// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseAuth {
    

// @LINE:9
def userLoginPost(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "userLoginPost")
}
                                                

// @LINE:8
def userLogin(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "user")
}
                                                

// @LINE:27
def userSignupCountDown(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "userSignupCountDown")
}
                                                

// @LINE:7
def adminLogin(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin")
}
                                                

// @LINE:28
def userSignupRedirect(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "userSignupRedirect")
}
                                                

// @LINE:25
def userSignup(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "userSignup")
}
                                                

// @LINE:10
def adminLoginPost(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "adminLoginPost")
}
                                                

// @LINE:26
def userSignupPost(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "userSignupPost")
}
                                                
    
}
                          

// @LINE:32
class ReverseWebJarAssets {
    

// @LINE:32
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "webjars/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:31
class ReverseAssets {
    

// @LINE:31
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:6
// @LINE:5
class ReverseApplication {
    

// @LINE:18
def unregisterApp(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "unregister")
}
                                                

// @LINE:15
def adminLogout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "adminLogout")
}
                                                

// @LINE:16
def adminHome(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "adminHome")
}
                                                

// @LINE:20
def redirect(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "redirect")
}
                                                

// @LINE:24
def callTrigger(phno:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "callTrigger/" + implicitly[PathBindable[String]].unbind("phno", dynamicString(phno)))
}
                                                

// @LINE:19
def userCountDown(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "userCountDown")
}
                                                

// @LINE:6
def foo(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "foo")
}
                                                

// @LINE:22
def userHome(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "userHome")
}
                                                

// @LINE:17
def registerApp(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                                                

// @LINE:11
def addDevice(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "addDevice")
}
                                                

// @LINE:13
def removeDevice(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "removeDevice")
}
                                                

// @LINE:12
def addDevicePost(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addDevicePost")
}
                                                

// @LINE:21
def userLogout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "userLogout")
}
                                                

// @LINE:14
def removeDevicePost(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeDevicePost")
}
                                                

// @LINE:23
def events(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "events")
}
                                                

// @LINE:5
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
// @LINE:5
package controllers.javascript {

// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseAuth {
    

// @LINE:9
def userLoginPost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.userLoginPost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "userLoginPost"})
      }
   """
)
                        

// @LINE:8
def userLogin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.userLogin",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "user"})
      }
   """
)
                        

// @LINE:27
def userSignupCountDown : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.userSignupCountDown",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userSignupCountDown"})
      }
   """
)
                        

// @LINE:7
def adminLogin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.adminLogin",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin"})
      }
   """
)
                        

// @LINE:28
def userSignupRedirect : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.userSignupRedirect",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userSignupRedirect"})
      }
   """
)
                        

// @LINE:25
def userSignup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.userSignup",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userSignup"})
      }
   """
)
                        

// @LINE:10
def adminLoginPost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.adminLoginPost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "adminLoginPost"})
      }
   """
)
                        

// @LINE:26
def userSignupPost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Auth.userSignupPost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "userSignupPost"})
      }
   """
)
                        
    
}
              

// @LINE:32
class ReverseWebJarAssets {
    

// @LINE:32
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.WebJarAssets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "webjars/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:31
class ReverseAssets {
    

// @LINE:31
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:6
// @LINE:5
class ReverseApplication {
    

// @LINE:18
def unregisterApp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.unregisterApp",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "unregister"})
      }
   """
)
                        

// @LINE:15
def adminLogout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.adminLogout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "adminLogout"})
      }
   """
)
                        

// @LINE:16
def adminHome : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.adminHome",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "adminHome"})
      }
   """
)
                        

// @LINE:20
def redirect : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.redirect",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "redirect"})
      }
   """
)
                        

// @LINE:24
def callTrigger : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.callTrigger",
   """
      function(phno) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "callTrigger/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("phno", encodeURIComponent(phno))})
      }
   """
)
                        

// @LINE:19
def userCountDown : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.userCountDown",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userCountDown"})
      }
   """
)
                        

// @LINE:6
def foo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.foo",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "foo"})
      }
   """
)
                        

// @LINE:22
def userHome : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.userHome",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userHome"})
      }
   """
)
                        

// @LINE:17
def registerApp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.registerApp",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        

// @LINE:11
def addDevice : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addDevice",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "addDevice"})
      }
   """
)
                        

// @LINE:13
def removeDevice : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeDevice",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "removeDevice"})
      }
   """
)
                        

// @LINE:12
def addDevicePost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addDevicePost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addDevicePost"})
      }
   """
)
                        

// @LINE:21
def userLogout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.userLogout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userLogout"})
      }
   """
)
                        

// @LINE:14
def removeDevicePost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeDevicePost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeDevicePost"})
      }
   """
)
                        

// @LINE:23
def events : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.events",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "events"})
      }
   """
)
                        

// @LINE:5
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:32
// @LINE:31
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
// @LINE:5
package controllers.ref {


// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseAuth {
    

// @LINE:9
def userLoginPost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.userLoginPost(), HandlerDef(this, "", "controllers.Auth", "userLoginPost", Seq(), "POST", """""", _prefix + """userLoginPost""")
)
                      

// @LINE:8
def userLogin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.userLogin(), HandlerDef(this, "", "controllers.Auth", "userLogin", Seq(), "GET", """""", _prefix + """user""")
)
                      

// @LINE:27
def userSignupCountDown(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.userSignupCountDown(), HandlerDef(this, "", "controllers.Auth", "userSignupCountDown", Seq(), "GET", """""", _prefix + """userSignupCountDown""")
)
                      

// @LINE:7
def adminLogin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.adminLogin(), HandlerDef(this, "", "controllers.Auth", "adminLogin", Seq(), "GET", """""", _prefix + """admin""")
)
                      

// @LINE:28
def userSignupRedirect(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.userSignupRedirect(), HandlerDef(this, "", "controllers.Auth", "userSignupRedirect", Seq(), "GET", """""", _prefix + """userSignupRedirect""")
)
                      

// @LINE:25
def userSignup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.userSignup(), HandlerDef(this, "", "controllers.Auth", "userSignup", Seq(), "GET", """""", _prefix + """userSignup""")
)
                      

// @LINE:10
def adminLoginPost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.adminLoginPost(), HandlerDef(this, "", "controllers.Auth", "adminLoginPost", Seq(), "POST", """""", _prefix + """adminLoginPost""")
)
                      

// @LINE:26
def userSignupPost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Auth.userSignupPost(), HandlerDef(this, "", "controllers.Auth", "userSignupPost", Seq(), "POST", """""", _prefix + """userSignupPost""")
)
                      
    
}
                          

// @LINE:32
class ReverseWebJarAssets {
    

// @LINE:32
def at(file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.WebJarAssets.at(file), HandlerDef(this, "", "controllers.WebJarAssets", "at", Seq(classOf[String]), "GET", """""", _prefix + """webjars/$file<.+>""")
)
                      
    
}
                          

// @LINE:31
class ReverseAssets {
    

// @LINE:31
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:6
// @LINE:5
class ReverseApplication {
    

// @LINE:18
def unregisterApp(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.unregisterApp(), HandlerDef(this, "", "controllers.Application", "unregisterApp", Seq(), "POST", """""", _prefix + """unregister""")
)
                      

// @LINE:15
def adminLogout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.adminLogout(), HandlerDef(this, "", "controllers.Application", "adminLogout", Seq(), "GET", """""", _prefix + """adminLogout""")
)
                      

// @LINE:16
def adminHome(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.adminHome(), HandlerDef(this, "", "controllers.Application", "adminHome", Seq(), "GET", """""", _prefix + """adminHome""")
)
                      

// @LINE:20
def redirect(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.redirect(), HandlerDef(this, "", "controllers.Application", "redirect", Seq(), "GET", """""", _prefix + """redirect""")
)
                      

// @LINE:24
def callTrigger(phno:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.callTrigger(phno), HandlerDef(this, "", "controllers.Application", "callTrigger", Seq(classOf[String]), "GET", """""", _prefix + """callTrigger/$phno<[^/]+>""")
)
                      

// @LINE:19
def userCountDown(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.userCountDown(), HandlerDef(this, "", "controllers.Application", "userCountDown", Seq(), "GET", """""", _prefix + """userCountDown""")
)
                      

// @LINE:6
def foo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.foo(), HandlerDef(this, "", "controllers.Application", "foo", Seq(), "GET", """""", _prefix + """foo""")
)
                      

// @LINE:22
def userHome(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.userHome(), HandlerDef(this, "", "controllers.Application", "userHome", Seq(), "GET", """""", _prefix + """userHome""")
)
                      

// @LINE:17
def registerApp(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.registerApp(), HandlerDef(this, "", "controllers.Application", "registerApp", Seq(), "POST", """""", _prefix + """register""")
)
                      

// @LINE:11
def addDevice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addDevice(), HandlerDef(this, "", "controllers.Application", "addDevice", Seq(), "GET", """""", _prefix + """addDevice""")
)
                      

// @LINE:13
def removeDevice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeDevice(), HandlerDef(this, "", "controllers.Application", "removeDevice", Seq(), "GET", """""", _prefix + """removeDevice""")
)
                      

// @LINE:12
def addDevicePost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addDevicePost(), HandlerDef(this, "", "controllers.Application", "addDevicePost", Seq(), "POST", """""", _prefix + """addDevicePost""")
)
                      

// @LINE:21
def userLogout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.userLogout(), HandlerDef(this, "", "controllers.Application", "userLogout", Seq(), "GET", """""", _prefix + """userLogout""")
)
                      

// @LINE:14
def removeDevicePost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeDevicePost(), HandlerDef(this, "", "controllers.Application", "removeDevicePost", Seq(), "POST", """""", _prefix + """removeDevicePost""")
)
                      

// @LINE:23
def events(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.events(), HandlerDef(this, "", "controllers.Application", "events", Seq(), "GET", """""", _prefix + """events""")
)
                      

// @LINE:5
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "", "controllers.Application", "index", Seq(), "GET", """""", _prefix + """""")
)
                      
    
}
                          
}
        
    