resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % ("0.1.1-"+v))

resolvers += {
  val typesafeRepoUrl = new java.net.URL("http://repo.typesafe.com/typesafe/releases")
  val pattern = Patterns(false, "[organisation]/[module]/[sbtversion]/[revision]/[type]s/[module](-[classifier])-[revision].[ext]")
  Resolver.url("Typesafe Repository", typesafeRepoUrl)(pattern)
}

libraryDependencies <<= (libraryDependencies, sbtVersion) { (deps, version) =>
  deps :+ ("com.typesafe.sbteclipse" %% "sbteclipse" % "1.3-RC2" extra("sbtversion" -> version))
}

resolvers += "sonatype.repo" at "https://oss.sonatype.org/content/groups/public"

resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"

libraryDependencies <+= sbtVersion(v => "eu.getintheloop" %% "sbt-cloudbees-plugin" % ("0.3.1_"+v))

libraryDependencies += "com.github.mpeltonen" %% "sbt-idea" % "0.10.0"

