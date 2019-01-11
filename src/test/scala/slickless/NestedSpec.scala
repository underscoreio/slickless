package slickless

import shapeless.{HNil, Generic}
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global

class NestedSpec extends Spec {

  case class Department(id: Long, city: String)

  case class Employee(id: Long, dept: Department, email: String)

  class Employees(tag: Tag) extends Table[Employee](tag, "emps") {
    def id             = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def departmentId   = column[Long]("dept_id")
    def departmentCity = column[String]("dept_city")
    def email          = column[String]("email")

    def department = (departmentId, departmentCity).mapTo[Department]

    def * = (id :: department :: email :: HNil).mappedWith(Generic[Employee])
  }

  lazy val emps = TableQuery[Employees]

  "slick tables with nested case class mappings" - {
    "should support inserts and selects" in {
      val db = Database.forConfig("h2")

      val emp = Employee(1L, Department(42L, "Brighton"), "dave@example.org")

      val action = for {
        _   <- emps.schema.create
        _   <- emps += emp
        ans <- emps.result.head
        _   <- emps.schema.drop
      } yield ans

      whenReady(db.run(action)) { _ should equal(emp) }
    }
  }
}
