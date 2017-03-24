package qa.sandbox.commons.web.model

import qa.sandbox.commons.web.logger.Loggable

trait Application extends WebContext {

  val url: String

  @Loggable
  def start(): Page

}
