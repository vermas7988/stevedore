package stevedore

import munit.CatsEffectSuite
import cats.Id

class ExecutorTests extends CatsEffectSuite {

  val executor = Executor.instance[Either[Throwable, *]]

  test("build and run empty image") {
    val build = executor.build(Build.empty)
    val result = build.flatMap(executor.run).map(_.getAll)
    assertEquals(
      result,
      Right(Map.empty)
    )
  }
}