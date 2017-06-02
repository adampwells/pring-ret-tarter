package com.starter.config.security;

import com.starter.model.party.Person;
import com.starter.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by adam.wells on 18/06/2016.
 */
@Configuration
public class SecurityConfig{

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public void configure(PersonRepository personRepository){

        Person administrator = personRepository.findByLogin("administrator");
        if (administrator == null){
            administrator = new Person();
            administrator.setFirstName("administrator");
            administrator.setLastName("administrator");
            administrator.setLogin("administrator");
            administrator.setPasswordHash(BCrypt.hashpw("administrator", BCrypt.gensalt()));
            administrator.getPermissions().add("ADMIN");
            administrator.getPermissions().add("USER");
            log.info("created user administrator");
        } else {
            administrator.setPasswordHash(BCrypt.hashpw("administrator", BCrypt.gensalt()));
            administrator.getPermissions().add("ADMIN");
            administrator.getPermissions().add("USER");
            log.info("user administrator already exists");
        }
        personRepository.saveAndFlush(administrator);

    }

}
