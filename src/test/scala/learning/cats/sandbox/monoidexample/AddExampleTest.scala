package learning.cats.sandbox.monoidexample

import cats.instances.int._
import cats.instances.option._
import org.scalatest.FlatSpec

class AddExampleTest extends FlatSpec {

  "add" should "sum all ints" in {
    assert(AddExample.add(List(5, 4, 3, 2, 1))(cats.Monoid[Int]) === 15)
  }

  it should "sum all Option[Int]" in {

    val testList = List(5, 4, 3, 2, 1).map(Option(_))
    assert(AddExample.add(testList)(cats.Monoid[Option[Int]]) === Some(15))

    assert(AddExample.add(List.empty[Option[Int]])(cats.Monoid[Option[Int]]) === None)
  }

  it should "sum all Order" in {
    case class Order(totalCost: Double, quantity: Double)

    val orderMonoid = new cats.Monoid[Order] {
      override def empty: Order = Order(0.0, 0.0)

      override def combine(x: Order, y: Order): Order =
        Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    }

    val orderList = List(
      Order(5, 1),
      Order(15, 2),
      Order(45, 3))

    assert(AddExample.add(orderList)(orderMonoid) === Order(65, 6))

    assert(AddExample.add(List.empty[Order])(orderMonoid) === Order(0.0, 0.0))
  }

}
