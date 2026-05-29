package com.ecampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecampus.model.LieuCourseMappings;

@Repository
public interface LieuCourseMappingsRepository extends JpaRepository<LieuCourseMappings, Integer> {



}