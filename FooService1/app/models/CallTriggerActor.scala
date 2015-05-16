package models

import akka.actor.Actor
import play.api.libs.iteratee.Iteratee
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.Concurrent
import play.api.libs.iteratee.Enumeratee
import play.api.libs.iteratee.Concurrent.Channel

case class Send(to: String)
case class CallFrom(phno: String)

class CallTriggerActor extends Actor {
	val clients = scala.collection.mutable.Map[String, (Enumerator[String],Channel[String])]()
	
	def receive = {
    	case Send(phno) => {
    	  println("send in second actor")
    	  val (enumerator, channel) = Concurrent.broadcast[String]
    	  val iteratee = Iteratee.foreach[String]{msg => {}}.map( _ =>{
    	    clients -= phno
    	  })
    	  
    	  if(!clients.contains(phno)){
    	    println(phno+" added")
    	    clients += ((phno, (enumerator, channel)))
    	    sender ! (iteratee, clients(phno)._1)
    	  }else {
    		sender ! (iteratee, clients(phno)._1)
    	  }
    	}
    	case CallFrom(phno) => {
    	  println("call from (second actor) " + phno)
    	  if(clients.contains(phno)) {
    	    println("phno pushed")
    	    clients(phno)._2.push(phno)
    	  }
    	  self ! Send(phno)
    	}
	}
}