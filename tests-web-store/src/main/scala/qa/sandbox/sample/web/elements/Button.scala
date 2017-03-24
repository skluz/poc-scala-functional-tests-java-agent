package qa.sandbox.sample.web.elements

import org.openqa.selenium.SearchContext
import qa.sandbox.commons.web.logger.Loggable
import qa.sandbox.commons.web.model.{Element, Locator}

class Button(locator: Locator)(implicit context: SearchContext) extends Element(locator) {

  @Loggable
  def click(): Unit = element.click()

  @Loggable
  def text(): String = element.getText()
}
