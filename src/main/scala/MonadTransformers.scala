import cats.data.OptionT
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object MonadTransformers {

  def generateNum: Future[Option[Long]] = Future {
    val source = Math.round(Math.random * 100)
    if(source <= 60) Some(source) else None
  }

  def main(args: Array[String]): Unit = {
    val maybeNum1F = generateNum
    val maybeNum2F = generateNum

    // val result = maybeNum1F + maybeNum2F --> does not work

    val resultFOT = for {
      num1 <- OptionT(maybeNum1F)
      num2 <- OptionT(maybeNum2F)
    } yield {
      num1 + num2
    }

    resultFOT.value.onComplete {
      case Success(value) => println(value)
      case Failure(exception) => println(exception)
    }

    Thread.sleep(1000)

  }



}
