object TailCall {

  def fact(f: Int, acc: Int): Int = {
    if (f == 1) acc
    else fact(f - 1, acc * f)
  }

  def demoTCO: Unit ={
    val f = fact(10, 1)
    println(s"$f") // prints 3628800
  }

  def main(args: Array[String]): Unit = {
    println("Meetup @ Link Value")
    demoTCO
  }
}
