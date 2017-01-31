package qa.sandbox.commons.samplers

import scala.reflect.runtime.{universe => ru}

object SamplersScanner {

  val samplersList = scala.collection.mutable.ArrayBuffer.empty[Class[_]]

  def get(): Unit = {
    samplersList.foreach((repoClass: Class[_]) => {
      val runtimeMirror = ru.runtimeMirror(repoClass.getClassLoader)
      val module = runtimeMirror.staticModule(repoClass.getName)
      val obj = runtimeMirror.reflectModule(module).instance
    })
  }

}
