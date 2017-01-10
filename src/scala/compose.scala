import scala.io.StdIn.readLine

object LVCompose {

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

  implicit val config = Map("withJvm" -> List(java, scala))

  def fromConfig(confKey: String, defaultConfig: String = "")(implicit config: Map[String, List[String]]): List[String] = {
    config.getOrElse(confKey, defaultConfig)
  }

  def printAge(guest: MeetupGuest): String = s"${guest.age}"

  def printName(guest: MeetupGuest): String = s"${guest.name}"

  def printTechno(guest: MeetupGuest, join: String): String = s"${guest.techno.mkString(join)}"

  def main(args: Array[String]): Unit = {

    def withDecorator(
                       prefix: String,
                       suffix: String,
                       guest: MeetupGuest,
                       printMeetupGuest: MeetupGuest => String
                     ): String = s"$prefix ${printMeetupGuest(guest)} $suffix"

    val decoratedPrintName = withDecorator("| ", _: String, _: MeetupGuest, printName) // -> "| ? ?"
    val decoratedPrintAge = withDecorator("| ", _: String, _: MeetupGuest, printAge)
    val decoratedPrintTechno = withDecorator("| ", _: String, _: MeetupGuest, printTechno(_, ", "))

    def printer(guest: MeetupGuest)(start: String): String = {
      val boundedPrintName = decoratedPrintName(_: String, guest)
      val boundedPrintAge = decoratedPrintAge(_: String, guest)
      val boundedPrintTechno = decoratedPrintTechno(_: String, guest)
      val end = (s: String) => s"$s |"

      val cmp = boundedPrintName compose boundedPrintAge compose boundedPrintTechno compose end

      cmp(start)
    }

    def print(guests: List[MeetupGuest], format: MeetupGuest => String => String): Unit = {
      guests.foreach(u => println(format(u)("")))
    }

    print(guests, printer)
  }

  def ln = println("")
}

