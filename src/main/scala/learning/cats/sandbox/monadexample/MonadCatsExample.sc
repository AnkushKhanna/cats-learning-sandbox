import cats.Monad
import cats.instances.list._
import cats.instances.option._

val opt1 = Monad[Option].pure(3)

val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))

val opt3 = Monad[Option].map(opt2)(a => a * 100)

val list1 = Monad[List].pure(10)

val list2 = Monad[List].flatMap(List(1, 5, 10, 50))(a => List(a * 10))

val list3 = Monad[List].map(List(1, 4, 5))(a => a * 10)


import cats.instances.option._
import cats.syntax.applicative._
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.language.higherKinds

1.pure[Option]

def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] = {
  for {
    x <- a
    y <- b
  } yield x * x + y * y
}

sumSquare(Option(5), Option(10))
sumSquare(List(1, 5), List(5, 10))

import cats.Id
sumSquare(3: Id[Int], 4: Id[Int])