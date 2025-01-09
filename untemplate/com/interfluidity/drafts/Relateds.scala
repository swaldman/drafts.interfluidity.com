import unstatic.ztapir.simple.Related

object Relateds:
  def chinaIndustrialPolicyExcluding( excludeUid : String ) : Related =
    val items =
      Related.Item( "##industrial-policy-and-ecosystems",             title = Some("Industrial policy and ecosystems") )             ::
      Related.Item( "##china-as-a-model",                             title = Some("China as a model") )                             ::
      Related.Item( "##form-of-the-subsidy",                          title = Some("Competition and the form of the subsidy") )      ::
      Related.Item( "##income-driven-repayment-of-fixed-capital",     title = Some("Income driven repayment of fixed capital") )     ::
      Related.Item( "##abundance-is-overcapacity",                    title = Some("Abundance is overcapacity") )                    ::
      Related.Item( "##industrial-policy-without-national-champions", title = Some("Industrial policy without national champions") ) ::
      Related.Item( "##private-firms-public-industries",              title = Some("Private firms, public industries") )             ::
      Nil
    Related(
      base = "Related",
      category = Some("here"),
      items = items.filter( item => ("##" + excludeUid) != item.href )
    )
  def housingExcluding( excludeUid : String ) : Related =
    val items =
      Related.Item( "https://www.interfluidity.com/v2/3036.html", title = Some("Zoning laws and property rights") )        ::
      Related.Item( "https://www.interfluidity.com/v2/6287.html", title = Some("Home is where the cartel is") )            ::
      Related.Item( "https://www.interfluidity.com/v2/6487.html", title = Some("There’s no substitute for a substitute") ) ::
      Related.Item( "https://www.interfluidity.com/v2/8772.html", title = Some("Microcities") )                            ::
      Related.Item( "##only-the-state-can-house-us",              title = Some("Only the state can house us") )            ::
      Related.Item( "##yimboree",                                 title = Some("Yimboree") )                               ::
      Related.Item( "##it-isnt-sprawl-if-its-dense",              title = Some("It isn't sprawl of it's dense") )          ::
      Nil
    Related(
      base = "Related",
      category = Some("here"),
      items = items.filter( item => ("##" + excludeUid) != item.href )
    )
