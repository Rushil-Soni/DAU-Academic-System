package com.ecampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecampus.model.AddDropPreferences;

@Repository
public interface AddDropPreferencesRepository extends JpaRepository<AddDropPreferences, Long> {

    @Query(value = "select * from ec2.adddroppref adp where adp.sid=:sid", nativeQuery = true)
    AddDropPreferences findBySid(@Param("sid") Long sid);
    
}
