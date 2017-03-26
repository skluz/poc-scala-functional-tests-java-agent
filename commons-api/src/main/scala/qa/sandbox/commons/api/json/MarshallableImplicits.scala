package qa.sandbox.commons.api.json

object MarshallableImplicits {

  implicit class Unmarshallable(unMarshallMe: String) {
    def toMap: Map[String,Any] = JsonUtil.toMap(unMarshallMe)
    def fromJson[T]()(implicit m: Manifest[T]): T =  JsonUtil.fromJson[T](unMarshallMe)
  }

  implicit class Marshallable[T](marshallMe: T) {
    def toJson: String = JsonUtil.toJson(marshallMe)
  }

}
