package qa.sandbox.sample.web.elements

import org.openqa.selenium.SearchContext
import qa.sandbox.commons.web.componenets.table.WebTableEntity
import qa.sandbox.commons.web.model.{Component, Locator}
import sandbox.commons.web.model.Component

class Table[T <: WebTableEntity](locator: Locator, entryType: Class[T])(implicit context: SearchContext) extends Component(locator) {

  def hello() = entryType.getName

}
