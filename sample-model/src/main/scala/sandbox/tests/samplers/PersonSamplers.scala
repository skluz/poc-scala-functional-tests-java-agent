package sandbox.tests.samplers

import qa.sandbox.commons.samplers.{MockEntry, SamplersRepository, SerializationType}
import sandbox.tests.model.Person

object PersonSamplers extends SamplersRepository {

  @MockEntry("/person/12", SerializationType.Json)
  def randomPerson = Person("John", 12)

  @MockEntry("/person/2", SerializationType.String)
  val x = 12

}