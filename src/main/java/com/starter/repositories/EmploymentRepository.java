package com.starter.repositories;

import com.starter.model.party.relationship.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adam.wells on 4/03/2016.
 */
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
}
