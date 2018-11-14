package learning.cats.sandbox

trait Printable[A] {
  def format(a: A): String
}

object PrintableInstance {
  implicit val printableString: Printable[String] = (a: String) => a

  implicit val printableInt: Printable[Int] = (a: Int) => a.toString

  object Ops {

    implicit class PrintableOps[A](a: A) {
      def formatOps(implicit printable: Printable[A]): String = {
        printable.format(a)
      }

      def print(implicit printable: Printable[A]) = {
        println(printable.format(a))
      }
    }

  }
}

case class Cat(name: String, age: Int, color: String)

object Cat {

  import PrintableInstance._
  import PrintableInstance.Ops._



  implicit val printableCat: Printable[Cat] =
    (cat: Cat) => {
      s"${cat.name.formatOps} is a ${cat.age.formatOps} year-old ${cat.color.formatOps} cat."
    }
}

