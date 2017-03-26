package qa.sandbox.mock.api.store

import javax.servlet.ServletContext

import com.typesafe.scalalogging.LazyLogging
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.LifeCycle
import org.scalatra.servlet.ScalatraListener

class StoreServer(storeState: StoreState) extends LazyLogging {

  private val server = new Server(0)

  def start(): Unit = {
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.setInitParameter(ScalatraListener.LifeCycleKey, "qa.sandbox.mock.api.store.ScalatraBootstrap")
    context.addEventListener(new ScalatraListener)
    server.setHandler(context)
    server.start()
    server.join()
  }

  def stop(): Unit = {

  }


}

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new StoreServlet, "/*")
  }
}