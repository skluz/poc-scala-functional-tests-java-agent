package qa.sandbox.commons.mock

import java.net.InetAddress
import javax.servlet.ServletContext
import com.typesafe.scalalogging.LazyLogging
import org.eclipse.jetty.server.{Server, ServerConnector}
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.LifeCycle
import org.scalatra.metrics.MetricsBootstrap
import org.scalatra.servlet.ScalatraListener
import org.scalatra.metrics.MetricsSupportExtensions._

class MockServer(servlet: BaseScalatraServlet) extends LazyLogging {

  private val server = new Server(0)
  private var port: Int = _
  private var url: String = _

  def start(): Unit = {
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.setInitParameter(ScalatraListener.LifeCycleKey, "qa.sandbox.commons.mock.ScalatraBootstrap")
    context.setAttribute("servlet", servlet)
    context.addEventListener(new ScalatraListener)
    server.setHandler(context)
    server.start()

    val connector = server.getConnectors.apply(0).asInstanceOf[ServerConnector]
    port = connector.getLocalPort
    val addr = InetAddress.getLocalHost
    url = s"http://${addr.getHostName}:$port${servlet.context}"
    logger.info(s"Server started: $url")
    server.join()
  }

  def stop(): Unit = {
    server.stop()
  }

}

class ScalatraBootstrap extends LifeCycle with MetricsBootstrap {
  override def init(context: ServletContext) {
    context.mount(context.getAttribute("servlet").asInstanceOf[BaseScalatraServlet], "/*")
    context.mountMetricsAdminServlet("/metrics-admin")
    context.mountHealthCheckServlet("/health")
    context.mountMetricsServlet("/metrics")
    context.installInstrumentedFilter("/*")
  }
}