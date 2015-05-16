
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
/* removeDevice Template File */
object removeDevice extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[RemoveDeviceFormData],play.api.mvc.Flash,play.api.templates.HtmlFormat.Appendable] {

    /* removeDevice Template File */
    def apply/*2.2*/(removeDeviceForm: Form[RemoveDeviceFormData])(implicit flash: play.api.mvc.Flash):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import helper.twitterBootstrap._

import play.api.i18n.Messages


Seq[Any](format.raw/*2.84*/("""

"""),format.raw/*7.1*/("""
"""),_display_(/*8.2*/main("Remove Device")/*8.23*/{_display_(Seq[Any](format.raw/*8.24*/("""
	<fieldset>
		<legend>Remove Device. <a href="/addDevice">Add Device</a></legend>
		"""),_display_(/*11.4*/flash/*11.9*/.get("removeSuccess").map/*11.34*/{ _ =>_display_(Seq[Any](format.raw/*11.40*/("""
			<div class="alert alert-success">Device Removed Successfully. Remove Another.</div>
		""")))}),format.raw/*13.4*/("""
		"""),_display_(/*14.4*/form(action=routes.Application.removeDevicePost())/*14.54*/{_display_(Seq[Any](format.raw/*14.55*/("""
			"""),_display_(/*15.5*/inputText(
				removeDeviceForm("phoneNumber"),
				'_label -> "Enter Phone Number",
				'_error -> "Enter valid phone number",
				'_showConstraints -> false
			)),format.raw/*20.5*/("""
			<button class="btn btn-primary">Remove Device</button>
		""")))}),format.raw/*22.4*/("""
	</fieldset>
	"""),_display_(/*24.3*/removeDeviceForm/*24.19*/.globalErrors.map/*24.36*/{ error =>_display_(Seq[Any](format.raw/*24.46*/("""
		<div class="alert alert-error">"""),_display_(/*25.35*/Messages(error.message)),format.raw/*25.58*/("""</div>
	""")))}),format.raw/*26.3*/("""
""")))}),format.raw/*27.2*/("""
"""))}
    }
    
    def render(removeDeviceForm:Form[RemoveDeviceFormData],flash:play.api.mvc.Flash): play.api.templates.HtmlFormat.Appendable = apply(removeDeviceForm)(flash)
    
    def f:((Form[RemoveDeviceFormData]) => (play.api.mvc.Flash) => play.api.templates.HtmlFormat.Appendable) = (removeDeviceForm) => (flash) => apply(removeDeviceForm)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 01 01:00:33 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/removeDevice.scala.html
                    HASH: a1b326ee5ca755bab536f459cd0469d29ae4f50c
                    MATRIX: 658->34|915->116|943->200|970->202|999->223|1037->224|1149->310|1162->315|1196->340|1240->346|1361->437|1391->441|1450->491|1489->492|1520->497|1703->660|1795->722|1837->738|1862->754|1888->771|1936->781|1998->816|2042->839|2081->848|2113->850
                    LINES: 19->2|27->2|29->7|30->8|30->8|30->8|33->11|33->11|33->11|33->11|35->13|36->14|36->14|36->14|37->15|42->20|44->22|46->24|46->24|46->24|46->24|47->25|47->25|48->26|49->27
                    -- GENERATED --
                */
            