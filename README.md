# slickless

Shapeless HList support for Slick.

by [Richard Dallaway][d6y], [Miles Sabin][milessabin], and [Dave Gurnell][davegurnell].

Copyright 2015 [Underscore Consulting LLP][underscore]. Licensed [Apache 2][license].

# Installation

Grab the code by adding the following to your `build.sbt`:

~~~
scalaVersion := "2.11.7"

resolvers += "Underscore Bintray" at "https://dl.bintray.com/underscoreio/libraries"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"     % "3.0.0",
  "com.chuusai"        %% "shapeless" % "2.2.5",
  "io.underscore"      %% "slickless" % "0.1.0"
)
~~~

# Usage

Import Slick, shapeless, and slickless,
and you should be able to define `Tables` on any shapeless `HList` type:

~~~ scala
import slick.driver.H2Driver.api._
import shapeless.{ HList, ::, HNil }
import slickless._

class Users(tag: Tag) extends Table[Long :: String :: HNil](tag, "users") {
  def id    = column[Long]( "id", O.PrimaryKey, O.AutoInc )
  def email = column[String]("email")

  def * = id :: email :: HNil
}

lazy val users = TableQuery[Users]
~~~

[d6y]: https://github.com/d6y
[milessabin]: https://github.com/milessabin
[davegurnell]: https://github.com/davegurnell

[underscore]: http://underscore.io
[license]: http://www.apache.org/licenses/LICENSE-2.0
