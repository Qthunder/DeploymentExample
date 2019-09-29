package example.deployment

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.syntax.kleisli._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    BlazeServerBuilder[IO]
      .withHttpApp(new Api[IO].routes.orNotFound)
      .bindHttp(8080, "0.0.0.0").serve.compile.lastOrError
  }
}
