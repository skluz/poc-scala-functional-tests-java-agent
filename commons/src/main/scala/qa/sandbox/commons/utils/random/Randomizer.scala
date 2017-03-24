package qa.sandbox.commons.utils.random

import java.time.{LocalDate, Period, Year}
import java.util.UUID
import java.util.concurrent.TimeUnit

import com.github.javafaker.Faker

object Randomizer {

  private val faker = new Faker()

  object Numbers {
    def int(min: Int = 1, max: Int = 100): Int = faker.number().numberBetween(min, max)
    def long(min: Long = 1, max: Long = Long.MaxValue): Long = faker.number().numberBetween(min, max)
    def double(min: Long = 0, max: Long = 1, scale: Int = 2): Double = faker.number().randomDouble(2, min, max)
  }
  object Boolean {
    def boolean(): Boolean = faker.bool().bool()
  }

  object Strings {
    def word(): String = faker.lorem().word()
    def sentence(wordCount: Int = 10): String = faker.lorem().sentence(wordCount)
    def paragraph(sentenceCount: Int = 10): String = faker.lorem().paragraph(sentenceCount)
  }

  object Instant {
    def inThePast() = faker.date().past(365, TimeUnit.DAYS).toInstant
  }

  object Dates {
    def date(from: LocalDate = LocalDate.now().minusYears(85), to: LocalDate = LocalDate.now().minusYears(18)): LocalDate = {
      LocalDate.ofEpochDay((Math.random() * (to.toEpochDay - from.toEpochDay) + from.toEpochDay).toLong)
    }
    def now(): LocalDate = LocalDate.now()
  }

  def id(): String = UUID.randomUUID().toString

}
