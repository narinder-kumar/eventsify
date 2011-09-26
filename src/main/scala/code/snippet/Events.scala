package code.snippet

import net.liftweb.http.StatefulSnippet
import xml.NodeSeq

class Events extends StatefulSnippet {

  var dispatch: DispatchIt = {
    case "create" => create _
  }

  def create(xhtml: NodeSeq): NodeSeq = {
    return xhtml
  }

}