package qa.sandbox.commons.model

import qa.sandbox.commons.utils.json.JsonUtil

trait RestEntity {

  def asJson: String = JsonUtil.toJson(this)

}
