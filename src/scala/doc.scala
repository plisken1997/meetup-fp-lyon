object LVDoc{

  def main(args: Array[String]): Unit = {

    // assigner une variable
    val welcome = "Welcome to Link Value :-)"

    // déclaration d'une classe immuable
    case class MeetupGuest(name: String, age: Int, techno: List[String])

    // instance de la classe
    val guest = MeetupGuest("L. Freamon", 46, List("java", "php", "javascript"))

    // fonction
    def square(toSquare: Int): Int = toSquare * toSquare

    // lambda/fonction anonyme
    val cube = (toCube: Int) => toCube * toCube * toCube

    // fonction avec fonction en paramètre
    val compute = (operande: Int, operation: Int => Int) => operation(operande)

    println(compute(3, cube))
    // prints `27`

  }
}