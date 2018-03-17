package controllers

import java.io.File

import akka.stream.scaladsl.{Source, StreamConverters}
import akka.util.ByteString
import models.Model.Showcase
import play.api.i18n.I18nSupport
import play.api.mvc._

class Application()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{
  import AWS.s3h

  def index = Action {
    Ok(views.html.index(null))
  }
  StreamConverters

  def displayShowcases = Action {

    Ok(
      views.html.showcases(
        AWS.objectsInBucket
          .filter(_.getKey.contains("mp3"))
          .map(summary => Showcase(summary.getKey)))
    )
  }

  def downloadShowcase(showcaseId: String) = Action {
    val x = AWS.showcaseBucket.get("Ben/Ben Parker - Showcase 051 - (Everything) When I play Live.mp3").get.content
    val dataContent: Source[ByteString, _] = StreamConverters.fromInputStream(() => x)
    Ok.chunked(dataContent)
  }
}

case class ShowcaseId(id: String)
