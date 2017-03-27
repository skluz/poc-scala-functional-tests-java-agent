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
  val AspectJWeaver = "1.8.10"
  val JavaFaker = "0.13"
  val Scalatra = "2.5.0"
}

lazy val `qa-sandbox` = (project in file("."))
  .aggregate(`commons`)
  .aggregate(`commons-api`)
  .aggregate(`commons-mock`)
  .aggregate(`commons-perf`)
  .aggregate(`commons-web`)
  .aggregate(`tests-api-store`)
  .aggregate(`tests-perf-store`)
  .aggregate(`tests-web-store`)
  .aggregate(`mock-api-store`)
  .aggregate(`mock-web-store`)

lazy val `commons` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "com.typesafe" % "config" % versions.TypesafeConfig,
    "ch.qos.logback" % "logback-classic" % versions.Logback,
    "com.typesafe.scala-logging" %% "scala-logging" % versions.ScalaLogging,
    "com.github.javafaker" % "javafaker" % versions.JavaFaker,
    "org.scalatest" %% "scalatest" % versions.ScalaTest
  ))

lazy val `commons-api` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % versions.Jackson,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % versions.Jackson,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % versions.Jackson
  ))
  .dependsOn(`commons`)

lazy val `commons-perf` = project
  .settings(commonSettings: _*)
  .dependsOn(`commons`)

lazy val `commons-mock` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "org.eclipse.jetty" % "jetty-server" % "9.4.2.v20170220",
    "org.eclipse.jetty" % "jetty-webapp" % "9.4.2.v20170220",
    "org.scalatra" %% "scalatra" % versions.Scalatra,
    "org.scalatra" %% "scalatra-metrics" % versions.Scalatra,
    "org.scalatra" %% "scalatra-json" % versions.Scalatra,
    "org.json4s"   %% "json4s-jackson" % "3.5.1",
    "org.json4s"   %% "json4s-ext" % "3.5.1",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % versions.Jackson,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % versions.Jackson,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % versions.Jackson
  ))
  .dependsOn(`commons`)

lazy val `commons-web` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "org.seleniumhq.selenium" % "selenium-java" % versions.Selenium exclude("org.seleniumhq.selenium", "selenium-java"),
    "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver
  ))
  .dependsOn(`commons`)

lazy val `tests-api-store` = project.settings(commonSettings: _*).dependsOn(`commons-api`)
lazy val `tests-perf-store` = project.settings(commonSettings: _*).dependsOn(`commons-perf`)
lazy val `tests-web-store` = project
  .settings(commonSettings: _*)
  .dependsOn(`commons-web`)
  .enablePlugins(JavaAgent)
  .settings(
    javaAgents += "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver % "test"
  )

lazy val `mock-api-store` = project
  .settings(commonSettings: _*)
  .dependsOn(`commons-mock`, `tests-api-store`)

lazy val `mock-web-store` = project.settings(commonSettings: _*).dependsOn(`commons-mock`)

commonSettings