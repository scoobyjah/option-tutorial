////an option can be thought of as a collection that is either full or empty
val maybeInt: Option[Int] = Some(10)
val none = Option.empty

val inc: Int => Option[Int] = (int: Int) => Some(int + 1)

//flatMap

maybeInt match {
  case None => None
  case Some(x) => inc(x)
}

maybeInt.flatMap(inc(_))

//map

maybeInt match {
  case None => None
  case Some(x) => Some(inc(x))
}

maybeInt.map(inc(_))

//isDefined

maybeInt match {
  case None => false
  case Some(_) => true
}

maybeInt.isDefined

//isempty
maybeInt match {
  case None => true
  case Some(_) => false
}

maybeInt.isEmpty

//forall
maybeInt match {
  case None => true       //interesting that None returns true
  case Some(x) => x < 11
}
val maybeString:Option[String] = Some("foo")
val noString:Option[String] = None
maybeString.forall(_ == "foo")
noString.forall(_ == "foo")

//exists
maybeInt match {
  case None => false
  case Some(x) => x < 11
}

maybeString.exists(_ == "foo")
noString.exists(_ == "foo")   //contrast with forall

//orElse
maybeString match {
  case None => Some("bar")
  case Some(x) => Some(x)
}

maybeString.orElse(Some("bar"))
noString.orElse(Some("bar"))

//getOrElse
maybeString match {
  case None => Some("bar")
  case Some(x) => x
}

maybeString.getOrElse(Some("bar"))
noString.getOrElse("bar")

//Constructing Options
//think about why options are useful.
// We use options to avoid dealing with nulls in Scala.
// How we construct an option depends on the situation.

//Generally, use Some(x), but sometimes e.g. if we are forced to confront a null in java, there may be a case where we want to construct an Option(x) to convert into the value we want.
//Constructing with Option(x) will do a null-check on x, Some(x) does not and may therefore contain null as a value.

Option(null)
Option("a")
Some(null)
Some("a")

//Nested options
//a cat may have given birth
case class Person(name: String, son: Option[Person])

//Abraham begat Isaac; and Isaac begat Jacob
//Jacob is Abraham's grandson
val abraham = Person("Abraham", Some(Person("Isaac", Some(Person("Jacob", Option.empty)))))

val grandson = abraham.son.flatMap(_.son).map(_.name)


//remember for comprehensions evaluate a sequential program.
// it's obvious here
// (and also now that we know this is the same as granddaughter)
// that this has to be done in sequence
val grandsonForComp = for {
  isaac <- abraham.son
  jacob <- isaac.son
} yield jacob.name

