object MyAdt {

  def ln = println("\n")

  def main(args: Array[String]): Unit = {

    def filterAge(p: (String, Int), maxAge: Int) = p._2 < maxAge

    val foreverYoung = filterAge(_: (String, Int), 30)

    val l = List(("John", 24), ("Lucie", 28), ("JC", 33), ("Vero", 40))

    val before30 = l.filter(foreverYoung)

    println(s"youngs and beautifull peoples are ${before30.map(_._1).mkString(", ")}")

    val therese = Some(("Therese", 40))
    therese
      .filter(foreverYoung)
      .fold(println("Therese is #old"))(t => println(t._1))

    def whatsMyName(l: (String, Int)): String = l._1

    val names = l.map(whatsMyName)

    println(s"Welcome to ${names.mkString(", ")}")

    therese
      .map(t => ("Kelly", t._2 - 15))
      .filter(foreverYoung)
      .fold(println("Therese is #old"))(t => println(s"Welcome ${t._1}"))

    def splitGeneration(r: (List[String], List[String]), c: (String, Int)) =
      if (foreverYoung(c)) {
        (r._1 ++ List(c._1), r._2)
      } else {
        (r._1, r._2 ++ List(c._1))
      }

    val doSplitGeneration = l.foldLeft[(List[String], List[String])](List(), List()) _
    val youngAndOld = doSplitGeneration(splitGeneration)

    println(s"Forever young with ${youngAndOld._1.mkString(", ")} !")
    println(s"Oldies are in tha place with ${youngAndOld._2.mkString(", ")} !")

    ln
  }
}
