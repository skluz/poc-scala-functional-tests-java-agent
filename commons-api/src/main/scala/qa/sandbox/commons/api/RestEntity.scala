package qa.sandbox.commons.api

import qa.sandbox.commons.api.json.JsonUtil

/**
  * Created by slawek on 26.03.2017.
  */
trait RestEntity {

  def asJson: String = JsonUtil.toJson(this)

}
