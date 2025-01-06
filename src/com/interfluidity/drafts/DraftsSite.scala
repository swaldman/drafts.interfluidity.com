package com.interfluidity.drafts

import scala.collection.*

import unstatic.*
import unstatic.ztapir.*
import unstatic.ztapir.simple.*

import unstatic.*, UrlPath.*

import java.nio.file.Path as JPath

import java.time.{Instant,ZoneId}

import untemplate.Untemplate.AnyUntemplate

import SimpleBlog.SyntheticUpdateAnnouncementSpec

object DraftsSite extends ZTSite.SingleStaticRootComposite( JPath.of("static") ):
  override val serverUrl : Abs    = Abs("https://drafts.interfluidity.com/")
  override val basePath  : Rooted = Rooted.root

  case class MainLayoutInput( renderLocation : SiteLocation, mainContentHtml : String, sourceUntemplates : immutable.Seq[AnyUntemplate] = immutable.Seq.empty, singleItemRssSpec : Option[SingleItemRssSpec] = None )

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

    override val revisionBinder : Option[RevisionBinder] = Some( RevisionBinder.GitByCommit(DraftsSite, JPath.of("."), siteRooted => Rel("public/").embedRoot(siteRooted)) )

    override val diffBinder : Option[DiffBinder] = Some( DiffBinder.JavaDiffUtils(DraftsSite) )

    override val syntheticUpdateAnnouncementSpec : Option[SyntheticUpdateAnnouncementSpec] = Some( SyntheticUpdateAnnouncementSpec( "Update-o-Bot", Instant.parse("2024-10-22T12:00:00.00Z") ) )

    override val generateSingleItemRss : Boolean = true

    override val allItemFeedSiteRooted : Option[Rooted] = Some(Rooted("/all-item-feed/index.rss"))

    override def layoutEntry(input: Layout.Input.Entry) : String = mainblog.layout_entry_html(input).text

    // overriding a def, but it's just a constant, so we override with val
    override val entrySeparator : String = partials.entry_separator_html().text

    // here the blog shares the sites main overall layout
    override def layoutPage(input: Layout.Input.Page): String =
      val singleItemRssSpec =
        if input.sourceEntries.length == 1 then
          val info = input.sourceEntries.head.entryInfo
          info.singleItemRssSiteRooted.map( siteRooted => SingleItemRssSpec( siteRooted, info.mbTitle ) )
        else
          None
      val mainLayoutInput = MainLayoutInput( input.renderLocation, input.mainContentHtml, input.sourceEntries.map( _.entryUntemplate ), singleItemRssSpec )
      layout_main_html(mainLayoutInput).text

    object Archive:
      val location = site.location("/archive.html")
      case class Input( renderLocation : SiteLocation, entryUntemplatesResolved : immutable.SortedSet[EntryResolved] )

      val task = zio.ZIO.attempt {
         val contentsHtml = mainblog.layout_archive_html( Input( location, entriesResolved ) ).text
         layout_main_html( MainLayoutInput( location, contentsHtml, Nil ) ).text
      }
      val endpointBinding = publicReadOnlyHtml( location, task, None, immutable.Set("archive"), resolveHashSpecials = true )
    end Archive

    object Subscribe:
      val location = site.location("/subscribe.html")

      val task = zio.ZIO.attempt {
         val contentsHtml = mainblog.subscribe_page_html().text
         layout_main_html( MainLayoutInput( location, contentsHtml, Nil ) ).text
      }
      val endpointBinding = publicReadOnlyHtml( location, task, None, immutable.Set("subscribe"), resolveHashSpecials = true )
    end Subscribe

    override def endpointBindings : immutable.Seq[ZTEndpointBinding] =
      super.endpointBindings :+ Archive.endpointBinding :+ Subscribe.endpointBinding

  end MainBlog

  // avoid conflicts, but early items in the lists take precedence over later items
  override val endpointBindingSources : immutable.Seq[ZTEndpointBinding.Source] = immutable.Seq( MainBlog )

object DraftsSiteGenerator extends ZTMain(DraftsSite, "drafts-site")


