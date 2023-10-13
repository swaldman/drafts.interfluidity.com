package com.interfluidity.drafts

import scala.collection.*

import unstatic.*
import unstatic.ztapir.*
import unstatic.ztapir.simple.*

import unstatic.*, UrlPath.*

import java.nio.file.Path as JPath

import java.time.ZoneId

import untemplate.Untemplate.AnyUntemplate

object DraftsSite extends ZTSite.SingleStaticRootComposite( JPath.of("drafts/static") ):
  override val serverUrl : Abs    = Abs("https://drafts.interfluidity.com/")
  override val basePath  : Rooted = Rooted.root

  case class MainLayoutInput( renderLocation : SiteLocation, mainContentHtml : String, sourceUntemplates : immutable.Seq[AnyUntemplate] = immutable.Seq.empty )

  object MainBlog extends SimpleBlog:
    override type Site = DraftsSite.type
    override val site = DraftsSite.this
    override lazy val rssFeed = site.location( "/feed/index.rss" )
    override val feedTitle = "drafts \u2014 interfluidity" // \u2014 is unicode &mdash;, works the same if I just include '—' char
    override val frontPage = site.location("/index.html")
    override val frontPageIdentifiers = super.frontPageIdentifiers ++ immutable.Set("home","homePage") // since we are using the blog as home
    override val maxFrontPageEntries = Some(5)
    override val timeZone = ZoneId.of("America/New_York")
    override def entryUntemplates =
      IndexFilter.fromIndex( IndexedUntemplates )
        .inOrBeneathPackage("com.interfluidity.drafts.mainblog")
        .withNameLike( _.startsWith("entry_") )
        .untemplates
        .map( _.asInstanceOf[EntryUntemplate] )
    override def mediaPathPermalink( ut : untemplate.Untemplate[?,?] ) : MediaPathPermalink =
      import MediaPathPermalink.*
      overridable( yearMonthDayNameDir(timeZone), ut )

    override def layoutEntry(input: Layout.Input.Entry) : String = mainblog.layout_entry_html(input).text

    // overriding a def, but it's just a constant, so we override with val
    override val entrySeparator : String = partials.entry_separator_html().text

    // here the blog shares the sites main overall layout
    override def layoutPage(input: Layout.Input.Page): String =
      val mainLayoutInput = MainLayoutInput( input.renderLocation, input.mainContentHtml, input.sourceEntries.map( _.entryUntemplate ) )
      layout_main_html(mainLayoutInput).text

    object Archive:
      val location = site.location("/archive.html")              
      case class Input( renderLocation : SiteLocation, entryUntemplatesResolved : immutable.SortedSet[EntryResolved] )

      val task = zio.ZIO.attempt {
         val contentsHtml = mainblog.layout_archive_html( Input( location, entriesResolved ) ).text
         layout_main_html( MainLayoutInput( location, contentsHtml, Nil ) ).text
      }
      val endpointBinding = ZTEndpointBinding.publicReadOnlyHtml( location, task, None, immutable.Set("archive") )
    end Archive

    override def endpointBindings : immutable.Seq[ZTEndpointBinding] = super.endpointBindings :+ Archive.endpointBinding
      
  end MainBlog

  // avoid conflicts, but early items in the lists take precedence over later items
  override val endpointBindingSources : immutable.Seq[ZTEndpointBinding.Source] = immutable.Seq( MainBlog )

object DraftsSiteGenerator extends ZTMain(DraftsSite, "drafts-site")


