package slickless

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration._
import org.scalatest.{ FreeSpec, Matchers }
import slick.driver.H2Driver.api._
import shapeless.{ HList, ::, HNil }

class HListShapeSpec extends FreeSpec with Matchers {
  class Users(tag: Tag) extends Table[Long :: String :: HNil](tag, "users") {
    def id    = column[Long]( "id", O.PrimaryKey, O.AutoInc )
    def email = column[String]("email")

    def * = id :: email :: HNil
  }

  lazy val users = TableQuery[Users]

  "slick tables with shapeless mappings" - {
    "should support inserts and selects" in {
      val db = Database.forConfig("h2")

      val action = for {
        _   <- users.schema.create
        _   <- users += 1L :: "dave@example.com" :: HNil
        ans <- users.result.head
      } yield ans

      Await.result(db.run(action), 1.second) should equal (1L :: "dave@example.com" :: HNil)
    }
  }
}
