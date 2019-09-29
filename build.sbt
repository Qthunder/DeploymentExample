import DockerPackager._
import Dependencies._
import Release._
import Bintray._

lazy val root =
  (project in file("."))
  .withRelease

lazy val libraryExample = project
  .settings(moduleName   := "Library Example")
  .settings(scalaVersion := "2.13.0")
  .withDependencies
  .withBintray

lazy val dockerExample = project
  .settings(moduleName   := "Docker Example")
  .settings(scalaVersion := "2.13.0")
  .settings(mainClass    := Some("example.deployment.Main"))
  .withDependencies
  .withDocker

