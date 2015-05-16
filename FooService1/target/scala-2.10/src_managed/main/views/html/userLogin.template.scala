
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
/* userLogin Template File */
object userLogin extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[UserFormData],play.api.mvc.Flash,play.api.templates.HtmlFormat.Appendable] {

    /* userLogin Template File */
    def apply/*2.2*/(userForm: Form[UserFormData])(implicit flash: play.api.mvc.Flash):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import helper.twitterBootstrap._

import play.api.i18n.Messages


Seq[Any](format.raw/*2.68*/("""
"""),format.raw/*6.1*/("""
"""),_display_(/*7.2*/main("User")/*7.14*/{_display_(Seq[Any](format.raw/*7.15*/("""
	<fieldset>
		<legend>User Login</legend>
		"""),_display_(/*10.4*/flash/*10.9*/.get("success").map/*10.28*/{ msg =>_display_(Seq[Any](format.raw/*10.36*/("""
			<div class="alert alert-success">"""),_display_(/*11.38*/msg),format.raw/*11.41*/("""</div>
		""")))}),format.raw/*12.4*/("""
			"""),_display_(/*13.5*/form(action=routes.Auth.userLoginPost())/*13.45*/{_display_(Seq[Any](format.raw/*13.46*/("""
				"""),_display_(/*14.6*/inputText(
					userForm("email"),
					'_label -> "Email",
					'_help -> "enter valid email address",
					'_error -> "Enter the valid email",
					'_showConstraints -> false
				)),format.raw/*20.6*/("""
			<button class="btn btn-primary">Proceed</button>
			""")))}),format.raw/*22.5*/("""
	</fieldset>	
	"""),_display_(/*24.3*/userForm/*24.11*/.globalErrors.map/*24.28*/{ error =>_display_(Seq[Any](format.raw/*24.38*/("""
		<div class="alert alert-error">"""),_display_(/*25.35*/Messages(error.message)),format.raw/*25.58*/("""</div>
	""")))}),format.raw/*26.3*/("""
""")))}),format.raw/*27.2*/("""
"""))}
    }
    
    def render(userForm:Form[UserFormData],flash:play.api.mvc.Flash): play.api.templates.HtmlFormat.Appendable = apply(userForm)(flash)
    
    def f:((Form[UserFormData]) => (play.api.mvc.Flash) => play.api.templates.HtmlFormat.Appendable) = (userForm) => (flash) => apply(userForm)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jun 30 14:48:21 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/userLogin.scala.html
                    HASH: 181c246ee211e4cfda6ae6fe534165be125daa45
                    MATRIX: 641->31|882->97|909->180|936->182|956->194|994->195|1066->241|1079->246|1107->265|1153->273|1218->311|1242->314|1282->324|1313->329|1362->369|1401->370|1433->376|1635->558|1722->615|1765->632|1782->640|1808->657|1856->667|1918->702|1962->725|2001->734|2033->736
                    LINES: 19->2|27->2|28->6|29->7|29->7|29->7|32->10|32->10|32->10|32->10|33->11|33->11|34->12|35->13|35->13|35->13|36->14|42->20|44->22|46->24|46->24|46->24|46->24|47->25|47->25|48->26|49->27
                    -- GENERATED --
                */
            