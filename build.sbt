import Build._
import Dependencies._
lazy val root = (project in file("."))
  .settings(name         := "Deployment Example")
  .settings(scalaVersion := "2.13.0")
  .settings(mainClass    := Some("example.deployment.Main"))
  .withDependencies
  .withDocker
  