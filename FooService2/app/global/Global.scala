package global

import play.api.GlobalSettings
import play.api._
import models.DAO
import models._

object Global extends GlobalSettings {
	override def onStart(app: Application) = {
	  super.onStart(app)
	  Logger.info("application started")
	  //DAO.createTables
	  //DAO.saveAdmin(Admin("nagarjuna@khoslalabs.com", "pamu"))
	}
	override def onStop(app: Application) = {
	  super.onStop(app)
	  Logger.info("application stopped")
	}
}