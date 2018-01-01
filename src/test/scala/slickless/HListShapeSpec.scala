package slickless

import shapeless.{::, HNil}
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global

class HListShapeSpec extends Spec {

  class Users(tag: Tag) extends Table[Long :: String :: HNil](tag, "users") {
    def id    = column[Long]( "id", O.PrimaryKey, O.AutoInc )
    def email = column[String]("email")

    def * = id :: email :: HNil
  }

  lazy val users = TableQuery[Users]

  "slick tables with hlist mappings" - {
    "should support inserts and selects" in {
      val db = Database.forConfig("h2")

      val action = for {
        _   <- users.schema.create
        _   <- users += 1L :: "dave@example.com" :: HNil
        ans <- users.result.head
        _   <- users.schema.drop
      } yield ans

      whenReady(db.run(action)) { _ should equal (1L :: "dave@example.com" :: HNil) }
    }
  }
}
