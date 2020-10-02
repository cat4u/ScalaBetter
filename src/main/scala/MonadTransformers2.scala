import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}


object MonadTransformers2 extends App {

  class GenerationException(number: Long, message: String)
    extends Exception(message)

  object NumberProducer {
    import cats.syntax.either._

    def queryNextNumber: Future[Either[GenerationException, Long]] = Future {
      Either catchOnly[GenerationException] {
        val source = Math.round(Math.random * 100)
        if (source <= 90) source
        else throw new GenerationException(source,
          "The generated number is too big!")
      }
    }
  }

  /*implicit val taskMonad = new Monad[Future] {
    override def flatMap[A, B](fa: Future[A])(f: (A) => Future[B]): Task[B] = fa.flatMap(f)

    override def pure[A](x: A): Future[A] = Future(x)

    override def tailRecM[A, B](a: A)(f: A => Future[Either[A, B]]): Future[B] =
      Future(f(a)).flatMap {
        case Left(continueA) => tailRecM(continueA)(f)
        case Right(b) => Future(b)
      }
  }*/

  override def main(args: Array[String]): Unit = {
    val num1TX = NumberProducer.queryNextNumber
    val num2TX = NumberProducer.queryNextNumber

    val resultTXT = for {
      num1 <- EitherT(num1TX)
      num2 <- EitherT(num2TX)
    } yield num1 + num2

    resultTXT.value.onComplete {
      case Success(value) => println {value}
      case Failure(exception) => println {exception}
    }
    // println(s"Result: ${resultTXT.value}")

    Thread.sleep(2000)
  }
}
