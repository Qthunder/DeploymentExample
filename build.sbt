import DockerPackager._
import Dependencies._
import Release._
lazy val root = (project in file("."))
  .settings(name         := "Deployment Example")
  .settings(scalaVersion := "2.13.1")
  .settings(mainClass    := Some("example.deployment.Main"))
  .withDependencies
  .withDocker
  .withRelease
