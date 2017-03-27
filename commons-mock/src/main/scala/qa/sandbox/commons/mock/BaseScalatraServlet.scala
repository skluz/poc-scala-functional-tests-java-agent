package qa.sandbox.commons.mock

import com.typesafe.scalalogging.LazyLogging
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.metrics.MetricsSupport

/**
  * Created by slawek on 27.03.2017.
  */
abstract class BaseScalatraServlet extends ScalatraServlet with MetricsSupport with LazyLogging with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  val context = "/"

  before() {
    val ipAddress = if (request.getHeader("X-FORWARDED-FOR") == null) {
      request.getRemoteAddr()
    } else {
      request.getHeader("X-FORWARDED-FOR")
    }
    val requestURL = request.getRequestURL
    if (request.getQueryString != null) requestURL.append("?").append(request.getQueryString)
    val completeURL = requestURL.toString
    logger.info(s"[$ipAddress] ${completeURL}")
    counter(request.getQueryString) += 1
    contentType = formats("json")
  }

}
