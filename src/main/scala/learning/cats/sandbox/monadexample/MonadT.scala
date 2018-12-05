package learning.cats.sandbox.monadexample

import scala.language.higherKinds

trait MonadT[F[_]] {

  def pure[A](value: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](a: F[A])(f: A => B): F[B] = flatMap(a)(a => pure(f(a)))
}

object MonadT {
  val optionMonad = new MonadT[Option] {
    override def pure[A](value: A): Option[A] = Option(value)

    override def flatMap[A, B](value: Option[A])(func: A => Option[B]): Option[B] = value match {
      case None => None
      case Some(a) => func(a)
    }
  }

  val listMonad = new MonadT[List] {
    override def pure[A](value: A): List[A] = List(value)

    override def flatMap[A, B](value: List[A])(func: A => List[B]): List[B] = value match {
      case Nil => Nil
      case head :: tail => func(head) ++ flatMap(tail)(func)
    }
  }

  type Id[A] = A

  val idMonad = new MonadT[Id] {
    override def pure[A](value: A): Id[A] = value

    override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)
  }
}
