package learning.cats.sandbox.monoidexample

import org.scalacheck.{Prop, Properties}

object SetMonoidTest extends Properties("Set Monoid") with MonoidSpec[Set[String]] {

  import Prop.forAll

  property("identity set monoid") = forAll {
    n: Set[String] =>
      identityLaw(n)(SetMonoid.unionSet) &&
        identityLaw(n)(SetMonoid.diffSet)
  }

  property("associativity set monoid") = forAll {
    (x: Set[String], y: Set[String], z: Set[String]) =>
      associativeLaw(x, y, z)(SetMonoid.unionSet) &&
      associativeLaw(x, y, z)(SetMonoid.intersectionSet) &&
        associativeLaw(x, y, z)(SetMonoid.diffSet)
  }
}
