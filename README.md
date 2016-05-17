# slickless

[![Join the chat at https://gitter.im/underscoreio/slickless](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/underscoreio/slickless?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Shapeless HList support for Slick.

by [Richard Dallaway][d6y], [Miles Sabin][milessabin], and [Dave Gurnell][davegurnell].

Copyright 2015-2016 [Underscore Consulting LLP][underscore]. Licensed [Apache 2][license].

# Installation

Grab the code by adding the following to your `build.sbt`:

~~~
scalaVersion := "2.11.8"

resolvers += "Underscore Bintray" at "https://dl.bintray.com/underscoreio/libraries"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"     % "3.1.1",
  "com.chuusai"        %% "shapeless" % "2.3.1",
  "io.underscore"      %% "slickless" % "0.2.1"
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
