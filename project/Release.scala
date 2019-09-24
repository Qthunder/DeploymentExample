import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.autoImport._

object Release extends AutoPlugin {
  object autoImport {
    implicit class ReleaseSettings(val project: Project) extends AnyVal {
      def withRelease: Project = project.settings(releaseSettings)
    }
  }

  import ReleaseTransformations._

  private lazy val releaseSettings = Seq(
    releaseUseGlobalVersion := true,
    releaseTagName          := s"${ (version in ThisBuild).value }",
    releaseTagComment       := s"Releasing ${(version in ThisBuild).value} [ci skip]",
    releaseCommitMessage    := s"Setting version to ${(version in ThisBuild).value} [ci skip]",
    releaseProcess          := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      setReleaseVersion,
      releaseStepCommand("docker:publish"),
      commitReleaseVersion,
      tagRelease,
      setNextVersion,
      commitNextVersion,
      pushChanges
    )
  )
}
