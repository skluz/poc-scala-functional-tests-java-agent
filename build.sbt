name := "qa-sandbox"

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.12.1",
  scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-language:reflectiveCalls", "-language:postfixOps"),
  crossPaths := false
)

lazy val versions = new {
  val TypesafeConfig = "1.3.1"
  val Logback = "1.2.2"
  val ScalaLogging = "3.5.0"
  val Jackson = "2.8.7"
  val ScalaTest = "3.0.1"
  val Selenium = "3.0.1"
  val WireMock = "2.4.1"
  val AspectJWeaver = "1.8.10"
  val JavaFaker = "0.13"
}

lazy val commonsDependencies = Seq(
  "com.typesafe" % "config" % versions.TypesafeConfig,
  "ch.qos.logback" % "logback-classic" % versions.Logback,
  "com.typesafe.scala-logging" %% "scala-logging" % versions.ScalaLogging
)

lazy val `qa-sandbox` = (project in file("."))
  .aggregate(`commons`)
  .aggregate(`model-store`)
  .aggregate(`mock-api-store`)
  .aggregate(`mock-web-store`)
  .aggregate(`tests-api-store`)
  .aggregate(`tests-web-store`)
  .aggregate(`tests-perf-store`)

lazy val `commons` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonsDependencies ++ Seq(
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % versions.Jackson,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % versions.Jackson,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % versions.Jackson,
    "com.github.tomakehurst" % "wiremock" % versions.WireMock,
    "org.seleniumhq.selenium" % "selenium-java" % versions.Selenium exclude("org.seleniumhq.selenium", "selenium-java"),
    "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver,
    "org.scalatest" %% "scalatest" % versions.ScalaTest,
    "com.github.javafaker" % "javafaker" % versions.JavaFaker,
    "com.sparkjava" %"spark-core" % "2.5.5"
  ))

lazy val `model-store` = project.settings(commonSettings: _*).dependsOn(`commons`)

lazy val `tests-api-store` = project.settings(commonSettings: _*).dependsOn(`commons`, `model-store`)
lazy val `tests-perf-store` = project.settings(commonSettings: _*).dependsOn(`commons`, `model-store`)

lazy val `tests-web-store` = project
  .settings(commonSettings: _*)
  .dependsOn(`commons`, `model-store`)
  .enablePlugins(JavaAgent)
  .settings(
    javaAgents += "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver % "test"
  )

lazy val `mock-api-store` = project.settings(commonSettings: _*).dependsOn(`commons`, `model-store`)
lazy val `mock-web-store` = project.settings(commonSettings: _*).dependsOn(`commons`)

commonSettings