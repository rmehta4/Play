
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*8.2*/import helper._


Seq[Any](format.raw/*7.32*/("""
"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*12.62*/("""
        """),format.raw/*13.9*/("""<title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*14.54*/routes/*14.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*14.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.59*/routes/*15.65*/.Assets.versioned("images/favicon.png")),format.raw/*15.104*/("""">
    </head>
    <body>
        """),format.raw/*19.32*/("""
        """),_display_(/*20.10*/content),format.raw/*20.17*/("""
        """),format.raw/*21.9*/("""<form method="post" action=""""),_display_(/*21.38*/routes/*21.44*/.HomeController.handleupdates()),format.raw/*21.75*/("""">
 	 <input type="text" name="username" /><br/>
  	<input type="text" name="timestamp" /><br/>
 	<input type="text" name="latitude" /><br/>
  	<input type="text" name="longitude" /><br/>

  	<input type="submit" value="Login" />
	</form>
        <script src=""""),_display_(/*29.23*/routes/*29.29*/.Assets.versioned("javascripts/main.js")),format.raw/*29.69*/("""" type="text/javascript"></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Aug 26 23:25:57 EDT 2017
                  SOURCE: /media/rutvij/Projects/sbt/play-java-seed/app/views/main.scala.html
                  HASH: 0a9272e91aa8531b4ee7de30411e29170ed9c36e
                  MATRIX: 1206->260|1309->292|1354->290|1381->308|1461->413|1497->422|1532->430|1558->435|1647->497|1662->503|1725->544|1813->605|1828->611|1889->650|1951->774|1988->784|2016->791|2052->800|2108->829|2123->835|2175->866|2463->1127|2478->1133|2539->1173
                  LINES: 33->7|36->8|39->7|40->9|43->12|44->13|44->13|44->13|45->14|45->14|45->14|46->15|46->15|46->15|49->19|50->20|50->20|51->21|51->21|51->21|51->21|59->29|59->29|59->29
                  -- GENERATED --
              */
          