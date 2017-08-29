
// @GENERATOR:play-routes-compiler
// @SOURCE:/media/rutvij/Projects/sbt/play-java-seed/conf/routes
// @DATE:Sat Aug 26 23:10:24 EDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
