package qa.sandbox.test.web.store

import qa.sandbox.commons.web.BaseWebSuite
import qa.sandbox.test.web.store.application.SampleWebApplication


class ComponentTest extends BaseWebSuite {

  it should "wait for elements being visible" in {
    val mainPage = SampleWebApplication.openComponentPage()
    mainPage.firstContainer.insideButton.text() shouldBe "First"
    mainPage.secondContainer.insideButton.text() shouldBe "Second"

    logger.info(mainPage.table.hello())
  }

}
