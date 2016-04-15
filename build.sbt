name := "slickless"

organization := "io.underscore"

version := "0.1.1"

licenses += ("Apache-2.0", url("http://apache.org/licenses/LICENSE-2.0"))

// Scala

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ywarn-dead-code",
  "-Xlint",
  "-Xfatal-warnings"
)

// Dependencies

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"           % "3.1.1",
  "com.chuusai"        %% "shapeless"       % "2.3.0",
  "org.scalatest"      %% "scalatest"       % "2.2.5"   % "test",
  "com.h2database"      % "h2"              % "1.4.191" % "test",
  "ch.qos.logback"      % "logback-classic" % "1.1.7"   % "test"
)

// Bintray

publishMavenStyle := true

bintrayOrganization := Some("underscoreio")

bintrayPackageLabels := Seq("scala", "slick", "shapeless")

bintrayRepository := "libraries"
