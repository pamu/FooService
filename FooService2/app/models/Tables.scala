package models

import scala.slick.driver.MySQLDriver.simple._
import java.sql.Timestamp
import scala.slick.model.ForeignKeyAction
import java.util.Date

/**
 * setting up database scheme (everything else is self explanatory)
 */

case class MissedCall(simNumber: String, phno: String, timestamp: Timestamp, id: Option[Long] = None)

class MissedCalls(tag: Tag) extends Table[MissedCall](tag, "MISSED_CALLS") {
  def simNumber = column[String]("SIM_NUMBER", O.NotNull)
  def phno = column[String]("PHNO", O.NotNull)
  def timestamp = column[Timestamp]("TIMESTAMP", O.NotNull)
  def id = column[Long]("ID", O.NotNull, O.AutoInc, O.PrimaryKey)
  def * = (simNumber, phno, timestamp, id.?) <> (MissedCall.tupled, MissedCall.unapply)
}

case class AppReg(simId: String, androidPhoneNumber: String, timestamp: Timestamp, whitelisted: Char = 'N', id: Option[Long] = None)

class AppRegs(tag: Tag) extends Table[AppReg](tag, "APP_REGISTRATIONS") {
  def simId = column[String]("SIM_NUMBER", O.NotNull)
  def phoneNumber = column[String]("PHONE_NUMBER", O.NotNull)
  def timestamp = column[Timestamp]("TIMESTAMP", O.NotNull)
  def whitelisted = column[Char]("WHITE_LISTED", O.NotNull, O.Default('N'))
  def id = column[Long]("ID", O.PrimaryKey, O.NotNull, O.AutoInc)
  def * = (simId, phoneNumber, timestamp, whitelisted, id.?) <> (AppReg.tupled, AppReg.unapply)
}


case class WhitelistedItem(simId:String, androidPhoneNumber: String, timestamp: Timestamp, id: Option[Long] = None)

class WhitelistedItems(tag: Tag) extends Table[WhitelistedItem](tag, "WHITE_LISTED_ITEMS") {
  def simId = column[String]("SIM_ID", O.NotNull)
  def androidPhoneNumber = column[String]("ANDROID_PHONE_NUMBER", O.NotNull)
  def timestamp = column[Timestamp]("TIMESTAMP", O.NotNull)
  def id = column[Long]("ID", O.PrimaryKey, O.NotNull, O.AutoInc)
  def * = (simId, androidPhoneNumber, timestamp, id.?) <> (WhitelistedItem.tupled, WhitelistedItem.unapply)
}


case class User(email: String, phno: String, id: Option[Long] = None)

class Users(tag: Tag) extends Table[User](tag, "USERS") {
  def email = column[String]("EMAIL", O.NotNull)
  def phno = column[String]("PHNO", O.NotNull)
  def id = column[Long]("ID", O.NotNull, O.PrimaryKey, O.AutoInc)
  def * = (email, phno, id.?) <> (User.tupled, User.unapply)
}

case class Admin(email: String, password: String, id: Option[Long] = None)

class Admins(tag: Tag) extends Table[Admin](tag, "ADMINS") {
  def email = column[String]("EMAIL", O.NotNull)
  def password = column[String]("PASSWORD", O.NotNull)
  def id = column[Long]("ID", O.NotNull, O.PrimaryKey, O.AutoInc)
  def * = (email, password, id.?) <> (Admin.tupled, Admin.unapply)
}

case class UserEntry(userId: Long, timestamp: Timestamp, id: Option[Long] = None)

class UserEntries(tag: Tag) extends Table[UserEntry](tag, "USER_ENTRIES") {
  def userId = column[Long]("USER_ID", O.NotNull)
  def timestamp = column[Timestamp]("TIMESTAMP", O.NotNull)
  def id = column[Long]("ID", O.NotNull, O.PrimaryKey, O.AutoInc)
  def * = (userId, timestamp, id.?) <> (UserEntry.tupled, UserEntry.unapply)
  def userIdFK = foreignKey("USER_ENTRY_USER_ID_FK", userId, TableQuery[Users])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
}

case class SigninStatus(userId: Long, status: Int = 0, id: Option[Long] = None)

class SigninStatuses(tag: Tag) extends Table[SigninStatus](tag, "SIGNIN_STATUSES"){
  def userId = column[Long]("USER_ID", O.NotNull)
  def status = column[Int]("STATUS", O.NotNull)
  def id = column[Long]("ID", O.NotNull, O.PrimaryKey, O.AutoInc)
  def * = (userId, status, id.?) <> (SigninStatus.tupled, SigninStatus.unapply)
  def userIdFk = foreignKey("SIGNIN_STATUS_USER_ID_FK", userId, TableQuery[Users])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
}

case class SigningupUser(email: String, phno: String)

case class SigningupUserEntry(email: String, phno: String, timestamp: Timestamp, id: Option[Long] = None)

class SigningupUserEntries(tag: Tag) extends Table[SigningupUserEntry](tag, "SIGNINGUP_USER_ENTRIES"){
  def email = column[String]("EMAIL", O.NotNull)
  def phno = column[String]("PHNO", O.NotNull)
  def timestamp = column[Timestamp]("TIMESTAMP", O.NotNull)
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc, O.NotNull)
  def * = (email, phno, timestamp, id.?) <> (SigningupUserEntry.tupled, SigningupUserEntry.unapply)
}
