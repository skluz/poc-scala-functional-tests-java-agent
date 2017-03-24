package qa.sandbox.sample.web

import qa.sandbox.commons.tests.BaseWebSuite
import qa.sandbox.sample.web.application.SampleWebApplication

class WaitUntilTest extends BaseWebSuite {

  it should "wait for elements being visible" in {
    val mainPage = SampleWebApplication.openLoaderPage()
    mainPage.performOperation()
  }

}
