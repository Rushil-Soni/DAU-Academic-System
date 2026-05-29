package com.ecampus.repository;

import com.ecampus.model.RegistrationOpenFor;
import com.ecampus.model.RegistrationOpenForId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationOpenForRepository extends JpaRepository<RegistrationOpenFor, RegistrationOpenForId> {

    @Query(value = "SELECT * FROM ec2.registrationopenfor rof WHERE rof.termid = :termid AND rof.batchid = :batchid AND rof.registrationtype = :registrationtype", nativeQuery = true)
    RegistrationOpenFor getRofByTrmBch(@Param("termid") Long termid, @Param("batchid") Long batchid, @Param("registrationtype") String registrationtype);

}