package learning.cats.sandbox

import org.scalatest.FlatSpec

class CatTest extends FlatSpec {

  "Cat" should "return correct printable String" in {
    val cat = Cat("fluffy", 10, "white")
    import Cat._
    import PrintableInstance.Ops._

    assert("fluffy is a 10 year-old white cat." === cat.formatOps)
  }

}
