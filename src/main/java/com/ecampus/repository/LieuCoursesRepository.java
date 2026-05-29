package com.ecampus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecampus.model.LieuCourses;

@Repository
public interface LieuCoursesRepository extends JpaRepository<LieuCourses, Integer> {

    @Query(value = "SELECT lcr.lcrtcrid FROM ec2.lieucourses lcr " +
               "JOIN ec2.lieucoursestudents lcs ON lcr.lcrid = lcs.lcslcrid " +
               "JOIN ec2.lieucoursemappings lcm ON lcm.lcmlcrid = lcr.lcrid " +
               "WHERE lcr.lcrtcrid IN (:tempEligibleTermCoursesList) " +
               "AND lcs.lcsstdid = :pStdId " +
               "AND lcm.lcmtcrid IN (:tempEligibleTermCoursesList) " +
               "AND lcr.lcractivationflag = 'T' " +
               "AND lcm.lcmactivationflag = 'T'", nativeQuery = true)
    List<Long> findForStd(@Param("pStdId") Long pStdId, @Param("tempEligibleTermCoursesList") List<Long> tempEligibleTermCoursesList);

}