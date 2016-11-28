object MyFunc {
  def main(args: Array[String]): Unit = {
    demoFunc
  }

  def demoFunc = {

    def calculate[B](op1: Int, op2: Int, op: (Int, Int) => B): B = op(op1, op2)

    def sum(a: Int, b: Int) = a + b
    def prod(a: Int, b: Int) = a * b
    def div(a: Int, b: Int): Double = a / b
    def sub(a: Int, b: Int) = a - b

    val opA = 10
    val opB = 20

    println(s"operation a + b produces ${calculate(opA, opB, sum)}") // 30
    println(s"operation a * b produces ${calculate(opA, opB, prod)}") // 200
    println(s"operation a / b produces ${calculate(opA, opB, div)}") // 0.5
    println(s"operation a - b produces ${calculate(opA, opB, sub)}") // -10

  }
}
