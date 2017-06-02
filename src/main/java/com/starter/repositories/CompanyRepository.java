package com.starter.repositories;

import com.starter.model.party.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adam.wells on 4/03/2016.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
