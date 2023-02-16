package com.interfluidity.drafts

import scala.collection.*

import unstatic.*
import unstatic.ztapir.*
import unstatic.ztapir.simple.*

import unstatic.*, UrlPath.*

import java.nio.file.Path as JPath

import untemplate.Untemplate.AnyUntemplate

object DraftsSite extends ZTSite.SingleRootComposite( JPath.of("drafts/static") ):
  override val serverUrl : Abs    = Abs("https://drafts.interfluidity.com/")
  override val basePath  : Rooted = Rooted.root

  case class MainLayoutInput( renderLocation : SiteLocation, mainContentHtml : String, sourceUntemplates : immutable.Seq[AnyUntemplate] = immutable.Seq.empty )

  object MainBlog extends SimpleBlog:
    override type Site = DraftsSite.type
    override val site = DraftsSite.this
    override lazy val rssFeed = site.location( "/feed/index.rss" )
    override val feedTitle = "drafts — interfluidity"
    override val frontPage = site.location("/index.html")
    override val frontPageIdentifiers = super.frontPageIdentifiers ++ immutable.Set("home","homePage") // since we are using the blog as home
    override val maxFrontPageEntries = Some(5)
    override def entryUntemplates =
      IndexFilter.fromIndex( IndexedUntemplates )
        .inOrBeneathPackage("com.interfluidity.drafts.mainblog")
        .withNameLike( _.startsWith("entry_") )
        .untemplates
        .map( _.asInstanceOf[EntryUntemplate] )
    override def mediaPathPermalink( ut : untemplate.Untemplate[?,?] ) : MediaPathPermalink =
      import MediaPathPermalink.*
      overridable( yearMonthDayNameDir, ut )

    override def layoutEntry(input: Layout.Input.Entry): String = mainblog.layout_entry_html(input).text

    // overriding a def, but it's just a constant, so we override with val
    override val entrySeparator : String = partials.entry_separator_html().text

    // here the blog shares the sites main overall layout
    override def layoutPage(input: Layout.Input.Page): String =
      val mainLayoutInput = MainLayoutInput( input.renderLocation, input.mainContentHtml, input.sourceEntries.map( _.entryUntemplate ) )
      layout_main_html(mainLayoutInput).text

  // avoid conflicts, but...
  //   (1) early items in the lists take precedence over later items
  //   (2) endpoint bindings take precedence over location bindings
  //
  override val endpointBindingSources : immutable.Seq[ZTEndpointBinding.Source]     = immutable.Seq( MainBlog )

object DraftsSiteGenerator extends ZTMain(DraftsSite, "drafts-site")

