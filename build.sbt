import DockerPackager._
import Dependencies._
import Release._
import Bintray._

skip in publish := true

lazy val root =
  (project in file("."))
  .aggregate(dockerExample, libraryExample)
  .withRelease

lazy val dockerExample = (project in file("docker-example"))
  .settings(moduleName   := "Deployment Example (Docker)")
  .settings(scalaVersion := "2.13.0")
  .settings(mainClass    := Some("example.deployment.Main"))
  .withDependencies
  .withDocker

lazy val libraryExample = (project in file("library-example"))
  .settings(moduleName   := "Deployment Example (Library)")
  .settings(scalaVersion := "2.13.0")
  .withDependencies
  .withBintray

