package qa.sandbox.tests.api.store.model

import java.time.Instant

import qa.sandbox.commons.api.RestEntity

case class Product(
  id: String,
  name: String,
  description: String,
  price: Int,
  available: Boolean,
  createdAt: Instant
) extends RestEntity
