object MyEither {

  def displayAge(user: MeetupGuest): MeetupGuest = {
    println(s"${user.name} is ${user.age}")
    user
  }

  def displayUser(user: MeetupGuest): MeetupGuest = {
    println(s"Welcome ${user.name} !")
    user
  }

  // ~ interface
  trait UnbelievableError {
    def getError: String
  }

  // erreur retournée si le meetupGuest n'est pas trouvé
  case class FindMeetupGuestError(id: Int, cause: String) extends UnbelievableError {
    def getError: String = s"oups ! ${cause}"
  }

  // erreur retournée si le meetupGuest n'a pas de bestfriend
  case class FindBestfriendError(id: Int, cause: String) extends UnbelievableError {
    def getError: String = s"Mince :'( ${cause}"
  }

  // find guest by guestId
  def findMeetupGuest(guestId: Int): Either[UnbelievableError, MeetupGuest] =
    guests
      .find(_.id == guestId)
      .headOption
      .fold[Either[FindMeetupGuestError, MeetupGuest]](Left(FindMeetupGuestError(guestId, s"$guestId not found in findMeetupGuest"))) {
      guest => Right(guest)
    }

  // returns user's best friend
  def findBestFriend(user: MeetupGuest): Either[UnbelievableError, MeetupGuest] =
    user.bestFriend
        .map(findMeetupGuest)
        .getOrElse(Left(FindBestfriendError(user.id, s"no besfriend found for ${user.name}[${user.id}] in findMeetupGuest")))

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

  def chainBestFriends(userId: Int) = {
    findMeetupGuest(userId)
      .map(displayAge) // Rocky is 38
      .map(displayUser) // Welcome Rocky !
      .flatMap(findBestFriend)
      .map(displayAge) // Appolo is 42
      .map(displayUser) // Welcome Appolo !
      .flatMap(findBestFriend) // None
      .map(displayAge) // not computed
      .map(displayUser) // not computed
  }

  def main(args: Array[String]): Unit = {
    ln
    val userId = 1
    val lastBestFriend = chainBestFriends(userId)
    ln

    lastBestFriend match {
      case Right(guest: MeetupGuest) => println(s"process ends with ${guest.name}")
      case Left(err: FindBestfriendError) => println(s"${err.getError} #asocial")
      case Left(err: FindMeetupGuestError) => println(s"${err.getError} #mystery")
      case Left(err: UnbelievableError) => println(s"what the hell %?!^# ${err.getError} #effetDemo")
    }

    ln
  }

  def ln = println("\n")
}