
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main("Welcome to Foo Service")/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""
    <div class="well">
    	<center>
    		<a href=""""),_display_(/*6.17*/routes/*6.23*/.Auth.userSignup()),format.raw/*6.41*/(""""><h3>User Signup</h3></a>
    		<a href="/user"><h3>User Login</h3></a>
    		<a href="/admin"><h3>Admin Login</h3></a>
    	</center>
    </div>

""")))}),format.raw/*12.2*/("""
"""))}
    }
    
    def render(message:String): play.api.templates.HtmlFormat.Appendable = apply(message)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 01 01:00:33 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/index.scala.html
                    HASH: 1d9922e5ec71be9ba547eada55eb3a0913728b3d
                    MATRIX: 556->1|667->18|695->21|733->51|772->53|852->107|866->113|904->131|1083->280
                    LINES: 19->1|22->1|24->3|24->3|24->3|27->6|27->6|27->6|33->12
                    -- GENERATED --
                */
            