package qa.sandbox.tests.api.store.samplers

import qa.sandbox.tests.api.store.model.Product
import qa.sandbox.commons.random.Randomizer

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