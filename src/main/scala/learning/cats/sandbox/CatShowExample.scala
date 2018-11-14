package learning.cats.sandbox

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

//object CatShowExample {
//  val showString: Show[String] = Show[String]
//  val showInt: Show[Int] = Show[Int]
//}

case class CatShow(name: String, age: Int, color: String)

object CatShow {

  implicit val showCat: Show[CatShow] =
    (cat: CatShow) => {
      s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat."
    }
}