package models

import scala.slick.driver.MySQLDriver.simple._

object DAO {
    val missedCalls = TableQuery[MissedCalls]
    val users = TableQuery[Users]
    val admins = TableQuery[Admins]
    val appRegs = TableQuery[AppRegs]
    val userEntries = TableQuery[UserEntries]
    val whitelistedItems = TableQuery[WhitelistedItems]
    val signinStatuses = TableQuery[SigninStatuses]
    //val signingupUsers = TableQuery[SigningupUsers]
    val signingupUserEntries = TableQuery[SigningupUserEntries]
   
	def db: Database = Database.forURL(url = "jdbc:mysql://localhost:3306/missed_calls_db", driver = "com.mysql.jdbc.Driver", user = "root", password = "root")
	def createTables = db.withSession(implicit session => {
	  missedCalls.ddl.create
	  users.ddl.create
	  admins.ddl.create
	  appRegs.ddl.create
	  whitelistedItems.ddl.create
	  userEntries.ddl.create
	  signinStatuses.ddl.create
	  //signingupUsers.ddl.create
	  signingupUserEntries.ddl.create
	})
	def saveAdmin(admin: Admin) = db.withTransaction(implicit session => {
	  admins += admin
	})
	def saveMissedCall(missedCall: MissedCall) = db.withSession(implicit session => {
	  missedCalls += missedCall
	})
	
	def findOneByEmail(email: String) = db.withTransaction(implicit tx => {
	  val userQuery = for(user <- users.filter(_.email === email)) yield user
	  userQuery.firstOption
	})
	
	def findOneAdminByEmail(email: String) = db.withTransaction(implicit tx => {
	  val adminQuery = for(admin <- admins.filter(_.email === email)) yield admin
	  adminQuery.firstOption
	})
	
	def checkAdmin(email: String, password: String): Boolean = db.withTransaction(implicit tx => {
	  val adminQuery = for(admin <- admins.filter(_.email === email).filter(_.password === password)) yield admin
	  adminQuery.exists.run
	})
	
	def isAppReg(simId: String, phno: String): Boolean = db.withTransaction(implicit tx => {
	  val appRegSimQuery = for(appReg <- appRegs.filter(_.simId === simId)) yield appReg
	  val appRegPhnoQuery = for(appReg <- appRegs.filter(_.phoneNumber === phno)) yield appReg
	  (appRegSimQuery.exists.run || appRegPhnoQuery.exists.run)
	})
	
	def regApp(appReg: AppReg) = db.withSession(implicit session => {
	  appRegs += appReg
	})
	
	def appUnReg(phno: String) = db.withTransaction(implicit tx => {
	  val appUnRegQuery = for(appReg <- appRegs.filter(_.phoneNumber === phno)) yield appReg
	  appUnRegQuery.delete
	})
	
	def isSimExists(simId: String): Boolean = db.withTransaction(implicit tx => {
	  val simRegQuery = for(appReg <- appRegs.filter(_.simId === simId.trim())) yield appReg
	  simRegQuery.exists.run
	})
	
	def isWhitelisted(simId: String): Boolean = db.withTransaction(implicit tx => {
	  val whitelistQuery = for(appReg <- appRegs.filter(_.simId === simId).filter(_.whitelisted === 'Y')) yield appReg
	  whitelistQuery.exists.run
	})
	
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
	def blacklist(simId: String) = db.withTransaction(implicit tx => {
	  val appRegQ = for(appReg <- appRegs.filter(_.simId === simId)) yield appReg.whitelisted
      appRegQ.update('N')
      val blacklistQuery = for(whitelistedItem <- whitelistedItems.filter(_.simId === simId)) yield whitelistedItem
      blacklistQuery.delete
	})
	def checkUserEmailAvailability(email: String) : Boolean = db.withTransaction(implicit tx => {
	  val emailQ = for(user <- users.filter(_.email === email)) yield user
	  emailQ.exists.run
	})
	def checkUserPhnoAvailability(phno: String): Boolean = db.withTransaction(implicit tx => {
	  val phnoQ = for(user <- users.filter(_.phno === phno)) yield user
	  phnoQ.exists.run
	})
	def saveUser(user: User) = db.withSession(implicit session => {
	  users += user
	})
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
	def getPhno(email: String) = db.withTransaction(implicit tx => {
	  val phnoQ = for(user <- users.filter(_.email === email)) yield user.phno
	  phnoQ.firstOption
	})
	def getPhnoFromWhiteList() = db.withTransaction(implicit tx => {
	  val whiteQ = for(whiteList <- whitelistedItems) yield whiteList.androidPhoneNumber
	  whiteQ.firstOption
	})
	def insertUserEntry(userId: Long) = db.withSession(implicit session => {
	  val dt = new java.util.Date
	  val time = new java.sql.Timestamp(dt.getTime())
	  userEntries += UserEntry(userId, time)
	})
	
	/*
	def saveSigningupUser(signingupUser: SigningupUser) = db.withTransaction(implicit tx => {
	  signingupUsers += signingupUser
	})
	def removeSigningupUser(email: String) = db.withTransaction(implicit tx => {
	  val userQ = for(user <- signingupUsers.filter(_.email === email)) yield user
	  userQ.delete
	})
	def findSigningupUserWithEmail(email: String) = db.withTransaction(implicit tx => {
	  val userQ = for(user <- signingupUsers.filter(_.email === email)) yield user
	  userQ.firstOption
	})
	def findSUEmailAvialability(email: String) = db.withTransaction(implicit tx => {
	  val userQ = for(user <- signingupUsers.filter(_.email === email)) yield user.email
	  userQ.exists.run
	})
	def findSUPhnoAvialability(phno: String) = db.withTransaction(implicit tx => {
	  val userQ = for(user <- signingupUsers.filter(_.phno === phno)) yield user.email
	  userQ.exists.run
	})
	*/
	def insertSigningupUserEntry(su: SigningupUser) = db.withTransaction(implicit tx => {
	  val date = new java.util.Date
	  
	  val t = new java.sql.Timestamp(date.getTime)
	  
	  signingupUserEntries += SigningupUserEntry(su.email, su.phno, t)
	})
	/*
	def getPhnoSigningupUser(email: String) = db.withTransaction(implicit tx => {
	  val phnoQ = for(suUser <- signingupUsers.filter(_.email === email)) yield suUser.phno
	  phnoQ.firstOption
	})
	* */
}