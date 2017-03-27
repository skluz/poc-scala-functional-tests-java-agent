package qa.sandbox.mock.api.store

import java.time.Instant

import qa.sandbox.commons.mock.MockServer

/**
  * Created by slawek on 25.03.2017.
  */
object Sandbox extends App {

  val storeState = new StoreState(Seq(Product("1", "coś", Instant.now())))
  val one = new MockServer(new StoreServlet(storeState)).start()

}
