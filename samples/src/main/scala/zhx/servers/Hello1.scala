package zhx.servers
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.blaze._
import zio._
import zio.interop.catz._
import zio.interop.catz.implicits._

object Hello1 extends App {

  def run(args: List[String]) =
    server.fold(_ => 1, _ => 0)

  val server = ZIO.runtime[Environment]
    .flatMap {
      implicit rts =>
        BlazeServerBuilder[Task]
          .bindHttp(8080, "localhost")
          .withHttpApp(Hello1Service.service)
          .serve
          .compile
          .drain
    }

}