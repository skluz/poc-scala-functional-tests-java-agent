package qa.sandbox.mock.api.store

import org.scalatra.ScalatraServlet

/**
  * Created by slawek on 25.03.2017.
  */
class StoreServlet extends ScalatraServlet {

  get("/") {
    "hello!"
  }

}
