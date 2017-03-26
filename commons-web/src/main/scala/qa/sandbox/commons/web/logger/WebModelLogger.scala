package qa.sandbox.commons.web.logger

import com.typesafe.scalalogging.LazyLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{AfterReturning, Around, Aspect, Pointcut}

@Aspect
abstract class WebModelLogger extends LazyLogging {

  @Pointcut
  def logTraceableAction()

  @Around("logTraceableAction()")
  def logTraceableAction(joinPoint: ProceedingJoinPoint): Any = {
    val caller = joinPoint.getThis.getClass.getSimpleName
    joinPoint.getArgs.length match {
      case 0 => logger.info("{}.{}()", caller, joinPoint.getSignature.getName)
      case 1 => logger.info("{}.{}({})", caller, joinPoint.getSignature.getName, joinPoint.getArgs.head)
      case default => throw new UnsupportedOperationException(default + "not implemented")
    }
    joinPoint.proceed()
  }

}
