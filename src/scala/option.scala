object MyOption {

  def main(args: Array[String]): Unit = {

    val scala = "scala"
    val java = "java"
    val php = "php"
    val rust = "rust"

    case class MeetupGuest(
                            id: Int,
                            name: String,
                            age: Int,
                            techno: List[String],
                            bestFriend: Option[Int] = None)

    val guests = List(
      MeetupGuest(1, "Rocky", 38, List(scala, java), Some(2)),
      MeetupGuest(2, "Apollo", 42, List(rust, php, java), None),
      MeetupGuest(3, "Adrienne", 34, List(php, rust), Some(1)),
      MeetupGuest(4, "Drago", 26, List(rust), None),
      MeetupGuest(5, "Mary Anne", 32, List(java, php), Some(2))
    )

    def displayAge(user: MeetupGuest): MeetupGuest = {
      println(s"${user.name} is ${user.age}")
      user
    }

    def displayUser(user: MeetupGuest): MeetupGuest = {
      println(s"Welcome ${user.name} !")
      user
    }

    def findMeetupGuest(userId: Int): Option[MeetupGuest] = guests.find(_.id == userId).headOption

    def findBestFriend(user: MeetupGuest): Option[MeetupGuest] = user.bestFriend.flatMap(findMeetupGuest)

    val userId = 1

    val lastBestFriend =
      findMeetupGuest(userId)
        .map(displayAge) // Rocky is 38
        .map(displayUser) // Welcome Rocky !
        .flatMap(findBestFriend)
        .map(displayAge) // Appolo is 42
        .map(displayUser) // Welcome Appolo !
        .flatMap(findBestFriend) // None
        .map(displayAge) // not computed
        .map(displayUser) // not computed
        .getOrElse(MeetupGuest(0, "Empty user", 0, Nil))

    displayUser(lastBestFriend);

    println("\n")
  }
}