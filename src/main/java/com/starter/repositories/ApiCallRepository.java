package com.starter.repositories;

import com.starter.model.log.ApiCall;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adam.wells on 4/03/2016.
 */
public interface ApiCallRepository extends JpaRepository<ApiCall, Long> {

}
