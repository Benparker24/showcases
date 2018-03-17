name := """play-getting-started"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.github.seratch" %% "awscala" % "0.6.+",
   "com.typesafe.akka" %% "akka-stream" % "2.5.11"
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
