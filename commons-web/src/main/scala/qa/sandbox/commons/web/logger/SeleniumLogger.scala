package qa.sandbox.commons.web.logger

import com.typesafe.scalalogging.LazyLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{Around, Aspect, Pointcut}
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.{SearchContext, WebElement}

@Aspect
abstract class SeleniumLogger extends LazyLogging {

  private val prefix = "\t"

  @Pointcut
  def logWebDriverAction()

  @Around("logWebDriverAction()")
  def logWebDriverAction(joinPoint: ProceedingJoinPoint): Any = {
    val caller = joinPoint.getThis.getClass.getSimpleName
    joinPoint.getSignature.getName match {
      case "get" => logger.info("{}{}.get({})", prefix, caller, joinPoint.getArgs.head)
      case "quit" => logger.info("{}{}.quit()", prefix, caller)
      case "findElement" | "findElements" => // handled by logSearchContextAction()
      case notFound => throw new UnsupportedOperationException(s"WebDriver.$notFound logger action not implemented")
    }
    joinPoint.proceed()
  }

  @Pointcut
  def logWebElementAction()

  @Around("logWebElementAction()")
  def logWebElementAction(joinPoint: ProceedingJoinPoint): Any = {
    val caller = joinPoint.getThis.getClass.getSimpleName
    joinPoint.getSignature.getName match {
      case "click" | "isDisplayed" => logger.info("{}{}.{}()", prefix, caller, joinPoint.getSignature.getName)
      case "setParent" | "setId" | "setFileDetector" | "setFoundBy" | "execute" =>
      case notFound => throw new UnsupportedOperationException(s"RemoteWebElement.$notFound logger action not implemented")
    }
    joinPoint.proceed()
  }

  @Pointcut
  def logSearchContextAction()

  @Around("logSearchContextAction()")
  def logSearchContextAction(joinPoint: ProceedingJoinPoint): Any = {
    val result = joinPoint.proceed()
    val caller = joinPoint.getThis.asInstanceOf[SearchContext].getClass.getSimpleName
    joinPoint.getSignature.getName match {
      case "findElement" => logger.info("{}{}.findElement({}) -> {}", prefix, caller, joinPoint.getArgs.head, findResult(result))
      case "findElements" => logger.info("{}{}.findElement({}) -> {}", prefix, caller, joinPoint.getArgs.head, findResult(result.asInstanceOf[List[WebElement]]))
      case notFound => throw new UnsupportedOperationException(s"SearchContext.$notFound logger action not implemented")
    }
    result
  }

  @Pointcut
  def logWaitForAction()

  @Around("logWaitForAction()")
  def logWaitForAction(joinPoint: ProceedingJoinPoint): Any = {
    val caller = joinPoint.getThis.asInstanceOf[FluentWait[_]].getClass.getSimpleName
    joinPoint.getSignature.getName match {
      case "until" => logger.info("{}{}.until({})", prefix, caller, joinPoint.getArgs.head)
      case "withTimeout" | "pollingEvery" | "ignoreAll" | "ignoring" | "propagateIfNotIgnored" => // no need to log
      case notFound => throw new UnsupportedOperationException(s"FluentWait.$notFound logger action not implemented")
    }
    joinPoint.proceed()
  }

  private def findResult[T](t: T): String = {
    t match {
      case _: WebElement => true.toString
      case _: List[_] => t.asInstanceOf[List[_]].size.toString
      case _ => throw new UnsupportedOperationException("Not implemented")
    }
  }

}
