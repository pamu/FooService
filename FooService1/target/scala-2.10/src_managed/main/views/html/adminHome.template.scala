
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
	<div class="well">
		<div id="messages"></div>
	</div>
	
	<script type="text/javascript">
		$(function()"""),format.raw/*16.15*/("""{"""),format.raw/*16.16*/("""
			var ws = new WebSocket(""""),_display_(/*17.29*/routes/*17.35*/.Application.events().webSocketURL()),format.raw/*17.71*/("""");
			alert("loaded")
			ws.onmessage = function(msg)"""),format.raw/*19.32*/("""{"""),format.raw/*19.33*/("""
				$('<h3 />').text(msg.data).appendTo('#messages');
			"""),format.raw/*21.4*/("""}"""),format.raw/*21.5*/("""
		"""),format.raw/*22.3*/("""}"""),format.raw/*22.4*/("""
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
                    DATE: Mon Jun 30 14:48:21 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/adminHome.scala.html
                    HASH: af094ff3c8a16a527106648b6bf47b00cdac9dd7
                    MATRIX: 617->31|746->66|773->68|799->86|837->87|1169->391|1198->392|1254->421|1269->427|1326->463|1408->517|1437->518|1522->576|1550->577|1580->580|1608->581
                    LINES: 19->2|22->2|23->3|23->3|23->3|36->16|36->16|37->17|37->17|37->17|39->19|39->19|41->21|41->21|42->22|42->22
                    -- GENERATED --
                */
            