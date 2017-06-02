package com.starter.repositories;

import com.starter.model.party.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adam.wells on 4/03/2016.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByLogin(String login);
    Person findByEmail(String email);
    Person findByUid(String uid);

}
