package qa.sandbox.mock.web.server

import java.io.File

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.LogEntry
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import qa.sandbox.mock.web.config.Config

object WebMockServer extends App with LazyLogging {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  lazy val baseDirectory = new File(Config.staticsDirectory).getCanonicalFile.getAbsolutePath

  def requestMethodAsInfo(req: HttpRequest): LogEntry = LogEntry(s"${req.method.name()} ${req.uri.toString()}", Logging.InfoLevel)

  val route =
    pathPrefix("static") {
      logRequest(requestMethodAsInfo _) {
        getFromDirectory(baseDirectory)
        //getFromResourceDirectory("static/")
      }
    }

  val bindingFuture = Http().bindAndHandle(route, Config.interface, Config.port)

  bindingFuture.foreach(i => logger.info("Successfully bound to {}", i.localAddress))

}
