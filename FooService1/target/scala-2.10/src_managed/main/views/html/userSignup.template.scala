
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
/* userSignup Template File */
object userSignup extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[scala.Tuple2[String, String]],play.api.mvc.Flash,play.api.templates.HtmlFormat.Appendable] {

    /* userSignup Template File */
    def apply/*2.2*/(userSignupForm: Form[(String, String)])(implicit flash: play.api.mvc.Flash):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import helper.twitterBootstrap._

import play.api.i18n.Messages


Seq[Any](format.raw/*2.78*/("""

"""),format.raw/*7.1*/("""
"""),_display_(/*8.2*/main("User Signup Form")/*8.26*/{_display_(Seq[Any](format.raw/*8.27*/("""
	<fieldset>
		<legend>User Signup</legend>
		"""),_display_(/*11.4*/flash/*11.9*/.get("success").map/*11.28*/{ msg =>_display_(Seq[Any](format.raw/*11.36*/("""
			<div class="alert alert-success">"""),_display_(/*12.38*/msg),format.raw/*12.41*/("""</div>
		""")))}),format.raw/*13.4*/("""
		"""),_display_(/*14.4*/form(action=routes.Auth.userSignupPost())/*14.45*/{_display_(Seq[Any](format.raw/*14.46*/("""
				"""),_display_(/*15.6*/inputText(
					userSignupForm("email"),
					'_label -> "Email",
					'_help -> "Choose valid email address",
					'_error -> "Enter the valid email",
					'_showConstraints -> false
				)),format.raw/*21.6*/("""
				"""),_display_(/*22.6*/inputText(
					userSignupForm("phno"),
					'_label -> "Phone Number",
					'_help -> "+91 will be added",
					'_error -> "Enter the valid Phone Number",
					'_showConstraints -> false
				)),format.raw/*28.6*/("""
			<button class="btn btn-primary">Proceed</button>
		""")))}),format.raw/*30.4*/("""
	</fieldset>
	"""),_display_(/*32.3*/userSignupForm/*32.17*/.globalErrors.map/*32.34*/{ error =>_display_(Seq[Any](format.raw/*32.44*/("""
		<div class="alert alert-error">"""),_display_(/*33.35*/Messages(error.message)),format.raw/*33.58*/("""</div>
	""")))}),format.raw/*34.3*/("""
""")))}),format.raw/*35.2*/("""
"""))}
    }
    
    def render(userSignupForm:Form[scala.Tuple2[String, String]],flash:play.api.mvc.Flash): play.api.templates.HtmlFormat.Appendable = apply(userSignupForm)(flash)
    
    def f:((Form[scala.Tuple2[String, String]]) => (play.api.mvc.Flash) => play.api.templates.HtmlFormat.Appendable) = (userSignupForm) => (flash) => apply(userSignupForm)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jun 30 14:48:21 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/userSignup.scala.html
                    HASH: 6fdfeb9ab3b830ce2114c95ffc7ef441abe53f97
                    MATRIX: 660->32|911->108|939->192|966->194|998->218|1036->219|1109->266|1122->271|1150->290|1196->298|1261->336|1285->339|1325->349|1355->353|1405->394|1444->395|1476->401|1685->590|1717->596|1930->789|2016->845|2058->861|2081->875|2107->892|2155->902|2217->937|2261->960|2300->969|2332->971
                    LINES: 19->2|27->2|29->7|30->8|30->8|30->8|33->11|33->11|33->11|33->11|34->12|34->12|35->13|36->14|36->14|36->14|37->15|43->21|44->22|50->28|52->30|54->32|54->32|54->32|54->32|55->33|55->33|56->34|57->35
                    -- GENERATED --
                */
            