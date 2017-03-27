package qa.sandbox.mock.api.store

import java.time.Instant

case class StoreState(products: Seq[Product])
case class Product(id: String, name: String, createdAt: Instant)
