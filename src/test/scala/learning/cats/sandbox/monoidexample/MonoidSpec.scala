package learning.cats.sandbox.monoidexample

trait MonoidSpec[A] {
  def identityLaw(n: A)(implicit m: Monoid[A]) = {
    m.combine(n, m.empty) == n &&
      m.combine(m.empty, n) == n
  }

  def associativeLaw(x: A, y: A, z: A)(implicit m: Semigroup[A]) = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }
}
