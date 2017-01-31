//package samplers
//
//import sandbox.tests.samplers.PersonSamplers
//
//import scala.reflect.runtime.{universe => ru}
//import scala.reflect.runtime.universe._
//
//object SamplersFinder {
//
//  def action(): Unit = {
//    val samplerRepositories = List(classOf[PersonSamplers])
//    samplerRepositories.foreach((repoClass: Class[_]) => {
//      val repositoryClass = ru.runtimeMirror(repoClass.getClassLoader).classSymbol(repoClass)
//      if(repositoryClass.annotations.exists(classAnnotation => classAnnotation.tree.tpe == ru.typeOf[SamplersRepository])) {
//        val members: List[(Symbol, Annotation)] = repositoryClass.toType.members.filter(classMember => classMember.annotations.exists(memberAnnotation => memberAnnotation.tree.tpe == ru.typeOf[MockSampler])).flatMap(classMember => classMember.annotations.filter(memberAnnotation => memberAnnotation.tree.tpe == ru.typeOf[MockSampler]).map((classMember, _))).toList
//        members.foreach(x => {
//          val annotation = x._2
//          val complexAnnotationType = ru.typeOf[MockSampler]
//          val complexClassSymbol = ru.typeOf[MockSampler].typeSymbol.asClass
//          val complexAnnotationArgs = annotation.tree.children.tail
//          complexAnnotationArgs.map(a => {
//            println(a)
//          })
////          val complexAnnotationArgValues = complexAnnotationArgs.map(a => a.productElement(0).asInstanceOf[ru.Constant].value)
////          val runtimeMirror = ru.runtimeMirror(getClass.getClassLoader)
////          val classSymbol = complexAnnotationType.typeSymbol.asClass
////          val classMirror = runtimeMirror.reflectClass(classSymbol)
////          val constructorMethodSymbol = complexAnnotationType.decl(ru.termNames.CONSTRUCTOR).asMethod
////          val constructorMethodMirror = classMirror.reflectConstructor(constructorMethodSymbol)
////          val myInstance = constructorMethodMirror(complexAnnotationArgValues: _*).asInstanceOf[MockSampler]
////          println(myInstance)
//        })
//      }
//
//    })
//  }
//
//}
