package qa.sandbox.test.web.store.pages

import qa.sandbox.commons.web.logger.Loggable
import qa.sandbox.commons.web.model.{Element, Locator, Page}
import qa.sandbox.test.web.store.elements.Button

class LoaderPage extends Page {

  def loadButton = new Button(Locator.name("loadButton"))
  def loader = new Element(Locator.id("loader"))

  @Loggable
  def performOperation(): LoaderPage = {
    loadButton.click()
    waitUntilElementVisible(loader)
    waitUntilElementInvisible(loader)
    this
  }

}
