package code.model

import net.liftweb.mongodb.record.{MongoMetaRecord, MongoId, MongoRecord}
import net.liftweb.record.field.{DateTimeField, TextareaField, StringField}

class Event extends MongoRecord[Event] with MongoId[Event] {
  def meta = Event
  object name extends StringField(this, 255)
  object description extends TextareaField(this, 5000)
  object startDate extends DateTimeField(this)
  object endDate extends DateTimeField(this)
}

object Event extends Event with MongoMetaRecord[Event] {

}