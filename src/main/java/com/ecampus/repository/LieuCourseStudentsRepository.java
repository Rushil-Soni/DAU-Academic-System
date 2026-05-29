package com.ecampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecampus.model.LieuCourseStudents;

@Repository
public interface LieuCourseStudentsRepository extends JpaRepository<LieuCourseStudents, Integer> {

    

}