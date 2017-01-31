package qa.sandbox.commons.samplers

import qa.sandbox.commons.samplers.SerializationType.SerializationType

import scala.annotation.StaticAnnotation

class MockEntry(url: String, serializationType: SerializationType) extends StaticAnnotation
