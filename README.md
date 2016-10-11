# slickless

[![Build Status](https://travis-ci.org/underscoreio/slickless.svg?branch=master)](https://travis-ci.org/underscoreio/slickless)
[![Coverage status](https://img.shields.io/codecov/c/github/underscoreio/slickless/develop.svg)](https://codecov.io/github/underscoreio/slickless)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.underscore/slickless_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.underscore/slickless_2.11)
[![Join the chat at https://gitter.im/underscoreio/slickless](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/underscoreio/slickless?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Shapeless HList support for Slick.

by [Richard Dallaway][d6y],
[Miles Sabin][milessabin],
and [Dave Gurnell][davegurnell].

Copyright 2015-2016 [Underscore Consulting LLP][underscore].
Licensed [Apache 2][license].

## Getting Started

Grab the code by adding the following to your `build.sbt`:

~~~
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"     % "3.1.1",
  "com.chuusai"        %% "shapeless" % "2.3.1",
  "io.underscore"      %% "slickless" % "0.3.0"
)
~~~

## Synopsis

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

If you want to map your `HList` to a case class
(i.e. you have a case class that has more than
22 fields and you are using slickless to bypass this limit),
you can do the following

~~~ scala
import slick.driver.H2Driver.api._
import shapeless.{ HList, ::, HNil, Generic }
import slickless._

case class User(id: Long, email: String)

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id    = column[Long]( "id", O.PrimaryKey, O.AutoInc )
  def email = column[String]("email")

  def * = (id :: email :: HNil).mappedWith(Generic[User])
}

lazy val users = TableQuery[Users]
~~~

## Notes

Due to this [issue](https://github.com/milessabin/shapeless/issues/619),
if you accidentally make a mapping which is incorrect,
the Scala compiler can take a huge amount of time to report an error.
If your slickless project is taking an insanely long amount of time to compile
(more than a couple of minutes),
try to make sure you have the mapping correct before using `<>`.

## Publishing

We use the [sbt-pgp plugin](http://www.scala-sbt.org/sbt-pgp/usage.html) and
the [sbt-sonatype plugin](https://github.com/xerial/sbt-sonatype)
to publish to [Maven Central](https://issues.sonatype.org/browse/OSSRH-24293).

[d6y]: https://github.com/d6y
[milessabin]: https://github.com/milessabin
[davegurnell]: https://github.com/davegurnell

[underscore]: http://underscore.io
[license]: http://www.apache.org/licenses/LICENSE-2.0
