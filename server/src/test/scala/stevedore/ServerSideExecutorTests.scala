package stevedore

import munit.CatsEffectSuite
import cats.Id

class ServerSideExecutorTests extends CatsEffectSuite {

  val executor = ServerSideExecutor.instance[Either[Throwable, *]]

  test("build and run empty image") {
    val build = executor.build(Build.empty)
    val result = build.flatMap(executor.run).map(_.getAll)
    assertEquals(
      result,
      Right(Map.empty)
    )
  }
}