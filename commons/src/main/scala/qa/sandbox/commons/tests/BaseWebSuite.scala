package qa.sandbox.commons.tests

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{Assertion, BeforeAndAfterAll, FlatSpec, Matchers}
import qa.sandbox.commons.web.config.DriverFactory

abstract class BaseWebSuite extends FlatSpec with BeforeAndAfterAll with Matchers with Assertion with LazyLogging {

  override def afterAll(): Unit = {
    DriverFactory.dispose()
  }

}
