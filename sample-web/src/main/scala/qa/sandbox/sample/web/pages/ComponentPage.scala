package qa.sandbox.sample.web.pages

import qa.sandbox.commons.web.model.{Component, Locator, Page}
import qa.sandbox.sample.web.elements.{Button, Table}
import qa.sandbox.sample.web.model.PersonTableEntry
import sandbox.commons.web.model.{Component, Element, Page}
import sandbox.tests.model.Person
import sandbox.tests.web.elements.{Button, Table}

class ComponentPage extends Page {

  def firstContainer = new Component(Locator.id("firstContainer")) {
    def insideButton = new Button(Locator.name("btn"))
  }

  def secondContainer = new Component(Locator.id("secondContainer")) {
    def insideButton = new Button(Locator.name("btn"))
  }

  def table = new Table(Locator.id("table"), classOf[PersonTableEntry])


}
