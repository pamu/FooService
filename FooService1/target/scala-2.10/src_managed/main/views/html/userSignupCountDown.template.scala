
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
/* userSignupCountDown Template File */
object userSignupCountDown extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[scala.Tuple2[Option[String], String],RequestHeader,play.api.templates.HtmlFormat.Appendable] {

    /* userSignupCountDown Template File */
    def apply/*2.2*/(phnos: (Option[String], String))(implicit request: RequestHeader):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*2.68*/("""
"""),_display_(/*3.2*/main("Call")/*3.14*/{_display_(Seq[Any](format.raw/*3.15*/("""
	<legend>Missed Call Panel</legend>
	<center>
		
		<div class="alert alert-success">
			"""),_display_(/*8.5*/phnos/*8.10*/._1/*8.13*/ match/*8.19*/{/*9.5*/case Some(number) =>/*9.25*/ {_display_(Seq[Any](format.raw/*9.27*/("""
					<h1>Call """),_display_(/*10.16*/number),format.raw/*10.22*/("""</h1>	
				""")))}/*12.5*/case None =>/*12.17*/ {_display_(Seq[Any](format.raw/*12.19*/("""
					<h1>Serious error ocurred. try to reload !!!</h1>	
				""")))}}),format.raw/*15.5*/("""
			
		</div>
		
		<div class="alert alert-success">
			<h1 id="start"></h1>
		</div>
		
	</center>
	<script type="text/javascript">
	
		$(document).ready(function() """),format.raw/*26.32*/("""{"""),format.raw/*26.33*/("""
			var count = 120;
		   	setTimeout(function() """),format.raw/*28.29*/("""{"""),format.raw/*28.30*/(""" count = 0; window.location = """"),_display_(/*28.62*/routes/*28.68*/.Auth.userSignupRedirect()),format.raw/*28.94*/(""""; """),format.raw/*28.97*/("""}"""),format.raw/*28.98*/(""",  60 * 2 * 1000);
		   		window.setInterval(function()"""),format.raw/*29.37*/("""{"""),format.raw/*29.38*/("""
			   		count = count - 1;
			   		$("#start").text(count)
			   		if(count <= 0) """),format.raw/*32.24*/("""{"""),format.raw/*32.25*/("""$("#start").text("Refresh to try again"); return;"""),format.raw/*32.74*/("""}"""),format.raw/*32.75*/("""
			   	"""),format.raw/*33.8*/("""}"""),format.raw/*33.9*/(""", 1000)
				var ws = new WebSocket(""""),_display_(/*34.30*/routes/*34.36*/.Application.callTrigger(phnos._2).webSocketURL()),format.raw/*34.85*/("""")	
	    		ws.onmessage = function(msg)"""),format.raw/*35.36*/("""{"""),format.raw/*35.37*/("""
	    			window.location = """"),_display_(/*36.29*/routes/*36.35*/.Auth.userSignupRedirect()),format.raw/*36.61*/("""";
	    		"""),format.raw/*37.8*/("""}"""),format.raw/*37.9*/("""
			"""),format.raw/*38.4*/("""}"""),format.raw/*38.5*/(""");

		
	   
		
	</script>
""")))}),format.raw/*44.2*/("""

"""))}
    }
    
    def render(phnos:scala.Tuple2[Option[String], String],request:RequestHeader): play.api.templates.HtmlFormat.Appendable = apply(phnos)(request)
    
    def f:((scala.Tuple2[Option[String], String]) => (RequestHeader) => play.api.templates.HtmlFormat.Appendable) = (phnos) => (request) => apply(phnos)(request)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jun 30 14:48:22 IST 2014
                    SOURCE: /home/nagarjuna/FooService/app/views/userSignupCountDown.scala.html
                    HASH: 622061ae243ff67a74f794c0659b5139025e5ea2
                    MATRIX: 684->41|844->107|871->109|891->121|929->122|1044->212|1057->217|1068->220|1082->226|1090->232|1118->252|1157->254|1200->270|1227->276|1257->293|1278->305|1318->307|1411->374|1605->540|1634->541|1711->590|1740->591|1799->623|1814->629|1861->655|1892->658|1921->659|2004->714|2033->715|2144->798|2173->799|2250->848|2279->849|2314->857|2342->858|2406->895|2421->901|2491->950|2558->989|2587->990|2643->1019|2658->1025|2705->1051|2742->1061|2770->1062|2801->1066|2829->1067|2886->1094
                    LINES: 19->2|22->2|23->3|23->3|23->3|28->8|28->8|28->8|28->8|28->9|28->9|28->9|29->10|29->10|30->12|30->12|30->12|32->15|43->26|43->26|45->28|45->28|45->28|45->28|45->28|45->28|45->28|46->29|46->29|49->32|49->32|49->32|49->32|50->33|50->33|51->34|51->34|51->34|52->35|52->35|53->36|53->36|53->36|54->37|54->37|55->38|55->38|61->44
                    -- GENERATED --
                */
            