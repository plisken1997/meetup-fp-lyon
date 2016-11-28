object MyCompose {

  def cutOn(rawContent: String, cut: Char): Array[String] = rawContent.split(cut)

  def takeFirst(content: Array[String]): Array[String] = content.map(_.trim().split(" ").head)

  def buildStr(content: Array[String], glue: String): String = content.mkString(glue)

  def main(args: Array[String]): Unit = {
    val intro = "Bienvenue au Meetup @ Link Value ! FP et apéro au menu ce soir ;-) ! Joie et amour à tous"
    println(intro)

    val build = buildStr(_: Array[String], " -> ")
    val take = takeFirst _
    val cut = cutOn (_: String, '!')

    val exe: String => String = build compose take compose cut

    println(exe(intro))

  }
}