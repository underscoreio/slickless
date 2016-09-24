package slickless

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FreeSpec, Matchers}
import shapeless.{::, HNil, Generic}
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global

class GenShapeSpec extends FreeSpec with Matchers with ScalaFutures {
  implicit val patience = PatienceConfig(timeout = Span(1, Seconds), interval = Span(250, Millis))

  case class Address(id: Long, house: Int, street: String)

  class Addresss(tag: Tag) extends Table[Address](tag, "addresses") {
    def id     = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def house  = column[Int]("house")
    def street = column[String]("street")

    def * = (id :: house :: street :: HNil).mappedWith(Generic[Address])
  }

  lazy val addresses = TableQuery[Addresss]

  "slick tables with generic mappings" - {
    "should support inserts and selects" in {
      val db = Database.forConfig("h2")

      val address = Address(1L, 29, "Acacia Road")

      val action = for {
        _   <- addresses.schema.create
        _   <- addresses += address
        ans <- addresses.result.head
        _   <- addresses.schema.drop
      } yield ans

      whenReady(db.run(action)) { _ should equal(address) }
    }
  }
}
