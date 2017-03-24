package qa.sandbox.commons.utils.dates

import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter

trait DateFormatter {

  implicit class LocalDateTimeFormatter(date: LocalDateTime) {
    def toISO8601String(): String = {
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX").withZone(ZoneOffset.UTC).format(date)
    }
  }

  implicit class LocalDateFormatter(date: LocalDate) {
    def toISO8601String(): String = {
      DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(date)
    }
  }

}
