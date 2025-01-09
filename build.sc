import $meta._

import mill._
import mill.scalalib._

import $ivy.`com.mchange::untemplate-mill:0.1.4`
import untemplate.mill._

val UnstaticVersion = "0.3.3-SNAPSHOT"

object Dependency {
  val Unstatic             = ivy"com.mchange::unstatic:${UnstaticVersion}"
  val UnstaticZTapir       = ivy"com.mchange::unstatic-ztapir:${UnstaticVersion}"
}

object drafts extends RootModule with UntemplateModule {
  override def scalaVersion = "3.3.4"

  // supports Scala 3.2.1
  // override def ammoniteVersion = "2.5.6"

  // we'll build an index!
  override def untemplateIndexNameFullyQualified : Option[String] = Some("com.interfluidity.drafts.IndexedUntemplates")

  override def untemplateSelectCustomizer: untemplate.Customizer.Selector = { key =>
    var out = untemplate.Customizer.empty

    if (key.inferredPackage.indexOf("mainblog")>=0 && key.inferredFunctionName.startsWith("entry_")) {
      out = out.copy(extraImports=Seq("unstatic.*","com.interfluidity.drafts.*","com.interfluidity.drafts.DraftsSite.MainBlog","unstatic.ztapir.simple.UpdateRecord"))
    }

    // to customize, examine key and modify the customer
    // with out = out.copy=...
    //
    // e.g. out = out.copy(extraImports=Seq("draft.*"))

    out
  }

  override def ivyDeps = T {
    super.ivyDeps() ++
      Agg (
        Dependency.Unstatic,
        Dependency.UnstaticZTapir,
      )
  }
}


