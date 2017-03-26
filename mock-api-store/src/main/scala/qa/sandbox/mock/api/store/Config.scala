package qa.sandbox.mock.api.store

import com.typesafe.config.ConfigFactory

object Config {

  private lazy val config = ConfigFactory.load()

  val interface: String = config.getString("interface")
  val port: Int = config.getInt("port")

}
