name := "TwitterAnalysisAufgabe"

version := "0.1"

scalaVersion := "2.11.12"

javacOptions ++= Seq("-source", "1.7", "-target", "1.8")

scalacOptions ++= Seq("-target:jvm-1.7")

parallelExecution in ThisBuild := false

libraryDependencies ++=Seq("org.apache.spark" %% "spark-core" % "2.3.2",
			   "org.apache.spark" %% "spark-sql" % "2.3.2",
			   "org.scalactic" %% "scalactic" % "3.0.5",
			   "org.scalatest" %% "scalatest" % "3.0.5" % "test",
			   "org.unbescape" % "unbescape" % "1.1.4.RELEASE",
			   "com.databricks" % "spark-xml_2.11" % "0.4.1",
			    "junit" % "junit" % "4.12" % "test",
			    "org.scala-lang.modules" %% "scala-xml" % "1.0.1"
			   )
