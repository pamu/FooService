
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
/* addDevice Template File */
object addDevice extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[AddDeviceFormData],play.api.mvc.Flash,play.api.templates.HtmlFormat.Appendable] {

    /* addDevice Template File */
    def apply/*2.2*/(addDeviceForm: Form[AddDeviceFormData])(implicit flash: play.api.mvc.Flash):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

import helper.twitterBootstrap._

import play.api.i18n.Messages


Seq[Any](format.raw/*2.78*/("""

"""),format.raw/*7.1*/("""
"""),_display_(/*8.2*/main("Add Device")/*8.20*/{_display_(Seq[Any](format.raw/*8.21*/("""
	<fieldset>
		<legend>Add Device. <a href="/removeDevice">Remove Device</a></legend>
		"""),_display_(/*11.4*/flash/*11.9*/.get("addSuccess").map/*11.31*/{ _ =>_display_(Seq[Any](format.raw/*11.37*/("""
			<div class="alert alert-success">Device Added Successfully. Add Another.</div>
		""")))}),format.raw/*13.4*/("""
		"""),_display_(/*14.4*/form(action = routes.Application.addDevicePost())/*14.53*/{_display_(Seq[Any](format.raw/*14.54*/("""
			"""),_display_(/*15.5*/inputText(
				addDeviceForm("simId"),
				'_label -> "SIM ID",
				'_showConstraints -> false
			)),format.raw/*19.5*/("""
			"""),_display_(/*20.5*/inputText(
				addDeviceForm("phoneNumber"),
				'_label -> "PHONE NUMBER",
				'_showConstraints -> false
			)),format.raw/*24.5*/("""
			<button class="btn btn-primary">Add Device</button>
		""")))}),format.raw/*26.4*/("""
	</fieldset>
	"""),_display_(/*28.3*/addDeviceForm/*28.16*/.globalErrors.map/*28.33*/{ error =>_display_(Seq[Any](format.raw/*28.43*/("""
		<div class="alert alert-error">"""),_display_(/*29.35*/Messages(error.message)),format.raw/*29.58*/("""</div>
	""")))}),format.raw/*30.3*/("""
""")))}),format.raw/*31.2*/("""
"""))}
    }
    
    def render(addDeviceForm:Form[AddDeviceFormData],flash:play.api.mvc.Flash): play.api.templates.HtmlFormat.Appendable = apply(addDeviceForm)(flash)
    
    def f:((Form[AddDeviceFormData]) => (play.api.mvc.Flash) => play.api.templates.HtmlFormat.Appendable) = (addDeviceForm) => (flash) => apply(addDeviceForm)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 01 01:00:33 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/addDevice.scala.html
                    HASH: bd45be268103cf55fbff4715ae44f8e9f8070d92
                    MATRIX: 646->31|897->107|925->191|952->193|978->211|1016->212|1131->301|1144->306|1175->328|1219->334|1335->420|1365->424|1423->473|1462->474|1493->479|1612->578|1643->583|1774->694|1863->753|1905->769|1927->782|1953->799|2001->809|2063->844|2107->867|2146->876|2178->878
                    LINES: 19->2|27->2|29->7|30->8|30->8|30->8|33->11|33->11|33->11|33->11|35->13|36->14|36->14|36->14|37->15|41->19|42->20|46->24|48->26|50->28|50->28|50->28|50->28|51->29|51->29|52->30|53->31
                    -- GENERATED --
                */
            