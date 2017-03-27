package qa.sandbox.mock.api.store

import org.json4s.ext.JodaTimeSerializers
import org.json4s.{DefaultFormats, Formats}
import qa.sandbox.commons.mock.BaseScalatraServlet

/**
  * Created by slawek on 25.03.2017.
  */
class StoreServlet(storeState: StoreState) extends BaseScalatraServlet {

  get("/products") {
    storeState.products
  }

}
