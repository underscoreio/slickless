package slickless

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.Matchers
import org.scalatest.freespec.AnyFreeSpec

abstract class Spec extends AnyFreeSpec with Matchers with ScalaFutures {
  implicit val patience = PatienceConfig(timeout = Span(30, Seconds), interval = Span(250, Millis))
}



