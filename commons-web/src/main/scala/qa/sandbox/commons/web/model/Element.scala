package qa.sandbox.commons.web.model

import org.openqa.selenium.{By, SearchContext, WebElement}

class Element(val locator: Locator)(implicit context: SearchContext) extends WebContext {

  protected val by: By = locator.by
  protected def element: WebElement = context.findElement(by)

  override def toString(): String = {
    "%s (%s)".format(this.getClass.getSimpleName, locator.by)
  }

}

trait X {

  def elements(locator: Locator): Seq[Element] = {null}

}
