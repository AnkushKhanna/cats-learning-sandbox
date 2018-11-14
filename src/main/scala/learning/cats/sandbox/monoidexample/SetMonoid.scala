package learning.cats.sandbox.monoidexample

object SetMonoid {

  implicit def unionSet[A] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
  }

  implicit def intersectionSet[A] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x.intersect(y)
  }

  implicit def diffSet[A]= new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = (x diff y) union (y diff x)
  }

}
