package qa.sandbox.mock.rest.server

import com.typesafe.scalalogging.LazyLogging
import spark.Service
import spark.Spark._

class StoreServer(storeState: StoreState) extends LazyLogging {

  get("/hello", (req, res) => {
    "Hello World"
  })

}
