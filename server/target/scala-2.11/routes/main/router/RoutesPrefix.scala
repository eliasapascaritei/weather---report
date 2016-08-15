
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/elias/workspace/Weather Report - scalajs /server/conf/routes
// @DATE:Mon Aug 15 18:56:18 EEST 2016


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
