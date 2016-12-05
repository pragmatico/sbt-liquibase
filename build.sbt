import xerial.sbt.Sonatype.autoImport._
import ReleaseTransformations._

sbtPlugin := true

organization := "co.pragmati"

name := "sbt-liquibase"

version := "0.6.6"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4")

libraryDependencies ++= {
  Seq (
    "org.liquibase" % "liquibase-core" % "3.5.3"
  )
}

homepage := Some(url("https://github.com/pragmatico/sbt-liquibase"))

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

releasePublishArtifactsAction := PgpKeys.publishSigned.value

releaseCrossBuild := false

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
  pushChanges
)


// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <scm>
    <connection>scm:git:git@github.com:pragmatico/sbt-liquibase.git</connection>
    <developerConnection>scm:git:git@github.com:pragmatico/sbt-liquibase.git</developerConnection>
    <url>github.com/pragmatico</url>
  </scm>
    <developers>
      <developer>
        <id>jmbataller</id>
        <name>Jose Miguel Bataller</name>
        <url>http://github.com/jmbataller</url>
      </developer>
    </developers>
}
