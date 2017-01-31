package qa.sandbox.mock.rest.config

import com.typesafe.config.ConfigFactory

object Config {

  private lazy val config = ConfigFactory.load()

  val interface: String = config.getString("interface")
  val port: Int = config.getInt("port")

}
