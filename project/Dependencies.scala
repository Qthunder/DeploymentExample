import sbt.Project
import sbt._
import sbt.Keys._

object Dependencies {
  implicit class WithDependencies(val project: Project) extends AnyVal {
    def withDependencies : Project = project.settings(libraryDependencies ++= dependencies)
  }

  val http4sVersion = "0.21.0-M4"

  val dependencies = List(
    "org.http4s"      %% "http4s-dsl"          % http4sVersion,
    "org.http4s"      %% "http4s-blaze-server" % http4sVersion,
    "org.http4s"      %% "http4s-circe"        % http4sVersion
  )
}
