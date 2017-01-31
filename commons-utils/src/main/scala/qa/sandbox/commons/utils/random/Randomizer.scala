package qa.sandbox.commons.utils.random

import java.time.{LocalDate, Period, Year}
import java.util.UUID

object Randomizer {

  object Numbers {
    def int(min: Int = 1, max: Int = 100): Int = min + (Math.random() * ((max - min) + 1)).toInt
    def double(min: Double = 0.0, max: Double = 100.0, scale: Int = 2): Double = BigDecimal(min + Math.random() * (max - min)).setScale(scale, BigDecimal.RoundingMode.HALF_DOWN).toDouble
    def boolean(): Boolean = Math.random() < 0.5
  }

  object Strings {

  }

  object Dates {
    def date(from: LocalDate = LocalDate.now().minusYears(85), to: LocalDate = LocalDate.now().minusYears(18)): LocalDate = {
      LocalDate.ofEpochDay((Math.random() * (to.toEpochDay - from.toEpochDay) + from.toEpochDay).toLong)
    }
    def now(): LocalDate = LocalDate.now()
  }

  class Person {
    val birthDate: LocalDate = Dates.date()
    val age: Int = Period.between(birthDate, Dates.now()).getYears
  }

  def id(): String = UUID.randomUUID().toString

}
