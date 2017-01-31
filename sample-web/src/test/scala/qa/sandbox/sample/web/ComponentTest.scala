package qa.sandbox.sample.web

import qa.sandbox.commons.web.test.BaseSuite
import qa.sandbox.sample.web.application.SampleWebApplication
import sandbox.tests.web.application.SampleWebApplication

class ComponentTest extends BaseSuite {

  it should "wait for elements being visible" in {
    val mainPage = SampleWebApplication.openComponentPage()
    mainPage.firstContainer.insideButton.text() shouldBe "First"
    mainPage.secondContainer.insideButton.text() shouldBe "Second"

    logger.info(mainPage.table.hello())
  }

}
