import scala.collection.mutable
import scala.io.StdIn.readLine

object LVFunc {

  val scala = "scala"
  val java = "java"
  val php = "php"
  val rust = "rust"

  implicit val config = Map("withJvm" -> List(java, scala))

  def fromConfig(env: String, confKey: String, defaultConfig: List[String] = Nil)(implicit config: Map[String, List[String]]): List[String] = {
    // dummy env ...
    config.getOrElse(confKey, defaultConfig)
  }

  def readConfiguration(env: String): (String, List[String]) => List[String] = {
    val cache: mutable.HashMap[String, List[String]] = mutable.HashMap()
    println("init cache")

    (confKey: String, defaultVal: List[String]) => {
      if (cache.contains(confKey)) {
        val fromCache = cache.getOrElse(confKey, defaultVal)
        println(s"from cache : [${fromCache.mkString(", ")}]")
        fromCache
      } else {
        val res = fromConfig(env, confKey)
        println(s"put $confKey with [${res.mkString(", ")}]")
        cache.put(confKey, res)
        res
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val confReader = readConfiguration("dev")
    confReader("withJvm", List())
    confReader("withJvm", List())
    confReader("withJvm", List())
  }

  def ln = println("")

}