// Algebraic Data Type

// Products
case class Person(name: String, age: Int)

// Sums
sealed trait Contact

case class Email(value: String)
  extends Contact

case class Phone(value: Phone)
 extends Contact

//////////// sample ////////////
final case class ID(value: Int)
final case class OrderId(value: String)
final case class OrderDetails(value: String)

final case class Event( id: ID, name: String, payload: Payload)

sealed trait Payload
object Payload {
  final case class Purchase(details: OrderDetails)
    extends Payload

  final case class Refund(orderId: OrderId)
    extends Payload
}

object ADTTest extends App {
  def changeName(event: Event, newName: String): Event =
    event.copy(name = newName)
}

