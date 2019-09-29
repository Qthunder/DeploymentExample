import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.autoImport._
import ReleaseTransformations._
import com.typesafe.sbt.SbtNativePackager.Docker

object Release extends AutoPlugin {
  implicit class ReleaseSettings(val project: Project) extends AnyVal {
    def withRelease: Project = project.settings(releaseSettings)
  }

  private val dockerPublish = ReleaseStep(action = releaseStepTask(publish in Docker), _.)

  private def releaseSettings = List(
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
      publishArtifacts,
      dockerPublish,
      commitReleaseVersion,
      tagRelease,
      setNextVersion,
      commitNextVersion,
      pushChanges))
}
