package com.starter.repositories;

import com.starter.model.security.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by adam.wells on 4/03/2016.
 */
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("select s from Session s where s.uid = :sessionUid and s.expired is null")
    Session findUnexpriredByUid(@Param("sessionUid") String sessionUid);

    Session findByUid(String sessionUid);

}
