import cats.data.Writer
import cats.instances.vector._

import scala.concurrent.{Await, Future}

Writer(Vector(
  "It was the best of times",
  "it was the worst of times"
), 1859)

import cats.syntax.writer._ // for tell
Vector("msg1", "msg2", "msg3").tell

val a = Writer(Vector("msg1", "msg2", "msg3"), 123)

val b = 123.writer(Vector("msg1", "msg2", "msg3"))

a.value

a.written

b.run

def slowly[A](body: => A) =
  try body finally Thread.sleep(100)

def factorial(n: Int): Int = {
  val ans = slowly(if (n == 0) 1 else n * factorial(n - 1));
  println(s"fact $n $ans")

  ans
}

//factorial(5)

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

Await.result(Future.sequence(Vector(
  Future(factorial(5)),
  Future(factorial(5))
)), 5.seconds)


type Logged[A] = Writer[Vector[String], A]

import cats.instances.vector._
import cats.syntax.applicative._

def factorialLogged(n: Int): Logged[Int] =
  for {
    ans <- if (n == 0) 1.pure[Logged] else slowly(factorialLogged(n - 1).map(_ * n))
    _ <- Vector(s"fact $n $ans").tell
  } yield ans

Await.result(Future.sequence(Vector(
  Future(factorialLogged(3).run),
  Future(factorialLogged(3).run)
)), 5.seconds)


val testA = 1.pure[Logged]
val n = 0
Vector(s"fact $n $testA").tell