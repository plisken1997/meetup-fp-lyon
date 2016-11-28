object MyEitherList {
  def main(args: Array[String]): Unit = {

    type Username = String

    type Email = String

    case class MeetupUser(
                           firstname: Username,
                           lastname: Username,
                           email: Email,
                           age: Option = None,
                           mobile: Option = None)

    trait ValidationError

    case class UsernameValidationError[Username](field: String, value: Username, error: String) extends ValidationError

    case class EmailValidationError[Email](field: String, value: Email, error: String) extends ValidationError

    def nameValidator[A](allowedValues: List[String])(field: String, name: A): Either[ValidationError, (String, A)] = {
      if (allowedValues.contains(name)) Left(UsernameValidationError(field, name, s"$name is not allowed !"))
      else Right((field, name))
    }

    def emailValidator(field: String, email: Email): Either[ValidationError, (String, Email)] = {
      "^[\w_-]+\.[\w_-]+@[a-z]{2, 6}$".r
        .findFirstIn(email)
        .fold[Either[ValidationError, (String, Email)]](
          Left(EmailValidationError(field, email, s"$email does not match a valid email pattern"))
        )(e => Right((field, email)))
    }

    val userInput = MeetupUser("Ivan", "Drago", "ivan.drago@rocky-iv.org")

    val badguys = List("Ivan", "Drago", "MrT")

    val goodguyValidator = nameValidator(badguys) (_, _)

    def validate[A](field: String, value: A): Either[ValidationError, (String, A)] = {
      value match {
        case u: Username <: A => goodguyValidator[A](field, u.asInstanceOf[Username])
        case e: Email => emailValidator(field, e)
        case _ => Right((field, value))
      }
    }

  }
}