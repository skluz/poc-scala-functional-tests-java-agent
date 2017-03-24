package sandbox.tests.samplers

import qa.sandbox.commons.utils.random.Randomizer
import sandbox.tests.model.Product

object ProductSamplers {

  def randomProduct = Product(
    id = Randomizer.id(),
    name = Randomizer.Strings.sentence(5),
    description = Randomizer.Strings.paragraph(),
    price = Randomizer.Numbers.int(1,1000),
    available = Randomizer.Boolean.boolean(),
    createdAt = Randomizer.Instant.inThePast()
  )
}