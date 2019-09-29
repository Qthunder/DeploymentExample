package example.deployment

import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

class Api[F[_] : Sync] extends Http4sDsl[F] {

  val routes: HttpRoutes[F] = HttpRoutes.of {
    case GET -> Root / "ping" => Ok("pong")
  }

}
