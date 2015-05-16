
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
/* userHome Template File */
object userHome extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.HtmlFormat.Appendable] {

    /* userHome Template File */
    def apply():play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](_display_(/*2.2*/main("Home")/*2.14*/{_display_(Seq[Any](format.raw/*2.15*/("""
	<button class="btn btn-primary"><a href=""""),_display_(/*3.44*/routes/*3.50*/.Application.userLogout),format.raw/*3.73*/("""">Log out</a></button>
	<div class="well">
		<h1>Welcome to home</h1>	
	</div>
""")))}),format.raw/*7.2*/("""
"""))}
    }
    
    def render(): play.api.templates.HtmlFormat.Appendable = apply()
    
    def f:(() => play.api.templates.HtmlFormat.Appendable) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jun 30 14:48:22 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/userHome.scala.html
                    HASH: 4e1438625ce6067d382ba727317ce1eb00bbad66
                    MATRIX: 688->30|708->42|746->43|816->87|830->93|873->116|982->196
                    LINES: 22->2|22->2|22->2|23->3|23->3|23->3|27->7
                    -- GENERATED --
                */
            