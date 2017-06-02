package com.starter.service

import java.util.UUID

import com.starter.dto.PersonDto
import com.starter.model.party.Person
import com.starter.repositories.PersonRepository
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by adam.wells on 2/04/2016.
  */
@Service
class PersonService {

  private val log: Logger = LoggerFactory.getLogger(classOf[PersonService])

  @Autowired
  val personRepository: PersonRepository = null

  def getOrCreate(personDto: PersonDto): Person = {
    log.info(s"getOrCreate [${personDto.toString}]")
    personDto.getPersonId match {
      case null => {
        personDto.getEmail match {
          case null => create(personDto)
          case _ => {
            log.info(s"about to search for [${personDto.getEmail}]")
            personRepository.findByEmail(personDto.getEmail.toLowerCase()) match {
              case null => create(personDto)
              case person => person
            }
          }
        }
      }
      case _  => personRepository.findOne(personDto.getPersonId)
    }
  }

  private def create(personDto: PersonDto): Person = {
    val person = new Person
    person.setFirstName(personDto.getFirstName)
    person.setEmail(if (personDto.getEmail == null) null else personDto.getEmail.toLowerCase)
    person.setPhone(personDto.getPhone)
    person.setLastName(personDto.getLastName)
    person.setLogin(if (personDto.getLogin == null) UUID.randomUUID.toString else personDto.getLogin)
    person.setPasswordHash(personDto.getPasswordHash)
    personRepository.saveAndFlush(person)
  }

}
