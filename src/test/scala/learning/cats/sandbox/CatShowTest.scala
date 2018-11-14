package learning.cats.sandbox

import org.scalatest.FlatSpec
import cats.syntax.show._

class CatShowTest extends FlatSpec {

  "Cat" should "return correct printable String" in {
    val cat = CatShow("fluffy", 10, "white")
    import CatShow._
    assert("fluffy is a 10 year-old white cat." === cat.show)
  }
}
