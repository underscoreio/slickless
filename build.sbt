name := "slickless"

organization := "io.underscore"

version := "0.1.0"

licenses += ("Apache-2.0", url("http://apache.org/licenses/LICENSE-2.0"))

// Scala

scalaVersion := "2.11.7"

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
  "com.typesafe.slick" %% "slick"           % "3.0.2",
  "com.chuusai"        %% "shapeless"       % "2.2.5",
  "org.scalatest"      %% "scalatest"       % "2.2.4"   % "test",
  "com.h2database"      % "h2"              % "1.4.185" % "test",
  "ch.qos.logback"      % "logback-classic" % "1.1.3"   % "test"
)

// Bintray

publishMavenStyle := true

bintrayOrganization := Some("underscoreio")

bintrayPackageLabels := Seq("scala", "slick", "shapeless")

bintrayRepository := "libraries"
