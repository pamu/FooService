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

case object Join
case class Leave(phno: String)
case class Broadcast(message: String)
case class Call(call: JsValue)


class CallEventsActor extends Actor {
  val (enumerator, channel) = Concurrent.broadcast[String]
  
  implicit val missedCallDataReads: Reads[MissedCallData] = (
    (JsPath \ "simNumber").read[String] and
    (JsPath \ "phno").read[String] and
    (JsPath \ "mtype").read[Int])(MissedCallData.apply _)
  
  def receive = {
    
  case Call(call) => {
    println("got a call in actor")
       call.validate[MissedCallData].fold(
              invalid = {
                errors => println("errors in json")
              },
              valid = {
                data => {
                  println(data+" in actor")
                  self ! Broadcast(s"sim number: ${data.simNumber} call from ${data.phno}")
                }
              }
          )
    }
    
    case Join => {
      println("join request")
      val iteratee = Iteratee.foreach[String]{msg => println(msg)}.map(_ => println("closed"))
      channel.push("starting ...")
      sender ! (iteratee, enumerator)
    }
    
    case Broadcast(msg: String) => channel.push(msg)
  }
}