package learning.cats.sandbox.monoidexample

import cats.syntax.semigroup._

object AddExample {
  def add[A](i: List[A])(implicit monoid: cats.Monoid[A]): A =
    i.foldLeft(monoid.empty)(_ |+| _)
}
