name := """hello-play-2_3-scala"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
   "mysql" % "mysql-connector-java" % "5.1.31",
  "com.typesafe.slick" %% "slick" % "2.0.2",
  "org.webjars" %% "webjars-play" % "2.3-M1",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "requirejs" % "2.1.11-1"
)

lazy val root = (project in file(".")).addPlugins(PlayScala)
