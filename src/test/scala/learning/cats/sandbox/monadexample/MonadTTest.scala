package learning.cats.sandbox.monadexample

import org.scalatest.FlatSpec

class MonadTTest extends FlatSpec {

  import MonadT._

  "Monad Option" should "return Some(_) with flatMap when Some(_) is supplied" in {
    val opt1 = Option(3)

    assert(optionMonad.flatMap(opt1)(a => Some(3 * 10)) === Some(30))
    assert(optionMonad.map(opt1)(a => 3 * 10) === Some(30))
  }

  it should "return None with flatMap when None is supplied" in {
    val opt1 = None

    assert(optionMonad.flatMap(opt1)(a => Some(3 * 10)) === None)
    assert(optionMonad.map(opt1)(a => 3 * 10) === None)

  }

  "Monad List" should "return List(...) with function when List(...) is supplied" in {
    val list1 = List(5, 10, 15, 20)

    assert(listMonad.flatMap(list1)(a => List(a + 5)) === List(10, 15, 20, 25))
    assert(listMonad.map(list1)(a => a + 5) === List(10, 15, 20, 25))
  }

  it should "return List.empty with function when List.empty is supplied" in {
    val list1 = List.empty[Boolean]

    assert(listMonad.flatMap(list1)(a => List(a)) === List.empty[Boolean])
    assert(listMonad.map(list1)(a => a) === List.empty[Boolean])
  }

}
