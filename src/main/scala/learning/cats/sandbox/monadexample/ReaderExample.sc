import cats.data.Reader

case class Cat(name: String, favoriteFood: String)

// defined class Cat
val catName: Reader[Cat, String] =
  Reader(cat => cat.name)


catName.run(Cat("Garfield", "fish"))

val greetKitty: Reader[Cat, String] =
  catName.map(n => s"Hello $n")

greetKitty.run(Cat("Garfield", "fish"))

val feedKitty: Reader[Cat, String] =
  Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

val greetAndFeed: Reader[Cat, String] =
  for {
    g <- greetKitty
    f <- feedKitty
  } yield s"$g, $f"

greetAndFeed(Cat("Garfield", "fish"))


// Example

case class Db(
               usernames: Map[Int, String],
               passwords: Map[String, String]
             )

//type DbReader[A] = Reader[Db, A]

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
  username   <- findUsername(userId)
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