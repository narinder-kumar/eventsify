package bootstrap.liftweb

import net.liftweb._
import util._
import common._
import http._
import sitemap._
import code.model._
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import code.db.EventsifyDB

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    EventsifyDB.setup

    siteMap
    
    email

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts
// default resouce bundle
    LiftRules.resourceNames = "i18n/bundle" :: LiftRules.resourceNames 
    
  }

  def siteMap = {
    // Build SiteMap
    def sitemap = SiteMap(
      Menu.i("Home") / "index" >> User.AddUserMenusAfter,
      Menu.i("Events") / "events"  submenus (
        Menu.i("List") / "events" / "list",
        Menu.i("Create") / "events" / "create")
    )

    def sitemapMutators = User.sitemapMutator

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemapMutators(sitemap))
  }
  
  def email = {
	  
   Mailer.devModeSend.default.set((m: MimeMessage) => logger.info( dump(m.getContent())))
   
   def dump(m: Any) = m match {
      case mm: MimeMultipart => mm.getBodyPart(0).getContent
      case otherwise => otherwise.toString
    }

    //System.setProperty("mail.debug", "true")
    
    import javax.mail.Authenticator
    def optionalAuth: Box[Authenticator] = for { 
           user <- Props.get("mail.user")
           pass <- Props.get("mail.password") 
        } yield new Authenticator {
          override def getPasswordAuthentication = new javax.mail.PasswordAuthentication(user,pass) 
        }
   
    logger.info("Mail authentication with: "+optionalAuth)
    optionalAuth foreach { a => System.setProperty("mail.smtp.auth", "true") }
    Mailer.authenticator = optionalAuth
	  
  }

}
