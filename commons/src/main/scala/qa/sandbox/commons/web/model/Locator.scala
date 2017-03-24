package qa.sandbox.commons.web.model

import org.openqa.selenium.By

class Locator(val by: By)

object Locator {
  def id(id: String): Locator = new Locator(By.id(id))
  def name(name: String): Locator = new Locator(By.name(name))
  def xpath(xpath: String): Locator = new Locator(By.xpath(xpath))
}