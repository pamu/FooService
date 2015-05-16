package models

import akka.actor.Actor
import play.api.libs.iteratee.Concurrent
import play.api.libs.json.JsValue
import play.api.libs.iteratee.Iteratee
import play.api.libs.json.Reads
import play.api.libs.json.JsPath
import controllers.MissedCallData
import play.api.libs.functional.syntax._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import java.sql.Timestamp
import play.api.libs.json.Json
import play.api.libs.iteratee.Enumerator
import play.api.Logger

/**
 * Actor messages
 */
case object Join
case class Leave(phno: String)
case class Broadcast(message: String)
case class Call(call: JsValue)


class CallEventsActor extends Actor {
 var channel: play.api.libs.iteratee.Concurrent.Channel[String] = null
  
  
  /**
   * missed call data reads (know about scala implicits)
   */
  
  implicit val missedCallDataReads: Reads[MissedCallData] = (
    (JsPath \ "simNumber").read[String] and
    (JsPath \ "phno").read[String])(MissedCallData.apply _)
  
  /**
   * receive block
   */
  def receive = {
    
  case Call(call) => {
    /**
     * console message to know whats happening
     */
    Logger.info("call message in call events actor")
    
       call.validate[MissedCallData].fold(
              invalid = {
                errors => Logger.info("errors in json")
              },
              valid = {
                data => {
                  /**
                   * console message
                   */
                  Logger.info(data+" in call events actor")
                  
                  /**
                   * send the message to broadcast to admin console
                   */
                  self ! Broadcast(s"sim serial #: ${data.simNumber}. call from: ${data.phno}")
                }
              }
          )
    }
    
    case Join => {
      /**
       * console message
       */
      Logger.info("join request in call events actor")
      
      val (enumerator, tchannel) = Concurrent.broadcast[String]
      channel = tchannel
      
      val iteratee = Iteratee.ignore[String].map( _ => {
        channel.eofAndEnd
        channel = null
        Logger.info("channel closed")
      })
      
      /**
       * returning the iteratee and enumerator
       */
      sender ! (iteratee, enumerator)
    }
    
    case Broadcast(msg: String) => if(channel != null) channel.push(msg)
  }
}