package qa.sandbox.test.web.store.config

import com.typesafe.config.ConfigFactory

object Config {

  private val config = ConfigFactory.load()
  val environment = config.getString("environment")
  private val env = config.getConfig(environment)

  object web {
    private val web = env.getConfig("web")
    lazy val url = web.getString("url")
  }

}
