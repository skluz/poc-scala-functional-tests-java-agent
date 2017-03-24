package qa.sandbox.mock.rest

import qa.sandbox.mock.rest.server.{StoreServer, StoreState}

/**
  * Created by slawek on 25.03.2017.
  */
object Sandbox extends App {

  val one = new StoreServer(new StoreState)
  val two = new StoreServer(new StoreState)

}
