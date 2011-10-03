package code.db

import net.liftweb.mongodb.{DefaultMongoIdentifier, MongoDB}
import com.mongodb.Mongo

object EventsifyDB {

  def setup {
    MongoDB.defineDb(DefaultMongoIdentifier, new Mongo, "eventsify")
  }
}