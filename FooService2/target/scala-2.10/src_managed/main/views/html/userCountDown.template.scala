
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
/* userCountDown Template File */
object userCountDown extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[scala.Tuple2[Option[String], String],RequestHeader,play.api.templates.HtmlFormat.Appendable] {

    /* userCountDown Template File */
    def apply/*2.2*/(phnos: (Option[String], String))(implicit request: RequestHeader):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*2.68*/("""

"""),_display_(/*4.2*/main("Call")/*4.14*/{_display_(Seq[Any](format.raw/*4.15*/("""
	<legend>Missed Call Panel</legend>
	<center>
		
		<div class="alert alert-success">
			"""),_display_(/*9.5*/phnos/*9.10*/._1/*9.13*/ match/*9.19*/{/*10.5*/case Some(number) =>/*10.25*/ {_display_(Seq[Any](format.raw/*10.27*/("""
					<h1>Call """),_display_(/*11.16*/number),format.raw/*11.22*/("""</h1>	
				""")))}/*13.5*/case None =>/*13.17*/ {_display_(Seq[Any](format.raw/*13.19*/("""
					<h1>Serious error ocurred. try to reload !!!</h1>	
				""")))}}),format.raw/*16.5*/("""
			
		</div>
		
		<div class="well">
			<h1 id="start"></h1>
		</div>
		
	</center>
	<script type="text/javascript">
	
		$(document).ready(function() """),format.raw/*27.32*/("""{"""),format.raw/*27.33*/("""
			var count = 120;
		   	setTimeout(function() """),format.raw/*29.29*/("""{"""),format.raw/*29.30*/(""" count = 0; window.location = """"),_display_(/*29.62*/routes/*29.68*/.Application.redirect()),format.raw/*29.91*/(""""; """),format.raw/*29.94*/("""}"""),format.raw/*29.95*/(""",  60 * 2 * 1000);
		   		window.setInterval(function()"""),format.raw/*30.37*/("""{"""),format.raw/*30.38*/("""
			   		count = count - 1;
			   		$("#start").text(count)
			   		if(count <= 0) """),format.raw/*33.24*/("""{"""),format.raw/*33.25*/("""$("#start").text("Refresh to try again"); return;"""),format.raw/*33.74*/("""}"""),format.raw/*33.75*/("""
			   	"""),format.raw/*34.8*/("""}"""),format.raw/*34.9*/(""", 1000)
		    """),format.raw/*35.7*/("""}"""),format.raw/*35.8*/(""");

	    ws = new WebSocket(""""),_display_(/*37.27*/routes/*37.33*/.Application.callTrigger(phnos._2).webSocketURL()),format.raw/*37.82*/("""")
	    ws.onmessage = function(msg)"""),format.raw/*38.34*/("""{"""),format.raw/*38.35*/("""
	    	window.location = """"),_display_(/*39.27*/routes/*39.33*/.Application.redirect()),format.raw/*39.56*/("""";
	    """),format.raw/*40.6*/("""}"""),format.raw/*40.7*/("""
		
	</script>
""")))}),format.raw/*43.2*/("""
"""))}
    }
    
    def render(phnos:scala.Tuple2[Option[String], String],request:RequestHeader): play.api.templates.HtmlFormat.Appendable = apply(phnos)(request)
    
    def f:((scala.Tuple2[Option[String], String]) => (RequestHeader) => play.api.templates.HtmlFormat.Appendable) = (phnos) => (request) => apply(phnos)(request)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 01 01:00:33 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/userCountDown.scala.html
                    HASH: da80830e51fbadfd2b147b6541fec72124258317
                    MATRIX: 666->35|826->101|854->104|874->116|912->117|1027->207|1040->212|1051->215|1065->221|1074->227|1103->247|1143->249|1186->265|1213->271|1243->288|1264->300|1304->302|1397->369|1576->520|1605->521|1682->570|1711->571|1770->603|1785->609|1829->632|1860->635|1889->636|1972->691|2001->692|2112->775|2141->776|2218->825|2247->826|2282->834|2310->835|2351->849|2379->850|2436->880|2451->886|2521->935|2585->971|2614->972|2668->999|2683->1005|2727->1028|2762->1036|2790->1037|2836->1053
                    LINES: 19->2|22->2|24->4|24->4|24->4|29->9|29->9|29->9|29->9|29->10|29->10|29->10|30->11|30->11|31->13|31->13|31->13|33->16|44->27|44->27|46->29|46->29|46->29|46->29|46->29|46->29|46->29|47->30|47->30|50->33|50->33|50->33|50->33|51->34|51->34|52->35|52->35|54->37|54->37|54->37|55->38|55->38|56->39|56->39|56->39|57->40|57->40|60->43
                    -- GENERATED --
                */
            