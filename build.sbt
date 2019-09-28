import DockerPackager._
import Dependencies._
import Release._

lazy val root = (project in file("."))

lazy val dockerExample = (project in file("docker-example"))
  .settings(moduleName   := "Deployment Example (Docker)")
  .settings(scalaVersion := "2.13.0")
  .settings(mainClass    := Some("example.deployment.Main"))
  .withDependencies
  .withDocker
  .withRelease

lazy val libraryExample = (project in file("library-example"))
  .settings(moduleName   := "Deployment Example (Library)")
  .settings(scalaVersion := "2.13.0")
  .withDependencies
  .withReleaseLibrary

