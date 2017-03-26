package qa.sandbox.test.web.store.pages

import qa.sandbox.commons.web.model.{Component, Locator, Page}
import qa.sandbox.test.web.store.elements.{Button, Table}
import qa.sandbox.test.web.store.model.PersonTableEntry

class ComponentPage extends Page {

  def firstContainer = new Component(Locator.id("firstContainer")) {
    def insideButton = new Button(Locator.name("btn"))
  }

  def secondContainer = new Component(Locator.id("secondContainer")) {
    def insideButton = new Button(Locator.name("btn"))
  }

  def table = new Table(Locator.id("table"), classOf[PersonTableEntry])


}
