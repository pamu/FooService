
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
/* adminHome Template File */
object adminHome extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[RequestHeader,play.api.templates.HtmlFormat.Appendable] {

    /* adminHome Template File */
    def apply/*2.2*/()(implicit request: RequestHeader):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*2.37*/("""
"""),_display_(/*3.2*/main("Admin Home")/*3.20*/{_display_(Seq[Any](format.raw/*3.21*/("""
	<div class="well">
		<h1>Admin Home</h1>
		<div class="alert alert-success">
			<h3><a href="/addDevice">Add Device</a></h3>
			<h3><a href="/removeDevice">Remove Device</a></h3>
		</div>	
	</div>
	<div class="alert alert-success">
		<div id="messages"></div>
	</div>
	
	<script type="text/javascript">
		$(function()"""),format.raw/*16.15*/("""{"""),format.raw/*16.16*/("""
			var ws = new WebSocket(""""),_display_(/*17.29*/routes/*17.35*/.Application.events().webSocketURL()),format.raw/*17.71*/("""");
			ws.onmessage = function(msg)"""),format.raw/*18.32*/("""{"""),format.raw/*18.33*/("""
				$('<h4 />').text(msg.data).appendTo('#messages');
			"""),format.raw/*20.4*/("""}"""),format.raw/*20.5*/("""
		"""),format.raw/*21.3*/("""}"""),format.raw/*21.4*/("""
		)
	</script>
""")))}))}
    }
    
    def render(request:RequestHeader): play.api.templates.HtmlFormat.Appendable = apply()(request)
    
    def f:(() => (RequestHeader) => play.api.templates.HtmlFormat.Appendable) = () => (request) => apply()(request)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 01 16:19:28 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/adminHome.scala.html
                    HASH: d997d8c98d7cbb05889dd05f035d6e063a70699e
                    MATRIX: 617->31|746->66|773->68|799->86|837->87|1184->406|1213->407|1269->436|1284->442|1341->478|1404->513|1433->514|1518->572|1546->573|1576->576|1604->577
                    LINES: 19->2|22->2|23->3|23->3|23->3|36->16|36->16|37->17|37->17|37->17|38->18|38->18|40->20|40->20|41->21|41->21
                    -- GENERATED --
                */
            