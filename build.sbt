val commonSettings = Seq(
  scalaVersion := "3.2.1",
  scalacOptions -= "-Xfatal-warnings",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "3.4.8",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7"
  ) 
)

val shared = project.settings(commonSettings)

val server = project.settings(commonSettings).dependsOn(shared)

val client = project.settings(commonSettings).dependsOn(shared)

val root  = project
  .in(file("."))
  .settings(publish := {})
  .aggregate(server, client, shared)

