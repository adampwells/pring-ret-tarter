package com.starter.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import com.starter.config.security.CustomAuthenticationToken
import com.starter.dto.RestBody
import com.starter.repositories.PersonRepository
import com.starter.service.SecurityService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.core.context.{SecurityContext, SecurityContextHolder}
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler

/**
  * Created by adam.wells on 23/06/2016.
  */
@Service
class BaseController {

  private val log: Logger = LoggerFactory.getLogger(classOf[BaseController])

  @Autowired
  val securityService: SecurityService = null

  @Autowired
  val personRepository: PersonRepository = null

  def withPermission(permission: String): PermissionProcessor[ResponseEntity[RestBody]] = {
    PermissionProcessor(checkPermission(permission))
  }

  def withAnyPermission(): PermissionProcessor[ResponseEntity[RestBody]] = {
    PermissionProcessor(checkAuthenticated())
  }

  def withPerson(personUid: String): PermissionProcessor[ResponseEntity[RestBody]] = {
    PermissionProcessor(checkPerson(personUid))
  }

  def checkPermission(permission: String): Option[ResponseEntity[RestBody]] = {

    if (securityService.userHasPermission(permission)){
      None
    } else {
      val body = new RestBody(s"Missing required permission [$permission]", null)
      Some(new ResponseEntity[RestBody](body, HttpStatus.FORBIDDEN))
    }

  }

  def checkPerson(personUid: String): Option[ResponseEntity[RestBody]] = {

    if (personRepository.findByUid(personUid) != null){
      None
    } else {
      val body = new RestBody(s"Not a valid volunteer [$personUid]", null)
      Some(new ResponseEntity[RestBody](body, HttpStatus.FORBIDDEN))
    }

  }

  def checkAuthenticated(): Option[ResponseEntity[RestBody]] = {

    if (securityService.userHasAnyPermission()){
      None
    } else {
      val body = new RestBody(s"Not logged in", null)
      Some(new ResponseEntity[RestBody](body, HttpStatus.FORBIDDEN))
    }

  }

  def findUsernameFromHeader(request: HttpServletRequest): String = {
    val token: String = request.getHeader("X-Auth-Token")
    val context: SecurityContext = SecurityContextHolder.getContext
    val authenticationToken: CustomAuthenticationToken = context.getAuthentication.asInstanceOf[CustomAuthenticationToken]
    authenticationToken.getSessionDto.getPerson.getLogin
  }

  @ExceptionHandler(Array(classOf[Exception]))
  def handleBadRequests(e: Exception, response: HttpServletResponse): ResponseEntity[RestBody] = {
    log.error(s"Something bad happened [${e.getMessage}]", e)
    val body = new RestBody(s"Something bad happened [${e.getMessage}]", null)
    new ResponseEntity[RestBody](body, HttpStatus.INTERNAL_SERVER_ERROR)
  }
}
