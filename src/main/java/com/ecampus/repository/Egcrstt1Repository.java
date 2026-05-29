package com.ecampus.repository;

import java.util.List;

import com.ecampus.model.Egcrstt1;
import com.ecampus.model.Egcrstt1Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Egcrstt1Repository extends JpaRepository<Egcrstt1, Egcrstt1Id> {

    List<Egcrstt1> findByStudIdAndTcridIn(Long studId, List<Long> tcrIds);

    @Query(value = "SELECT COUNT(*) FROM ec2.EGCRSTT1 eg WHERE eg.STUD_ID=:pStdId AND eg.TCRID=:tempTermCourseId AND eg.ROW_ST>0 AND eg.OBTGR_ID = 10", nativeQuery = true)
    Long countPassGradesForAudit(@Param("pStdId") Long pStdId, @Param("tempTermCourseId") Long tempTermCourseId);
    
}