object LVFunc {

  case class MeetupGuest(name: String, age: Int, techno: List[String])

  val scala = "scala"
  val java = "java"
  val php = "php"
  val rust = "rust"

  val guests = List(
    MeetupGuest("Rocky", 38, List(scala, java)),
    MeetupGuest("Apollo", 42, List(rust, php, java)),
    MeetupGuest("Adrienne", 34, List(php, rust)),
    MeetupGuest("Drago", 26, List(rust)),
    MeetupGuest("Mary Anne", 32, List(java, php))
  )

  def printAge(guest: MeetupGuest): String = s"I'm ${guest.age}"

  def printName(guest: MeetupGuest): String = s"Hello ${guest.name} !"

  def printTechno(guest: MeetupGuest, join: String): String = s"I craft with ${guest.techno.mkString(join)}"

  def print(guests: List[MeetupGuest], format: MeetupGuest => String): Unit =
    println(
      guests
        .foldRight[List[String]](Nil)((c, r) => format(c) :: r)
        .mkString("\n")
    )

  def main(args: Array[String]): Unit = {
    print(guests, printAge)
    print(guests, printName)
    print(guests, printTechno(_: MeetupGuest, " - "))
  }

  def ln = println("")

}