package learning.cats.sandbox.monoidexample

trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}


object Monoid {
  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

object BooleanMonoids {

  implicit val andMonoid = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val orMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val xorMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = (x, y) match {
      case (true, true) => false
      case (false, false) => false
      case _ => true
    }
  }

}