package stevedore

enum Command {
  case Build(build: stevedore.Build)
  case Run(hash: Hash)
}

case class Build(
  base: Build.Base,
  commands: List[Build.Command],
)

object Build {

  enum Base {
    case EmptyImage
    case ImageReference(hash: Hash)
  }

  enum Command {
    case Upsert(key: String, value: String)
    case Delete(key: String)
  }

  def empty = Build(Build.Base.EmptyImage, Nil)
}

case class Hash(value: Array[Byte])
