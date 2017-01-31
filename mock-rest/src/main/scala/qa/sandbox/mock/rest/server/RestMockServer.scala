package qa.sandbox.mock.rest.server

import com.github.tomakehurst.wiremock.WireMockServer
import com.typesafe.scalalogging.LazyLogging
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.client.WireMock._
import qa.sandbox.commons.samplers.SamplersScanner
import qa.sandbox.mock.rest.config.Config
import sandbox.tests.samplers.PersonSamplers

object RestMockServer extends App with LazyLogging {

  val mockServer = new WireMockServer(options().port(Config.port).bindAddress(Config.interface))

  mockServer.stubFor(
    get(urlEqualTo("/person/12"))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(PersonSamplers.randomPerson.asJson)
      )
  )
  mockServer.start()
  SamplersScanner.get()
  mockServer.stop()

}
