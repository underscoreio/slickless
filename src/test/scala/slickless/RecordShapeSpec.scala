package slickless

import shapeless.{::, HNil}
import slick.jdbc.H2Profile.api._
import shapeless._
import shapeless.labelled.FieldType
import shapeless.record._
import shapeless.syntax.singleton._

import scala.concurrent.ExecutionContext.Implicits.global

class RecordShapeSpec extends Spec {

  val id = Witness("their_id") // weird field name as example of being distinct from column names
  val email = Witness("their_email")
  type Rec = FieldType[id.T, Long] :: FieldType[email.T, String] :: HNil

  // Placeholder functions: generate something like this from a list of fields?
  def toRecord(pair: (Long, String)): Rec = ("their_id" ->> pair._1) :: ("their_email" ->> pair._2) :: HNil
  def fromRecord(r: Rec): Option[(Long, String)] = Some( (r("their_id"), r("their_email")) )

  // TODO: what would make a good table type here (Rec)?
  // Bonus: Presumably it'd be good to accept any record that has the table fields, silently ignoring any extra fields
  class Users(tag: Tag) extends Table[Rec](tag, "users_r") {
    def id    = column[Long]("db_id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("db_email")

    // TODO: make generic
    // Match columns to fields based based on the column order in the tuple and field order in the record
    def * = (id, email) <> (toRecord, fromRecord)
  }

  lazy val users = TableQuery[Users]

  "slick tables with hlist mappings" - {
    "should support inserts and selects via records" in {
      val db = Database.forConfig("h2")

      val user = ("their_id" ->> 1L) :: ("their_email" ->> "dave@example.com") :: HNil

      val action = for {
        _   <- users.schema.create
        _   <- users += user
        ans <- users.result.head
        _   <- users.schema.drop
      } yield ans

      whenReady(db.run(action)) { result =>
        result shouldBe user
        result("their_id") shouldBe 1L
        result("their_email") shouldBe "dave@example.com"
      }
    }
  }
}
