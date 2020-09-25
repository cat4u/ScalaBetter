object Implicits {
  //import Personn.stringToPerson

  def sayHello(implicit name: String): String = s"Hello $name"

  class Person(val name: String)
  object Person {
    implicit val person: Person = new Person("User")
  }

  def sayHellop(implicit person: Person): String = s"Hello ${person.name}"

  // implicit conversions
  case class Personn(name: String){
    def greet: String = s"Hello! I'm $name"
  }

//  object Personn {
//    implicit def stringToPerson(str: String): Personn = Personn(str)
//  }
  implicit class StringToPerson(str: String) {
    def greet: String = s"hello! i'm $str"
  }

  def main(args: Array[String]): Unit = {
    implicit val name: String = "Joe"
    println(sayHello)

    println(sayHellop)

    println("Joe".greet)
  }

}
