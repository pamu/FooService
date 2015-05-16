package models

import scala.slick.driver.MySQLDriver.simple._

object DAO {
  /**
   * TableQuery for each Table
   */
    val missedCalls = TableQuery[MissedCalls]
    val users = TableQuery[Users]
    val admins = TableQuery[Admins]
    val appRegs = TableQuery[AppRegs]
    val userEntries = TableQuery[UserEntries]
    val whitelistedItems = TableQuery[WhitelistedItems]
    val signinStatuses = TableQuery[SigninStatuses]
    val signingupUserEntries = TableQuery[SigningupUserEntries]
   
    /**
     * database handle
     */
	lazy val db: Database = Database.forURL(url = "jdbc:mysql://localhost:3306/missed_calls_db", driver = "com.mysql.jdbc.Driver", user = "root", password = "root")
	
	/**
	 * creates the tables
	 */
	def createTables = db.withSession(implicit session => {
	  missedCalls.ddl.create
	  users.ddl.create
	  admins.ddl.create
	  appRegs.ddl.create
	  whitelistedItems.ddl.create
	  userEntries.ddl.create
	  signinStatuses.ddl.create
	  signingupUserEntries.ddl.create
	})
	
	/**
	 * writes admin to the database admin table 
	 */
	def saveAdmin(admin: Admin) = db.withTransaction(implicit session => {
	  admins += admin
	})
	
	/**
	 * writes missed call data to missed calls table
	 */
	def saveMissedCall(missedCall: MissedCall) = db.withSession(implicit session => {
	  missedCalls += missedCall
	})
	
	/**
	 * returns a user option for given email
	 */
	def findOneByEmail(email: String) = db.withTransaction(implicit tx => {
	  val userQuery = for(user <- users.filter(_.email === email)) yield user
	  userQuery.firstOption
	})
	
	/**
	 * returns a admin option for given email
	 */
	def findOneAdminByEmail(email: String) = db.withTransaction(implicit tx => {
	  val adminQuery = for(admin <- admins.filter(_.email === email)) yield admin
	  adminQuery.firstOption
	})
	
	/**
	 * check admin credentials
	 */
	def checkAdmin(email: String, password: String): Boolean = db.withTransaction(implicit tx => {
	  val adminQuery = for(admin <- admins.filter(_.email === email).filter(_.password === password)) yield admin
	  adminQuery.exists.run
	})
	
	/**
	 * check if app is reged
	 */
	def isAppReg(simId: String, phno: String): Boolean = db.withTransaction(implicit tx => {
	  val appRegSimQuery = for(appReg <- appRegs.filter(_.simId === simId)) yield appReg
	  val appRegPhnoQuery = for(appReg <- appRegs.filter(_.phoneNumber === phno)) yield appReg
	  (appRegSimQuery.exists.run || appRegPhnoQuery.exists.run)
	})
	
	/**
	 * reg the app
	 */
	def regApp(appReg: AppReg) = db.withSession(implicit session => {
	  appRegs += appReg
	})
	
	/**
	 * un reg the app 
	 */
	def appUnReg(phno: String) = db.withTransaction(implicit tx => {
	  val appUnRegQuery = for(appReg <- appRegs.filter(_.phoneNumber === phno)) yield appReg
	  appUnRegQuery.delete
	})
	
	/**
	 * check if app or device with particular sim is registered
	 */
	def isSimExists(simId: String): Boolean = db.withTransaction(implicit tx => {
	  val simRegQuery = for(appReg <- appRegs.filter(_.simId === simId.trim())) yield appReg
	  simRegQuery.exists.run
	})
	
	/**
	 * check is its white listed
	 */
	def isWhitelisted(simId: String): Boolean = db.withTransaction(implicit tx => {
	  val whitelistQuery = for(appReg <- appRegs.filter(_.simId === simId).filter(_.whitelisted === 'Y')) yield appReg
	  whitelistQuery.exists.run
	})
	
	/**
	 * white list the sim
	 */
	def whitelist(simId: String) = db.withTransaction(implicit tx => {
      val appRegQ = for(appReg <- appRegs.filter(_.simId === simId)) yield appReg.whitelisted
      appRegQ.update('Y')
      val appRegQ2 = for(appReg <- appRegs.filter(_.simId === simId)) yield appReg
      appRegQ2.firstOption.foreach{
        appReg => {
          val date = new java.util.Date()
          
          val t = new java.sql.Timestamp(date.getTime())
          
          whitelistedItems += WhitelistedItem(appReg.simId, appReg.androidPhoneNumber,t)
        }
      }
	})
	
	/**
	 * remove from white list
	 */
	def blacklist(simId: String) = db.withTransaction(implicit tx => {
	  val appRegQ = for(appReg <- appRegs.filter(_.simId === simId)) yield appReg.whitelisted
      appRegQ.update('N')
      val blacklistQuery = for(whitelistedItem <- whitelistedItems.filter(_.simId === simId)) yield whitelistedItem
      blacklistQuery.delete
	})
	
