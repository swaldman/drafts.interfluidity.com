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
