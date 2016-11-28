import scala.io.StdIn.readLine

object MyPartialApplication {

  def ln = println("\n")

  def main(args: Array[String]): Unit = {
    println("Meetup @ Link Value")

    def calculate[Int](op1: Int, op2: Int, op: (Int, Int) => Int): Int = op(op1, op2)

    def prod(a: Int, b: Int) = a * b

    val first = 10

    // calculate is partially applied ; doProductTen is type of Int => Int
    val doProductTen = calculate(first, _: Int, prod)

    // read second value from terminal
    val second = readLine("What is the second operand ? ").trim.toInt

    println(s"operation $first * $second produces ${doProductTen(second)}")

    ln
  }
}
