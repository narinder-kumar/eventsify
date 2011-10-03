package code.snippet

import code.model.Event

import _root_.net.liftweb.http._
import _root_.scala.xml._
import _root_.net.liftweb.util.Helpers._
import _root_.net.liftweb.http.S._
class Events extends StatefulSnippet {

  def dispatch:DispatchIt = {
    case "create" => create _
  }

  var editingEvent:Event = Event.createRecord

  def create(xhtml:NodeSeq):NodeSeq = {
    (
      "#name" #> editingEvent.name.toForm &
      "#description" #> editingEvent.description.toForm &
      "type=submit" #> SHtml.submit(?("Save"), () => save)
      ).apply(xhtml)
  }

  def save = {
    editingEvent.save
    redirectToHome
  }

  def redirectToHome {
    editingEvent = Event.createRecord
    redirectTo("/events/list")
  }
}