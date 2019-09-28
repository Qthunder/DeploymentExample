import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.autoImport._
import ReleaseTransformations._

object Release extends AutoPlugin {
  implicit class ReleaseSettings(val project: Project) extends AnyVal {
    def withRelease: Project = project.settings(releaseSettings(publishStep = releaseStepCommand("docker:publish")))
    def withReleaseLibrary : Project = project.settings(releaseSettings(publishArtifacts))
  }

  private def releaseSettings(publishStep : ReleaseStep) = List(
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
      publishStep,
      commitReleaseVersion,
      tagRelease,
      setNextVersion,
      commitNextVersion,
      pushChanges))
}
