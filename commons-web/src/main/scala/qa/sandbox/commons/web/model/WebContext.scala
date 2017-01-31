package qa.sandbox.commons.web.model

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, SearchContext, WebDriver}
import qa.sandbox.commons.web.config.DriverFactory
import qa.sandbox.commons.web.logger.Loggable

trait WebContext extends LazyLogging {

  private def waitTimeout = 5

  protected def driver: WebDriver = DriverFactory.getInstance()
  protected implicit def context: SearchContext = driver

  @Loggable
  protected def waitUntilElementInvisible(element: Element) = {
    new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.invisibilityOfElementLocated(element.locator.by))
  }

  @Loggable
  protected def waitUntilElementVisible(element: Element) = {
    new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.visibilityOfElementLocated(element.locator.by))
  }

}
