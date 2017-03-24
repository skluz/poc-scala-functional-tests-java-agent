package qa.sandbox.commons.api

import qa.sandbox.commons.utils.json.JsonUtil

trait RestEntity {

  def asJson: String = JsonUtil.toJson(this)

}
