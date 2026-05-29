package com.ecampus.repository;

import com.ecampus.model.CourseTypeConversion;
import com.ecampus.model.CourseTypeConversionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTypeConversionRepository extends JpaRepository<CourseTypeConversion, CourseTypeConversionId> {

	List<CourseTypeConversion> findByOrigCtpid(Long origCtpid);

	List<CourseTypeConversion> findByOrigCtpidIn(List<Long> origCtpids);

	boolean existsByOrigCtpid(Long origCtpid);
}
