
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

object main_Scope0 {
  import models._
  import controllers._
  import play.api.i18n._
  import views.html._
  import play.api.templates.PlayMagic._
  import play.api.mvc._
  import play.api.data._

  class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable, Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String, Html, play.twirl.api.HtmlFormat.Appendable] {

    /**/
    def apply /*2.2*/ (title: String, content: Html): play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
        {

          Seq[Any](format.raw /*2.32*/ ("""

"""), format.raw /*4.1*/ ("""<!DOCTYPE html>
<html lang="en">
    <head>
        <title>"""), _display_( /*7.17*/ title), format.raw /*7.22*/ ("""</title>
        <link rel="stylesheet" media="screen" href=""""), _display_( /*8.54*/ routes /*8.60*/ .Assets.versioned("stylesheets/bootstrap.css")), format.raw /*8.106*/ ("""">
        <link rel="stylesheet" href=""""), _display_( /*9.39*/ routes /*9.45*/ .Assets.versioned("font-awesome-4.6.3/css/font-awesome.min.css")), format.raw /*9.109*/ ("""">
    </head>
    <body>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js"></script>
        """), _display_( /*14.10*/ content), format.raw /*14.17*/ ("""
        """), _display_( /*15.10*/ playscalajs /*15.21*/ .html.scripts("client")), format.raw /*15.44*/ ("""
    """), format.raw /*16.5*/ ("""</body>
</html>
"""))
        }
      }
    }

    def render(title: String, content: Html): play.twirl.api.HtmlFormat.Appendable = apply(title, content)

    def f: ((String, Html) => play.twirl.api.HtmlFormat.Appendable) = (title, content) => apply(title, content)

    def ref: this.type = this

  }

}

/**/
object main extends main_Scope0.main
/*
                  -- GENERATED --
                  DATE: Mon Aug 15 18:00:58 EEST 2016
                  SOURCE: /home/elias/workspace/Weather Report - scalajs /server/app/views/main.scala.html
                  HASH: dc984a4f6108a1a0a73424f56127ff97f83a98d5
                  MATRIX: 530->2|655->32|683->34|769->94|794->99|882->161|896->167|963->213|1030->254|1044->260|1129->324|1358->526|1386->533|1423->543|1443->554|1487->577|1519->582
                  LINES: 20->2|25->2|27->4|30->7|30->7|31->8|31->8|31->8|32->9|32->9|32->9|37->14|37->14|38->15|38->15|38->15|39->16
                  -- GENERATED --
              */
