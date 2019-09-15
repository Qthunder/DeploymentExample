import com.typesafe.sbt.packager.docker.{DockerAlias, DockerPlugin}
import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.SbtNativePackager.Docker
import com.typesafe.sbt.packager.archetypes.scripts.AshScriptPlugin
import sbt.Keys._
import sbt._
object Build {
  implicit class WithDocker(val project: Project) extends AnyVal {
    def withDocker: Project = project.enablePlugins(DockerPlugin, AshScriptPlugin).settings(settings)
  }
  
  val settings =  List(
    dockerBaseImage in Docker := "adoptopenjdk/openjdk12-openj9:alpine-slim",
    maintainer      in Docker := "gabrieasman10@gmail.com",
    dockerAlias     in Docker := 
      DockerAlias(
        registryHost = Some("eu.gcr.io"),
        username     = Some("copper-creek-252916"),
        name         = moduleName.value,
        tag          = Some(version.value)
      )
  )
}
