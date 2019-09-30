import bintray.BintrayPlugin.autoImport._
import sbt.Keys._
import sbt.Project
object Bintray {

  implicit class WithBintray(project: Project) {
    def withBintray: Project = project.settings(settings)
  }

  val settings = List(
    skip in publish := false,
    bintrayPackage := Project.normalizeModuleID(moduleName.value),
    bintrayOmitLicense := true,
    bintrayOrganization := Some("ovotech"),
    bintrayRepository := "maven-private"
  )
}
