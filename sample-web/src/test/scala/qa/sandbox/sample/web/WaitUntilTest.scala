package qa.sandbox.sample.web

import qa.sandbox.commons.web.test.BaseSuite
import qa.sandbox.sample.web.application.SampleWebApplication

class WaitUntilTest extends BaseSuite {

  it should "wait for elements being visible" in {
    val mainPage = SampleWebApplication.openLoaderPage()
    mainPage.performOperation()
  }

}
