package stevedore

import cats.{Applicative, ApplicativeThrow}
import cats.implicits.*

object ServerSideExecutor {
  def instance[F[_]: ApplicativeThrow]: Executor[F] = new Executor[F] {

    val emptyHash: Hash = Hash(Array.empty)

    override def build(build: Build): F[Hash] =
      (build == Build.empty)
      .guard[Option]
      .as(emptyHash).liftTo[F](new Throwable("Unsupported Build!"))

    override def run(hash: Hash): F[SystemState] =
      (hash == emptyHash)
        .guard[Option]
        .as(KVState(Map.empty))
        .liftTo[F](new Throwable("Unsupported hash!"))
  }

  private final case class KVState(getAll: Map[String, String]) extends SystemState
}