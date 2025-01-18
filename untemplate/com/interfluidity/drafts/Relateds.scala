import unstatic.ztapir.simple.Related

object Relateds:
  trait Abstract:
    def Items : Seq[Related.Item]
    val DefaultCategory : String = "here"
    def excluding( excludeUid : String, category : String = DefaultCategory ) : Related =
      Related(
        base = "Related",
        category = Some(category),
        items = Items.filter( item => ("##" + excludeUid) != item.href )
      )
  object ChinaIndustrialPolicy extends Abstract:
    override val DefaultCategory = ("industrial policy")
    val Items =
      Related.Item( "##industrial-policy-and-ecosystems",             title = Some("Industrial policy and ecosystems") )             ::
      Related.Item( "##china-as-a-model",                             title = Some("China as a model") )                             ::
      Related.Item( "##form-of-the-subsidy",                          title = Some("Competition and the form of the subsidy") )      ::
      Related.Item( "##income-driven-repayment-of-fixed-capital",     title = Some("Income driven repayment of fixed capital") )     ::
      Related.Item( "##abundance-is-overcapacity",                    title = Some("Abundance is overcapacity") )                    ::
      Related.Item( "##industrial-policy-without-national-champions", title = Some("Industrial policy without national champions") ) ::
      Related.Item( "##private-firms-public-industries",              title = Some("Private firms, public industries") )             ::
      Nil
  object Housing extends Abstract:
    override val DefaultCategory = ("housing")
    val Items =
      Related.Item( "https://www.interfluidity.com/v2/3036.html", title = Some("Zoning laws and property rights") )        ::
      Related.Item( "https://www.interfluidity.com/v2/6287.html", title = Some("Home is where the cartel is") )            ::
      Related.Item( "https://www.interfluidity.com/v2/6487.html", title = Some("There’s no substitute for a substitute") ) ::
      Related.Item( "https://www.interfluidity.com/v2/8772.html", title = Some("Microcities") )                            ::
      Related.Item( "##only-the-state-can-house-us",              title = Some("Only the state can house us") )            ::
      Related.Item( "##yimboree",                                 title = Some("Yimboree") )                               ::
      Related.Item( "##it-isnt-sprawl-if-its-dense",              title = Some("It isn't sprawl of it's dense") )          ::
      Nil
  object HardInformationSoftInformation extends Abstract:
    override val DefaultCategory = ("hard information, soft information")
    val Items =
      Related.Item( "https://www.interfluidity.com/v2/933.html", title = Some("Monday at the Treasury: an overlong exegesis") )          ::
      Related.Item( "##banks-should-fail-much-more-often",       title = Some("Banks should fail much more often") )                     ::
      Related.Item( "##situated-vs-unsituated-virtues",          title = Some("Situated versus unsituated virtues") )                    ::
      Related.Item( "##indirection-capitalism-ii",               title = Some("Indirection and the character of capitalism (Part II)") ) ::
      Related.Item( "##seeing-like-a-ceo",                       title = Some("Seeing like a CEO") )                                     ::
      Nil

