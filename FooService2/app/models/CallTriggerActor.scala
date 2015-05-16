package models

import akka.actor.Actor
import play.api.libs.iteratee.Iteratee
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.Concurrent
import play.api.libs.iteratee.Enumeratee
import play.api.libs.iteratee.Concurrent.Channel
import play.api.Logger

/**
 * actor messages
 */
case class Send(to: String)
case class CallFrom(phno: String)

class CallTriggerActor extends Actor {
 /**
  *  clients is a map of phnoes and (enumerator, channel) tuple
  */ 
 val clients = scala.collection.mutable.Map[String, (Enumerator[String],Channel[String])]()
	
	def receive = {
   
    	case Send(phno) => {
    	  
    	  Logger.info("send message in call trigger actor")
    	  /**
    	   * create a enumera
    	   */
    	 
    	  
    	  val iteratee = Iteratee.ignore[String].map( _ =>{
    	    /**
    	     * close channels
    	     */
    	    clients(phno)._2.eofAndEnd;
    	    clients(phno)._1.andThen(Enumerator.eof[String])
    	    Logger.info("channel closed in call trigger actor")
    	    
    	    /**
    	     * remove the phno from clients map
    	     */
    	    clients -= phno
    	  })
    	  
    	  if(!clients.contains(phno)){
    		  
    	    /**
    	     * create enumerator and channel for new client
    	     */
    	     val (enumerator, channel) = Concurrent.broadcast[String]
    	     
    	    clients += ((phno, (enumerator, channel)))
    	    
    	    Logger.info(phno+" added to clients list")
    	    
    	    /**
    	     * return the iteratee and enumerator tuple
    	     */
    	    sender ! (iteratee, clients(phno)._1)
    	  }else {
    	    
    	    /**
    	     * return corresponding iteratee, enumerator tuple
    	     */
    		sender ! (iteratee, clients(phno)._1)
    	  }
    	}
    	
    	case CallFrom(phno) => {
    	  
    	  Logger.info("in call trigger actor call from " + phno)
    	  
    	  if(clients.contains(phno)) {
    	    /**
    	     * send the message to the subscribed client
    	     */
    	    clients(phno)._2.push(phno)
    	  }else {
    	    /**
    	     * send message
    	     */
    	    self ! Send(phno) 
    	  }
    	}
	}
}