package qa.sandbox.commons

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{Assertion, BeforeAndAfterAll, FlatSpec, Matchers}

/**
  * Created by slawek on 26.03.2017.
  */
abstract class BaseSuite extends FlatSpec with BeforeAndAfterAll with Matchers with Assertion with LazyLogging {

}
