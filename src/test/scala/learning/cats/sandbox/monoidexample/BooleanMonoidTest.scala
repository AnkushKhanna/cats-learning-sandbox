package learning.cats.sandbox.monoidexample

import org.scalacheck._

object BooleanMonoidTest extends Properties("Boolean Monoid") with MonoidSpec[Boolean] {

  import Prop.forAll

  property("identity orBoolean") = forAll {
    n: Boolean =>
      identityLaw(n)(BooleanMonoids.orMonoid) &&
        identityLaw(n)(BooleanMonoids.andMonoid) &&
        identityLaw(n)(BooleanMonoids.xorMonoid)
  }

  property("associativity orBoolean") = forAll {
    (x: Boolean, y: Boolean, z: Boolean) =>
      assosicativeLaw(x, y, z)(BooleanMonoids.orMonoid) &&
        assosicativeLaw(x, y, z)(BooleanMonoids.andMonoid) &&
        assosicativeLaw(x, y, z)(BooleanMonoids.xorMonoid)
  }
}
