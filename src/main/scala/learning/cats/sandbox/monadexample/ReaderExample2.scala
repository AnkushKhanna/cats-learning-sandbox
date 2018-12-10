package learning.cats.sandbox.monadexample

import cats.data.Reader

object ReaderExample2 extends App {

  case class Db(
                 usernames: Map[Int, String],
                 passwords: Map[String, String]
               )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(
                     username: String,
                     password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  import cats.syntax.applicative._

  def checkLogin(
                  userId: Int,
                  password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      passwordOk <- username.map { username =>
        checkPassword(username, password)
      }.getOrElse {
        false.pure[DbReader]
      }
    } yield passwordOk


  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )
  val passwords = Map(
    "dade" -> "zerocool",
    "kate" -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)
  checkLogin(1, "zerocool").run(db)
  // res10: cats.Id[Boolean] = true
  checkLogin(4, "davinci").run(db)
  // res11: cats.Id[Boolean] = false
}
