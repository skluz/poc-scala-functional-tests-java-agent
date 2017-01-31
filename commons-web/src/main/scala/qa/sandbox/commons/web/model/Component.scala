package qa.sandbox.commons.web.model

import org.openqa.selenium.{SearchContext, WebElement}

abstract class Component(locator: Locator) extends WebContext {

  override protected implicit def context: SearchContext = super.context.findElement(locator.by)
  protected def element: WebElement = context.asInstanceOf[WebElement]

}
