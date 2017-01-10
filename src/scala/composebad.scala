import scala.io.StdIn.readLine

object LVComposeBad{

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


  def print(guests: List[MeetupGuest], format: MeetupGuest => String): Unit = {
    guests.foreach(u => println(format(u)))
  }

  def printUserWithTechno(guest: MeetupGuest) = {
    s"${printName(guest)}: ${printTechno(guest, " - ")}"
  }

  def printUserWithTechnoAndAge(guest: MeetupGuest) = {
    s"${printName(guest)}: ${printTechno(guest, " - ")} -> ${printAge(guest)}"
  }

  def printAgeWithTechnoAndUser(guest: MeetupGuest) = {
    s"${printAge(guest)} : ${printTechno(guest, " - ")} (${printName(guest)})"
  }

  def printTechnoAndUser(guest: MeetupGuest) = {
    s"${printTechno(guest, " - ")} (${printName(guest)})"
  }

  def fatPrint(guests: List[MeetupGuest]): Unit = {
    print(guests, printUserWithTechno)
    print(guests, printUserWithTechnoAndAge)
    print(guests, printAgeWithTechnoAndUser)
    print(guests, printTechnoAndUser)
  }


  def main(args: Array[String]): Unit = {

  }

  def ln = println("")
}

