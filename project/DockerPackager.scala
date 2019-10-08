import com.typesafe.sbt.SbtNativePackager.Docker
import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.packager.docker.DockerPlugin
import sbt.Keys._
import sbt._
object DockerPackager {
  implicit class WithDocker(val project: Project) extends AnyVal {
    def withDocker: Project = project.enablePlugins(DockerPlugin).settings(settings)
  }

  val settings =  List(
    dockerBaseImage  in Docker := "adoptopenjdk/openjdk12-openj9:alpine-slim",
    maintainer       in Docker := "gabrieasman10@gmail.com",
    dockerRepository in Docker := Some("eu.gcr.io"),
    packageName      in Docker := s"copper-creek-252916/${Project.normalizeModuleID(moduleName.value)}"
  )
}
