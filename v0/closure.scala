import scala.collection.mutable

object MyClosure {

  def helloForALl(applicationName: String, participants: List[String]): List[String] = {
    val sayHello = (p: String) => s"Hi ${p}, welcome to $applicationName !"
    participants.map(sayHello)
  }

  def demoClosure: Unit = {
    val helloAll = helloForALl("Link Value meetup", List("Élise", "Jean-Christophe", "Irène", ""))
    println(helloAll.mkString("\n"))
  }

  def createCallback(var1: Int => Int): Int => String = {
    val z = 8
    p => s"${var1(p) + z}"
  }

  def demo: Unit = {
    val cb = createCallback(x => x * 4)
    val computed = cb(9)
    //    x => x * 4 + z
    //    9 => 9 * 4 + z
    //    9 => 36 + z
    //    9 => 36 + 8
    println(computed) // prints 44
  }

  def applyCallback[A, B](var1: Int => B): B = {
    var1(10)
  }

  def cube: (Int) => Int = {
    val cache = new mutable.HashMap[Int, Int]
    a: Int => cache.getOrElse[Int](a, {
      val r: Int = a * a * a
      cache.put(a, r)
      println(s"add `$a` into runtime cache")
      r
    })
  }

  def main(args: Array[String]): Unit = {
    println("Meetup @ Link Value")
    val toCube = cube
    val first = toCube(3)
    println(first)
    val again = toCube(3)
    println(again)
  }
}
