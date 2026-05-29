package com.ecampus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecampus.model.Semesters;
import com.ecampus.model.StudentRegistrations;

@Repository
public interface StudentRegistrationsRepository extends JpaRepository<StudentRegistrations, Long> {

    @Query(value = "SELECT * FROM ec2.STUDENTREGISTRATIONS srg WHERE srg.SRGROWSTATE > 0 AND srg.SRGSTDID=:studentId", nativeQuery = true)
    List<StudentRegistrations> findregisteredsemesters(@Param("studentId") Long studentId);

    @Query(value = "SELECT * FROM ec2.STUDENTREGISTRATIONS srg WHERE srg.SRGROWSTATE > 0 AND srg.SRGSTDID=:studentId and srg.SRGSTRID=:semesterId", nativeQuery = true)
    StudentRegistrations getsrgbystdidandstrid(@Param("studentId") Long studentId, @Param("semesterId") Long semesterId);

    @Query("SELECT MAX(s.srgid) FROM StudentRegistrations s")
    Optional<Long> findMaxSrgid();

    
    // Get latest active registration record per student
    List<StudentRegistrations> findBysrgstdidAndSrgrowstateGreaterThanOrderBySrgidDesc(
            Long studentId, int state);

    // Get most recent registration entry
    Optional<StudentRegistrations> findTopBysrgstdidOrderBySrgidDesc(Long studentId);

    // Get all semesters registered by student in order
    @Query("SELECT sr FROM StudentRegistrations sr JOIN sr.semesters s " +
            "WHERE sr.students.stdid = :studentId " +
            "ORDER BY s.strseqno ASC")
    List<StudentRegistrations> findAllRegistrationsByStudentIdOrderBySemesterSequence(
            @Param("studentId") Long studentId);


    // Used for semester-wise grade card (corrected based on entity)
    @Query("SELECT r FROM StudentRegistrations r " +
            "WHERE r.students.stdid = :studentId " +
            "AND r.semesters.strid = :semesterId " +
            "AND r.srgrowstate > 0")
    StudentRegistrations findByStudentIdAndSemesterId(@Param("studentId") Long studentId,
                                                     @Param("semesterId") Long semesterId);


    // Semester dropdown loading
    @Query(value = "SELECT * FROM ec2.semesters WHERE strtrmid = :termId", nativeQuery = true)
    List<Semesters> findSemestersByTerm(@Param("termId") Long termId);
    @Query("""
       SELECT r
       FROM StudentRegistrations r
       JOIN r.students s
       JOIN r.semesters sem
       WHERE sem.strid = :semesterId
         AND r.srgrowstate > 0
       ORDER BY s.stdinstid ASC
       """)
    List<StudentRegistrations> findBySemesterOrderByStudentInstId(@Param("semesterId") Long semesterId);

    @Query(value = "SELECT * FROM ec2.STUDENTREGISTRATIONS srg WHERE srg.SRGSTRID=:semesterId", nativeQuery = true)
    List<StudentRegistrations> findByStrid(@Param("semesterId") Long semesterId);
}
