package learning.cats.sandbox.monoidexample


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