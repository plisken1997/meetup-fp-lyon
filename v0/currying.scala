import scala.io.StdIn.readLine

object MyCurrying {

  def ln = println("\n")

  def main(args: Array[String]): Unit = {
    println("Meetup @ Link Value")

    def calculate[Int](op1: Int)(op2: Int)(op: (Int, Int) => Int): Int = op(op1, op2)

    def sum(a: Int, b: Int) = a + b
    def prod(a: Int, b: Int) = a * b
    def div(a: Int, b: Int): Int = Math.round(a / b)
    def sub(a: Int, b: Int) = a - b

    // read first value from terminal
    val first = readLine("What is the first operand ?").trim.toInt
    val initCalculate = calculate(first) _

    // read second value from terminal
    val second = readLine("What is the second operand ?").trim.toInt
    val doCalculate = initCalculate(second) (_)

    println(s"operation $first + $second produces ${doCalculate(sum)}")
    println(s"operation $first * $second produces ${doCalculate(prod)}")
    println(s"operation round($first / $second) produces ${doCalculate( div)}")
    println(s"operation $first - $second produces ${doCalculate(sub)}")

    ln
  }
}
