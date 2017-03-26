package qa.sandbox.test.web.store

import qa.sandbox.commons.web.BaseWebSuite
import qa.sandbox.test.web.store.application.SampleWebApplication

class WaitUntilTest extends BaseWebSuite {

  it should "wait for elements being visible" in {
    val mainPage = SampleWebApplication.openLoaderPage()
    mainPage.performOperation()
  }

}
