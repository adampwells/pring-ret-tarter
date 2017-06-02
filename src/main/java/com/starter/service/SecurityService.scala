package com.starter.service

import com.starter.config.security.CustomAuthenticationToken
import com.starter.dto.SessionDto
import com.starter.model.security.Session
import com.starter.repositories.{PersonRepository, SessionRepository}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.{SecurityContext, SecurityContextHolder}
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import scala.collection.JavaConversions._

/**
  * Created by adam.wells on 2/04/2016.
  */
@Service
class SecurityService {

  private val log: Logger = LoggerFactory.getLogger(classOf[SecurityService])

  @Autowired
  val personRepository: PersonRepository = null

  @Autowired
  val sessionRepository: SessionRepository = null

  def login(username: String, password: String, ipAddress: String, userAgent: String): Option[SessionDto] = {

    personRepository.findByLogin(username) match {
      case null => None
      case person => {
        if (BCrypt.checkpw(password, person.getPasswordHash)){
          val session = new Session
          session.setPerson(person)
          session.setExpired(null)
          session.setIpAddress(ipAddress)
          session.setUserAgent(userAgent)
          session.getPermissions.addAll(person.getPermissions)
          val savedSession: Session = sessionRepository.saveAndFlush(session)
          setupSecurityContext(savedSession)
          Some(savedSession.asDto())
        } else {
          None
        }
      }
    }
  }

  def isValidSession(sessionUid: String): Boolean = {
    sessionRepository.findUnexpriredByUid(sessionUid) != null
  }

  def findValidSession(sessionUid: String): Option[Session] = Option(sessionRepository.findUnexpriredByUid(sessionUid))

  def setupSecurityContext(session: Session): Unit = {
    val securityContext: SecurityContext = SecurityContextHolder.getContext
    val grantedAuthorities = session.getPermissions.map(new SimpleGrantedAuthority(_)).toList
    val mobilizrAuthenticationToken: CustomAuthenticationToken = new CustomAuthenticationToken(session.getPerson.getLogin, null, grantedAuthorities, session.asDto)
    securityContext.setAuthentication(mobilizrAuthenticationToken)
  }

  def userHasPermission(permission: String): Boolean = {
    val securityContext: SecurityContext = SecurityContextHolder.getContext
    try {
      val authentication = securityContext.getAuthentication.asInstanceOf[CustomAuthenticationToken]
      authentication.getAuthorities.filter(_.getAuthority.equals(permission)).size == 1
    } catch {
      case e: ClassCastException => {
        log.info("can't cast Authentication")
        false
      }
      case ex: Exception => {
        log.error("some kind of security issue", ex)
        false
      }
    }
  }

  def userHasAnyPermission(): Boolean = {
    val securityContext: SecurityContext = SecurityContextHolder.getContext

    try {
      val authentication = securityContext.getAuthentication.asInstanceOf[CustomAuthenticationToken]
      authentication.getAuthorities.size() > 0
    } catch {
      case e: ClassCastException => {
        log.info("can't cast Authentication")
        false
      }
      case ex: Exception => {
        log.error("some kind of security issue", ex)
        false
      }
    }

  }

}
