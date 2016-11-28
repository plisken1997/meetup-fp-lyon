object MyEither {

  def main(args: Array[String]): Unit = {

    case class MeetupUser(id: Int, name: String, age: Int, bestFriend: Option[Int] = None)

    trait MyError {
      def getError: String
    }

    case class UserNotFound(id: Int) extends MyError {
      def getError = s"User $id not found"
    }

    case class NothingToDo(cause: String) extends MyError {
      def getError = s"$cause"
    }

    val users = List(
      MeetupUser(1, "Rocky", 32, Some(2)),
      MeetupUser(2, "Appolo", 27)
    )

    def findMeetupUser(userId: Int): Either[MyError, MeetupUser] = {
      users
        .find(_.id == userId)
        .headOption
        .fold[Either[UserNotFound, MeetupUser]](Left(UserNotFound(userId))) { user =>
          Right(user)
        }
    }

    def findBestFriend(user: MeetupUser): Either[MyError, MeetupUser] = {
      user.bestFriend match {
        case Some(id) => findMeetupUser(id)
        case _ => Left(NothingToDo(s":'( no friend for ${user.name}"))
      }
    }

    def displayAge(user: MeetupUser): MeetupUser = {
      println(s"${user.name} is ${user.age}")
      user
    }

    def displayUser(user: MeetupUser): MeetupUser = {
      println(s"Welcome ${user.name} !")
      user
    }

    val userId = 1

    val res = findMeetupUser(userId)
      .map(displayAge) // Rocky is 32
      .map(displayUser) // Welcome Rocky !
      .flatMap(findBestFriend)
      .map(displayAge) // Appolo is 27
      .map(displayUser) // Welcome Appolo !
      .flatMap(findBestFriend) // None
      .map(displayAge) // not computed
      .map(displayUser) // not computed

    res match {
      case Left(err: MyError) => println(s"Something went wrong : ${err.getError}")
      case _ => println("Ends with success !")
    }
  }
}
