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
      Related.Item( "##a-conversation-with-kevin-erdmann",        title = Some("A conversation with Kevin Erdmann") )      ::
      Nil
  object HardInformationSoftInformation extends Abstract:
    override val DefaultCategory = ("hard information, soft information")
    val Items =
      Related.Item( "https://www.interfluidity.com/v2/933.html",                 title = Some("Monday at the Treasury: an overlong exegesis") )               ::
      Related.Item( "##banks-should-fail-much-more-often",                       title = Some("Banks should fail much more often") )                          ::
      Related.Item( "##situated-vs-unsituated-virtues",                          title = Some("Situated versus unsituated virtues") )                         ::
      Related.Item( "##indirection-capitalism-ii",                               title = Some("Indirection and the character of capitalism (Part II)") )      ::
      Related.Item( "##seeing-like-a-ceo",                                       title = Some("Seeing like a CEO") )                                          ::
      Related.Item( "##hard-information-soft-information-groupthink-corruption", title = Some("Hard information, soft information, groupthink, corruption") ) ::
      Nil
  object TradeCapitalAccountProtectionism extends Abstract:
    override val DefaultCategory = ("trade and capital-account protectionism")
    val Items =
      Related.Item( "https://www.interfluidity.com/v2/26.html",                  title = Some("Global Imbalances — Warren Buffett’s Cap & Trade") )            ::
      Related.Item( "https://www.interfluidity.com/v2/53.html",                  title = Some("Countering currency manipulation with high deficit spending") ) ::
      Related.Item( "https://www.interfluidity.com/v2/67.html",                  title = Some("Two Cheers for Bernanke’s Speech on Trade") )                   ::
      Related.Item( "https://www.interfluidity.com/v2/540.html",                 title = Some("""Fixing “global imbalances” in three easy steps""") )          ::
      Related.Item( "https://www.interfluidity.com/v2/753.html",                 title = Some("Revaluing China") )                                             ::
      Related.Item( "##china-as-a-model",                                        title = Some("China as a model") )                                            ::
      Related.Item( "##if-we-weren't-idiots-bop-edition",                        title = Some("If we weren't idiots, Balance of Payments edition") )           ::
      Related.Item( "##keynesian-compromise",                                    title = Some("Keynesian Compromise") )                                        ::
      Related.Item( "##balance-as-a-norm",                                       title = Some("Balance as a norm") )                                           ::
      Related.Item( "##how-can-taxing-foreign-investors-balance-trade",          title = Some("How can taxing foreign investors balance trade?") )             ::
      Related.Item( "##overall-but-not-bilateral-balance",                       title = Some("Overall but not bilateral balance") )                           ::
      Related.Item( "##the-asset-side-of-the-balance-sheet",                     title = Some("The asset side of the balance sheet") )                         ::
      Nil

  object NewCollege extends Abstract:
    override val DefaultCategory = ("New College")
    val Items =
      Related.Item( "##new-college",                                             title = Some("New College") )       ::
      Related.Item( "##we-haunt",                                                title = Some("We haunt") )          ::
      Related.Item( "##masculine-virtues",                                       title = Some("Masculine Virtues") ) ::
      Nil



