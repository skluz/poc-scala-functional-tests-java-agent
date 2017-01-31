package qa.sandbox.sample.web.application

import qa.sandbox.commons.web.logger.Loggable
import qa.sandbox.commons.web.model.Application
import qa.sandbox.sample.web.config.Config.web
import qa.sandbox.sample.web.pages.{ComponentPage, LoaderPage}
import sandbox.commons.web.model.Application
import sandbox.tests.web.config.Config
import sandbox.tests.web.pages.{ComponentPage, LoaderPage}

object SampleWebApplication extends Application {

  override val url: String = web.url

  @Loggable
  override def start(): LoaderPage = {
    driver.get(url + "/index.html")
    new LoaderPage()
  }

  @Loggable
  def openLoaderPage(): LoaderPage = {
    driver.get(url + "/loader.html")
    new LoaderPage()
  }

  @Loggable
  def openComponentPage(): ComponentPage = {
    driver.get(url + "/container.html")
    new ComponentPage()
  }

}
