package qa.sandbox.test.web.store.application

import qa.sandbox.commons.web.logger.Loggable
import qa.sandbox.commons.web.model.Application
import qa.sandbox.test.web.store.config.Config.web
import qa.sandbox.test.web.store.pages.{ComponentPage, LoaderPage}

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