	/**
	 * check if email exists
	 */
	def checkUserEmailAvailability(email: String) : Boolean = db.withTransaction(implicit tx => {
	  val emailQ = for(user <- users.filter(_.email === email)) yield user
	  emailQ.exists.run
	})
	
	/**
	 * check if phno exists
	 */
	def checkUserPhnoAvailability(phno: String): Boolean = db.withTransaction(implicit tx => {
	  val phnoQ = for(user <- users.filter(_.phno === phno)) yield user
	  phnoQ.exists.run
	})
	
	/**
	 * save user returning id
	 */
	def saveUser(user: User): Long = db.withTransaction(implicit tx => {
	  def userAutoId = users returning users.map(_.id) into {
	    case (_, id) => id
	  } 
	  userAutoId.insert(user)
	})
	
	/**
	 * check if missed call exists with in certain time span
	 */
	def isMissedCallInInterval(userId: Long, phno: String, minutes: Int): Boolean = db.withTransaction(implicit tx => {
	  val date = new java.util.Date
	  val mins = date.getMinutes() - minutes
	  date.setMinutes(mins)
	  val t = new java.sql.Timestamp(date.getTime())
	  println(t.toString)
	  val timeQ = for(userEntry <- userEntries.filter(_.userId === userId).filter(_.timestamp >= t);
			  		  missedCall <- missedCalls
			  					.filter(_.phno === phno)
			  					.filter(_.timestamp >= t)) yield missedCall
	  val exists = timeQ.exists.run
	  exists
	})
	
	/**
	 * check if missed call exists with in certain time span
	 */
	def isMissedCallInIntervalForSigningupUser(su: SigningupUser, phno: String, minutes: Int): Boolean = db.withTransaction(implicit tx => {
	  val date = new java.util.Date
	  val mins = date.getMinutes() - minutes
	  date.setMinutes(mins)
	  val t = new java.sql.Timestamp(date.getTime())
	  println(t.toString)
	  val timeQ = for(signingupUserEntry <- signingupUserEntries.filter(_.email === su.email).filter(_.phno === su.phno).filter(_.timestamp >= t);
			  		  missedCall <- missedCalls
			  					.filter(_.phno === phno)
			  					.filter(_.timestamp >= t)) yield missedCall
	  val exists = timeQ.exists.run
	  exists
	})
	
	/**
	 * get phno from email
	 */
	def getPhno(email: String) = db.withTransaction(implicit tx => {
	  val phnoQ = for(user <- users.filter(_.email === email)) yield user.phno
	  phnoQ.firstOption
	})
	
	/**
	 * get phno from white list randomly 
	 */
	def getPhnoFromWhiteList() = db.withTransaction(implicit tx => {
	  val whiteQ = for(whiteList <- whitelistedItems) yield whiteList.androidPhoneNumber
	  val list = whiteQ.list()
	  val length = list.length
	  val rand = scala.util.Random
	  Option(list(rand.nextInt(length)))
	})
	
	/**
	 * save user entry along with timestamp
	 */
	def insertUserEntry(userId: Long) = db.withSession(implicit session => {
	  val dt = new java.util.Date
	  val time = new java.sql.Timestamp(dt.getTime())
	  userEntries += UserEntry(userId, time)
	})
	
	/**
	 * save signingup timestamp of user 
	 */
	def insertSigningupUserEntry(su: SigningupUser) = db.withTransaction(implicit tx => {
	  val date = new java.util.Date
	  
	  val t = new java.sql.Timestamp(date.getTime)
	  
	  signingupUserEntries += SigningupUserEntry(su.email, su.phno, t)
	})
	
	/**
	 * returns signin status option
	 */
	def isTryingToSignin(email: String) = db.withTransaction(implicit tx => {
	 val signinStatusQ = for((user, signinStatus) <- users.filter(_.email === email).innerJoin(signinStatuses).on(_.id === _.userId)) yield signinStatus.status
     signinStatusQ.firstOption
	})
	
	/**
	 * update signin status flag to 1
	 */
	def signinOn(email: String) = db.withTransaction(implicit tx => {
	 val userQ = for(user <- users.filter(_.email === email)) yield user
	 userQ.firstOption match {
	   case Some(user) => {
		 val signinQ = for(signin <- signinStatuses.filter(_.userId === user.id)) yield signin.status
		 signinQ.update(1)
	   }
	   case None => {}
	 }
	})
	
	/**
	 * 
	 * update sigin status flag to 0
	 */
	def signinOff(email: String) = db.withTransaction(implicit tx => {
	 val userQ = for(user <- users.filter(_.email === email)) yield user
	 userQ.firstOption match {
	   case Some(user) => {
	     val signinQ = for(signin <- signinStatuses.filter(_.userId === user.id)) yield signin.status
	     signinQ.update(0)
	   }
	   case None => {}
	 }
	})
	
	/**
	 * save sigin status entry
	 */
	def saveSigninStatusEntry(userId: Long) = db.withSession(implicit session => {
	  signinStatuses += SigninStatus(userId, 0)
	})
}