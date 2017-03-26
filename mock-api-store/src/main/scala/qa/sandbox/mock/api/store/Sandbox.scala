package qa.sandbox.mock.api.store

/**
  * Created by slawek on 25.03.2017.
  */
object Sandbox extends App {

  val one = new StoreServer(new StoreState).start()

}
