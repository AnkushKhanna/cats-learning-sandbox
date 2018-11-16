package learning.cats.sandbox.monoidexample
import cats.instances.int._

object CatsMonoidExample extends App {

  val i = cats.Monoid[Int]
  println(i.empty)
  println(i.combine(1, 2))
}
