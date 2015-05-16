
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
/* adminLogin Template File */
object adminLogin extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[AdminFormData],play.api.templates.HtmlFormat.Appendable] {

    /* adminLogin Template File */
    def apply/*2.2*/(adminForm: Form[AdminFormData]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import helper.twitterBootstrap._

import play.api.i18n.Messages


Seq[Any](format.raw/*2.34*/("""
"""),format.raw/*6.1*/("""
"""),_display_(/*7.2*/main("Admin")/*7.15*/{_display_(Seq[Any](format.raw/*7.16*/("""
	<fieldset>
		<legend>Admin Login</legend>
			"""),_display_(/*10.5*/form(action = routes.Auth.adminLoginPost())/*10.48*/{_display_(Seq[Any](format.raw/*10.49*/("""
				"""),_display_(/*11.6*/inputText(
					adminForm("email"),
					'_label -> "Email",
					'_showConstraints -> false
				)),format.raw/*15.6*/("""
				"""),_display_(/*16.6*/inputPassword(
					adminForm("password"),
					'_label -> "Password",
					'_showConstraints -> false
				)),format.raw/*20.6*/("""
			<button class="btn btn-primary">Proceed</button>
			""")))}),format.raw/*22.5*/("""
	</fieldset>
	"""),_display_(/*24.3*/adminForm/*24.12*/.globalErrors.map/*24.29*/{ error =>_display_(Seq[Any](format.raw/*24.39*/("""
		<div class="alert alert-error">"""),_display_(/*25.35*/Messages(error.message)),format.raw/*25.58*/("""</div>
	""")))}),format.raw/*26.3*/("""
""")))}),format.raw/*27.2*/("""
"""))}
    }
    
    def render(adminForm:Form[AdminFormData]): play.api.templates.HtmlFormat.Appendable = apply(adminForm)
    
    def f:((Form[AdminFormData]) => play.api.templates.HtmlFormat.Appendable) = (adminForm) => apply(adminForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 01 01:00:33 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/adminLogin.scala.html
                    HASH: b8527297f3e1565a921ed67d97fd401ec0af4fe2
                    MATRIX: 626->32|833->64|860->147|887->149|908->162|946->163|1020->211|1072->254|1111->255|1143->261|1261->359|1293->365|1421->473|1508->530|1550->546|1568->555|1594->572|1642->582|1704->617|1748->640|1787->649|1819->651
                    LINES: 19->2|27->2|28->6|29->7|29->7|29->7|32->10|32->10|32->10|33->11|37->15|38->16|42->20|44->22|46->24|46->24|46->24|46->24|47->25|47->25|48->26|49->27
                    -- GENERATED --
                */
            