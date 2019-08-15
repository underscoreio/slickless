import slickless.Spec
import shapeless.{HNil, Generic}
import slick.jdbc.H2Profile.api._
import slickless._
import scala.concurrent.ExecutionContext.Implicits.global

class Issue42 extends Spec {

  case class Department(id: Long, city: String)

  case class Employee(id: Long, dept1: Department, dept2: Department, email: String)

  class Employees(tag: Tag) extends Table[Employee](tag, "emps42") {
    def id              = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def departmentIdA   = column[Long]("dept_a_id")
    def departmentCityA = column[String]("dept_a_city")
    def departmentIdB   = column[Long]("dept_b_id")
    def departmentCityB = column[String]("dept_b_city")
    def email           = column[String]("email")

    def departmentA = (departmentIdA, departmentCityA).mapTo[Department]
    def departmentB = (departmentIdB, departmentCityB).mapTo[Department]

    def * = (id :: departmentA :: departmentB :: email :: HNil).mappedWith(Generic[Employee])
  }

  lazy val emps = TableQuery[Employees]

  "slick tables with nested case class mappings" - {
    "should support inserts and selects" in {
      val db = Database.forConfig("h2")

      val emp = Employee(1L, Department(21L, "Brighton"), Department(22L, "Hove"), "dave@example.org")

      val action = for {
        _   <- emps.schema.drop.asTry
        _   <- emps.schema.create
        _   <- emps += emp
        ans <- emps.result.head
        _   <- emps.schema.drop
      } yield ans

      whenReady(db.run(action)) { _ should equal(emp) }
    }
  }
}
