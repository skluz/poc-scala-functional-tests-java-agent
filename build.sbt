name := "qa-sandbox"

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.12.1",
  scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-language:reflectiveCalls", "-language:postfixOps"),
  crossPaths := false,
  resolvers += "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

lazy val versions = new {
  val TypesafeConfig = "1.3.1"
  val Logback = "1.1.7"
  val ScalaLogging = "3.5.0"
  val AkkaHttp = "10.0.0"
  val Akka = "2.4.14"
  val WireMock = "2.4.1"
  val Jackson = "2.8.4"
  val ScalaTest = "3.0.1"
  val Selenium = "3.0.1"
  val AspectJWeaver = "1.8.10"
  val Clapper = "1.1.1"
}

lazy val commonDependencies = Seq(
  "com.typesafe" % "config" % versions.TypesafeConfig,
  "ch.qos.logback" % "logback-classic" % versions.Logback,
  "com.typesafe.scala-logging" %% "scala-logging" % versions.ScalaLogging,
  "org.scalatest" %% "scalatest" % versions.ScalaTest % "test"
)


lazy val `qa-sandbox` = (project in file("."))
  .aggregate(`commons-model`)
  .aggregate(`commons-utils`)
  .aggregate(`commons-rest`)
  .aggregate(`commons-web`)
  .aggregate(`mock-web`)
  .aggregate(`mock-rest`)
  .aggregate(`sample-model`)
  .aggregate(`sample-rest`)
  .aggregate(`sample-web`)
  .aggregate(`sample-perf`)

lazy val `commons-model` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(
    "org.clapper" %% "classutil" % versions.Clapper
  ))
  .dependsOn(`commons-utils`)

lazy val `commons-utils` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % versions.Jackson
  ))

lazy val `commons-rest` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(

  ))

lazy val `commons-web` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(
    "org.seleniumhq.selenium" % "selenium-java" % versions.Selenium exclude("org.seleniumhq.selenium", "selenium-java"),
    "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver
  ))

lazy val `mock-web` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(
    "com.typesafe.akka" %% "akka-http" % versions.AkkaHttp,
    "com.typesafe.akka" %% "akka-slf4j" % versions.Akka
  ))

lazy val `mock-rest` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(
    "com.github.tomakehurst" % "wiremock" % versions.WireMock
  ))
  .dependsOn(`sample-rest`)

lazy val `sample-model` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(

  ))
  .dependsOn(`commons-model`)

lazy val `sample-web` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(
    "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver
  ))
  .dependsOn(`sample-model`, `commons-web` % "test->test;compile->compile")
  .enablePlugins(JavaAgent)
  .settings(
    javaAgents += "org.aspectj" % "aspectjweaver" % versions.AspectJWeaver % "test"
  )

lazy val `sample-rest` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(

  ))
  .dependsOn(`sample-model`, `commons-rest` % "test->test;compile->compile")

lazy val `sample-perf` = project
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= commonDependencies ++ Seq(

  ))
  .dependsOn(`sample-model`, `sample-rest`)


commonSettings