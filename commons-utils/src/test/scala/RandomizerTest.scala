import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FlatSpec, Matchers}
import qa.sandbox.commons.utils.dates.DateFormatter
import qa.sandbox.commons.utils.random.Randomizer

class RandomizerTest extends FlatSpec with Matchers with LazyLogging with DateFormatter {

  val seedSize = 1000

  it should "generate random ids" in {
    val ids = collection.mutable.Set[String]()
    (0 until seedSize).foreach(_ => ids += Randomizer.id())
    ids should have size seedSize
  }

  it should "generate integers" in {
    (0 until seedSize).foreach(_ => {
      Randomizer.Numbers.int() should (be >= 1 and be <= 100)
      Randomizer.Numbers.int(5, 20) should (be >= 5 and be <= 20)
      Randomizer.Numbers.int(-10, 2) should (be >= -10 and be <= 2)
    })
  }

  it should "generate doubles" in {
    (0 until seedSize).foreach(_ => {
      Randomizer.Numbers.double() should (be >= 0.0 and be <= 100.0)
      Randomizer.Numbers.double(5.0, 20.0, 4) should (be >= 5.0 and be <= 20.0)
      Randomizer.Numbers.double(-10.0, 10.0, 1).toString should fullyMatch regex "-?\\d*\\.\\d{1}"
      Randomizer.Numbers.double(0.0, 1.0, 3).toString should fullyMatch regex "\\d*\\.\\d{1,3}"
    })
  }

  it should "generate dates" in {
    val date = new Randomizer.Person()
    logger.info("Date: {}, {}", date.birthDate.toString, date.age.toString)
  }

}
