package qa.sandbox.commons.web.config

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.SafariDriver

object DriverFactory extends LazyLogging {

  private var threadDriver: ThreadLocal[WebDriver] = _

  def getInstance(): WebDriver = {
    if(threadDriver == null) {
      threadDriver = new ThreadLocal[WebDriver]()
      val webDriver = Config.browser match {
        case "local.chrome" => localChromeDriver()
        case "local.safari" => localSafariDriver()
        case "local.firefox" => localFirefoxDriver()
        case unsupported => throw new UnsupportedOperationException(s"Driver ${unsupported} not implemented")
      }
      threadDriver.set(webDriver)
    }
    threadDriver.get()
  }

  private def localFirefoxDriver(): WebDriver = {
    System.setProperty("webdriver.gecko.driver", Config.local.firefox.driverPath)
    new FirefoxDriver()
  }

  private def localSafariDriver(): WebDriver = {
    new SafariDriver()
  }

  private def localChromeDriver(): WebDriver = {
    System.setProperty("webdriver.chrome.driver", Config.local.chrome.driverPath)
    val options = new ChromeOptions()
    options.addArguments("start-maximized")
    new ChromeDriver(options)
  }

  def dispose() = {
    if (threadDriver.get() != null) {
      threadDriver.get().quit()
      threadDriver.set(null)
      threadDriver = null
    }
  }

}
