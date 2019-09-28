import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.autoImport._
import ReleaseTransformations._

object Release extends AutoPlugin {
  implicit class ReleaseSettings(val project: Project) extends AnyVal {
    def withRelease: Project = project.settings(releaseSettings)
  }

  private lazy val releaseSettings = List(
    releaseUseGlobalVersion := true,
    releaseTagName          := s"${ (version in ThisBuild).value }",
    releaseTagComment       := s"Releasing ${(version in ThisBuild).value} [ci skip]",
    releaseCommitMessage    := s"Setting version to ${(version in ThisBuild).value} [ci skip]",
    releaseProcess          := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      releaseStepCommand("docker:publish"),
      commitReleaseVersion,
      tagRelease,
      setNextVersion,
      commitNextVersion,
      pushChanges))
}
