
//////////// Smart Constructors ///////////
/*
sealed abstract case class Email private (value: String)

object Email {

  def checkEmail(value: String): Boolean = ???

  def fromString(value: String): Option[Email] =
    if(checkEmail(value)) Some(new Email(value){})
    else None
}
*/