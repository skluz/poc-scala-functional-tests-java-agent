package qa.sandbox.commons.web.config

import com.typesafe.config.ConfigFactory

object Config {

  private lazy val config = ConfigFactory.load()

  val browser: String = config.getString("browser")

  object local {

    private val _local = config.getConfig("local")

    object chrome {
      val driverPath: String = _local.getConfig("chrome").getString("webdriver.chrome.driver")
    }

    object firefox {
      val driverPath: String = _local.getConfig("firefox").getString("webdriver.gecko.driver")
    }

  }

}
