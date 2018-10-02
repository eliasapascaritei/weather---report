package client

import org.scalajs.dom
import org.scalajs.dom.{ XMLHttpRequest, document }
import org.scalajs.jquery.{ JQuery, jQuery, JQueryAjaxSettings, JQueryXHR }
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{ global => g, literal => lit, newInstance => jsnew }
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.{ Array, Date, JSON }
import scalacss.Defaults._
import scalacss.ScalatagsCss._
import scalatags.Text._
import scalatags.Text.all._

trait DataGenerator {

  def initialize(lat: Double, long: Double) = {
    val map_canvas = document.getElementById("map_canvas")
    val map_options = lit(center = (jsnew(g.google.maps.LatLng))(lat, long), zoom = 6, mapTypeId = g.google.maps.MapTypeId.TERRAIN)
    val gogleMap = (jsnew(g.google.maps.Map))(map_canvas, map_options)
    val marker = (jsnew(g.google.maps.Marker))(lit(map = gogleMap, position = (jsnew(g.google.maps.LatLng)(lat, long))))
  }

  def getTime(time: Long): String = {

    val date = new Date(time * 1000);

    date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()
  }
}

class WeatherReport extends DataGenerator {

  @JSExport
  def showReport(): Unit = {
    val renderHtml = new WeatherFrag(scalatags.Text)
    dom.document.body.innerHTML = renderHtml.htmlFrag.render
  }

  @JSExport
  def showDetail() {
    cleanUI

    val name = jQuery("#name").value()
    jQuery.ajax(js.Dynamic.literal(
      `type` = "GET",
      url = "/weather/" + name,
      success = { (data: String, textStatus: String, jqXHR: JQueryXHR) =>
      populateWeatherReprt(data)
    }
    ).asInstanceOf[JQueryAjaxSettings])
  }

  private def cleanUI: JQuery = {
    jQuery("#cityName").empty()
    jQuery("#weather").empty()
    jQuery("#pressure").empty()
    jQuery("#humidity").empty()
    jQuery("#sunrise").empty()
    jQuery("#sunset").empty()
    jQuery("#geocoords").empty()
    jQuery("#temp").empty()
    jQuery("#alt").empty()
  }

  private def populateWeatherReprt(data: String) = {
    val result = JSON.parse(data)
    val weather = result.weather.asInstanceOf[Array[js.Dynamic]](0)
    jQuery("#tempDetail").attr("style", "display:block;")
    jQuery("#cityName").append(s"${result.name}, ${result.sys.country}")
    val image = s"http://openweathermap.org/img/w/${weather.icon}.png"
    jQuery("#temp").append(s"<img src=$image>${Math.floor(result.main.temp.toString.toDouble - 273.15)}")
    jQuery("#weather").append(s"${weather.main}")
    jQuery("#pressure").append(s"${result.main.pressure} hpa")
    jQuery("#humidity").append(s"${result.main.humidity} %")
    jQuery("#sunrise").append(getTime(result.sys.sunrise.toString.toLong))
    jQuery("#sunset").append(getTime(result.sys.sunset.toString.toLong))
    jQuery("#geocoords").append(s"long:  ${result.coord.lon}<br>lat:       ${result.coord.lat}")
    jQuery("#alt").append(result.main.sea_level)
    initialize(result.coord.lat.toString.toDouble, result.coord.lon.toString.toDouble)
  }
}

@JSExport
object Weather extends WeatherReport with js.JSApp {
  @JSExport
  def main(): Unit = showReport
}

class WeatherFrag[Builder, Output <: FragT, FragT](val bundle: scalatags.generic.Bundle[Builder, Output, FragT]) {

  val htmlFrag = html(
    ReportStyles.render[TypedTag[String]],
    body(
      div(
        ReportStyles.mainDiv,
        h1(
          ReportStyles.heading,
          img(ReportStyles.firstImg, src := "/assets/images/google.jpg"),
          span(ReportStyles.firstSpan, "  Weather Report "),
          img(ReportStyles.secondImg, src := "/assets/images/google.jpg")
        )
      ),
      div(
        ReportStyles.secondDiv, id := "search",
        input(
          ReportStyles.search,
          id := "name", name := "name", placeholder := "Oras",
          `type` := "text", value := "Suceava", size := 15
        ),
        button(
          ReportStyles.searchIcon, "  ",
          ReportStyles.bootstrapButton,
          `type` := "button", name := "submit", id := "submit",
          onclick := "client.Weather().showDetail();", "Search"
        )
      ),
      div(ReportStyles.mainContainer, id := "tempDetail",
        div(
          div(
            `class` := "col-md-6",
            div(ReportStyles.mapCanvas, id := "map_canvas")
          ),
          div(
            `class` := "col-md-6",
            div(
              ReportStyles.innerDiv,
              div(ReportStyles.city, id := "cityName"),
              table(
                ReportStyles.table,
                tr(
                  td(
                    ReportStyles.firstTd,
                    div(id := "temp")
                  ),
                  td(
                    ReportStyles.secondTd,
                    div(id := "weather")
                  )
                ),
                tr(
                  td(ReportStyles.td, "Presiune"),
                  td(id := "pressure")
                ),
                tr(
                  td(ReportStyles.td, "Umiditate"),
                  td(id := "humidity")
                ),
                tr(
                  td(ReportStyles.td, "Rasarit "),
                  td(id := "sunrise")
                ),
                tr(
                  td(ReportStyles.td, "Apus"),
                  td(id := "sunset")
                ),
                tr(
                  td(ReportStyles.td, "Coordonate GPS"),
                  td(id := "geocoords")
                ),
                tr(
                  td(ReportStyles.td, "Altitudine"),
                  td(id := "alt")
                )
              )
            )
          )

        ))
    )
  )

}