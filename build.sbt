name := "Eventsify"

scalaVersion := "2.9.1"

seq(com.github.siasia.WebPlugin.webSettings :_*)

seq(bees.RunCloudPlugin.deploymentSettings :_*)

// If using JRebel
jettyScanDirs := Nil

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers += "liftmodules repository" at "http://repository-liftmodules.forge.cloudbees.com/release/"

libraryDependencies ++= {
  val liftVersion = "2.4-M4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default",
    "net.liftmodules" %% "google-analytics" % (liftVersion+"-0.9"),
    "net.liftweb" % "lift-mongodb_2.9.1" % "2.4-M4" % "compile->default", // mongo-db
    "net.liftweb" % "lift-mongodb-record_2.9.1" % "2.4-M4" % "compile->default", // mongo-db-record
    "net.liftweb" % "lift-record_2.9.1" % "2.4-M4" % "compile->default"
    )
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  //"org.eclipse.jetty" % "jetty-webapp" % "7.3.0.v20110203" % "jetty", // For Jetty 7
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty,test", // For Jetty 6, add scope test to make jetty avl. for tests
  "org.scala-tools.testing" % "specs_2.9.0" % "1.6.8" % "test", // For specs.org tests
  "junit" % "junit" % "4.8" % "test->default", // For JUnit 4 testing
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default" // Logging
)

