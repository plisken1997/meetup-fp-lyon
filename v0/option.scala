object MyOption {

  def main(args: Array[String]): Unit = {

    case class MeetupUser(id: Int, name: String, age: Int, bestFriend: Option[Int] = None)

    val users = List(
      MeetupUser(1, "Rocky", 32, Some(2)),
      MeetupUser(2, "Appolo", 27)
    )

    def findMeetupUser(userId: Int): Option[MeetupUser] = users.find(_.id == userId).headOption

    def findBestFriend(user: MeetupUser): Option[MeetupUser] = user.bestFriend.map(findMeetupUser).flatten

    def displayAge(user: MeetupUser): MeetupUser = {
      println(s"${user.name} is ${user.age}")
      user
    }

    def displayUser(user: MeetupUser): MeetupUser = {
      println(s"Welcome ${user.name} !")
      user
    }

    val userId = 1

    findMeetupUser(userId)
      .map(displayAge) // Rocky is 32
      .map(displayUser) // Welcome Rocky !
      .flatMap(findBestFriend)
      .map(displayAge) // Appolo is 27
      .map(displayUser) // Welcome Appolo !
      .flatMap(findBestFriend) // None
      .map(displayAge) // not computed
      .map(displayUser) // not computed

  }
}