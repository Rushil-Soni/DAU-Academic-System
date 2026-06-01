package com.ecampus.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecampus.dto.CourseRegistrationDTO;
import com.ecampus.model.Semesters;
import com.ecampus.model.StudentRegistrationCourses;
import com.ecampus.model.StudentRegistrations;
import com.ecampus.model.Students;
import com.ecampus.repository.SemesterCoursesRepository;
import com.ecampus.repository.SemestersRepository;
import com.ecampus.repository.StudentRegistrationCoursesRepository;
import com.ecampus.repository.StudentRegistrationsRepository;
import com.ecampus.repository.StudentsRepository;

@Service
public class StudentRegistrationService {
    @Autowired
    private StudentsRepository studentRepo;

    @Autowired
    private SemestersRepository semesterRepo;

    @Autowired
    private StudentRegistrationsRepository registrationRepo;

    @Autowired
    private SemesterCoursesRepository semesterCoursesRepo;

    @Autowired
    private StudentRegistrationCoursesRepository registrationCoursesRepo;

    public Students getStudentById(Long id) {
        return studentRepo.findStudent(id);
    }

    public List<Semesters> getSemesterById(Long id) {
        return semesterRepo.findActiveSemestersByBranchId(id);
    }

    public Long getMaxSemesterId(Long batchId) {
        return semesterRepo.findMaxSemesterId(batchId);
    }

    public Long getLatestStudentIdByInstituteId(String instituteId) {
        if (instituteId == null || instituteId.isBlank()) {
            return null;
        }
        return studentRepo.findStdid(instituteId);
    }

    public List<StudentRegistrations> getRegistrationsByStudentId(Long studentId) {
        return registrationRepo.findregisteredsemesters(studentId);
    }

    public Semesters getLatestSemesterForStudent(Long studentId) {
        Students student = studentRepo.findStudent(studentId);
        if (student == null || student.getStdbchid() == null) {
            return null;
        }
        Long maxSemesterId = semesterRepo.findMaxSemesterId(student.getStdbchid());
        return semesterRepo.findById(maxSemesterId).orElse(null);
    }

    /**
     * Get CORE courses for the semester. C-Type is sourced from coursetypes.ctpcode.
     */
    public List<CourseRegistrationDTO> getCoreCoursesBySemester(Long semesterId) {
        List<Object[]> coreCourses = semesterCoursesRepo.getCoreCoursesBySemesterRaw(semesterId);

        return mapCourseRows(coreCourses);
    }

    public List<CourseRegistrationDTO> getProjectInternshipCoursesBySemester(Long semesterId) {
        List<Object[]> rows = semesterCoursesRepo.getProjectInternshipCoursesBySemesterRaw(semesterId);
        return mapCourseRows(rows);
    }

    public List<CourseRegistrationDTO> getBacklogCoursesForStudentBySemester(Long studentId, Long semesterId) {
        List<Object[]> rows = semesterCoursesRepo.getBacklogCoursesForStudentBySemesterRaw(studentId, semesterId);
        List<CourseRegistrationDTO> backlog = mapCourseRows(rows);
        backlog.forEach(dto -> {
            dto.setRegType("BACKLOG");
            if (isBlank(dto.getRemarks())) {
                dto.setRemarks("BACKLOG");
            }
        });
        return backlog;
    }

    public List<CourseRegistrationDTO> getGradeImprovementCoursesForStudentBySemester(Long studentId, Long semesterId) {
        List<Object[]> rows = semesterCoursesRepo.getGradeImprovementCoursesForStudentBySemesterRaw(studentId, semesterId);
        List<CourseRegistrationDTO> gradeImprovement = mapCourseRows(rows);
        gradeImprovement.forEach(dto -> {
            dto.setRegType("GRADE-IMPROVEMENT");
            if (isBlank(dto.getRemarks())) {
                dto.setRemarks("GRADE IMPROVEMENT");
            }
        });
        return gradeImprovement;
    }

    public List<CourseRegistrationDTO> getAuditCoursesForStudentBySemester(
            Long studentId,
            Long semesterId,
            List<CourseRegistrationDTO> coreCourses,
            List<CourseRegistrationDTO> backlogCourses,
            List<CourseRegistrationDTO> gradeImprovementCourses,
            List<CourseRegistrationDTO> projectCourses,
            List<CourseRegistrationDTO> existingAdditionalCourses) {
        List<Object[]> rows = semesterCoursesRepo.getAuditCoursesForStudentBySemesterRaw(semesterId);
        List<CourseRegistrationDTO> audits = mapCourseRows(rows);

        Set<Long> blockedScrids = new HashSet<>();
        Set<Long> blockedCourseIds = new HashSet<>();
        Set<Long> blockedTcrIds = new HashSet<>();
        if (coreCourses != null) {
            coreCourses.stream().map(CourseRegistrationDTO::getScrid).filter(id -> id != null).forEach(blockedScrids::add);
            coreCourses.stream().map(CourseRegistrationDTO::getScrcrsid).filter(id -> id != null).forEach(blockedCourseIds::add);
            coreCourses.stream().map(CourseRegistrationDTO::getScrtcrid).filter(id -> id != null).forEach(blockedTcrIds::add);
        }
        if (backlogCourses != null) {
            backlogCourses.stream().map(CourseRegistrationDTO::getScrid).filter(id -> id != null).forEach(blockedScrids::add);
            backlogCourses.stream().map(CourseRegistrationDTO::getScrcrsid).filter(id -> id != null).forEach(blockedCourseIds::add);
            backlogCourses.stream().map(CourseRegistrationDTO::getScrtcrid).filter(id -> id != null).forEach(blockedTcrIds::add);
        }
        if (projectCourses != null) {
            projectCourses.stream().map(CourseRegistrationDTO::getScrid).filter(id -> id != null).forEach(blockedScrids::add);
            projectCourses.stream().map(CourseRegistrationDTO::getScrcrsid).filter(id -> id != null).forEach(blockedCourseIds::add);
            projectCourses.stream().map(CourseRegistrationDTO::getScrtcrid).filter(id -> id != null).forEach(blockedTcrIds::add);
        }
        if (gradeImprovementCourses != null) {
            gradeImprovementCourses.stream().map(CourseRegistrationDTO::getScrid).filter(id -> id != null).forEach(blockedScrids::add);
            gradeImprovementCourses.stream().map(CourseRegistrationDTO::getScrcrsid).filter(id -> id != null).forEach(blockedCourseIds::add);
            gradeImprovementCourses.stream().map(CourseRegistrationDTO::getScrtcrid).filter(id -> id != null).forEach(blockedTcrIds::add);
        }
        if (existingAdditionalCourses != null) {
            existingAdditionalCourses.stream().map(CourseRegistrationDTO::getScrid).filter(id -> id != null).forEach(blockedScrids::add);
            existingAdditionalCourses.stream().map(CourseRegistrationDTO::getScrcrsid).filter(id -> id != null).forEach(blockedCourseIds::add);
            existingAdditionalCourses.stream().map(CourseRegistrationDTO::getScrtcrid).filter(id -> id != null).forEach(blockedTcrIds::add);
        }

        return audits.stream()
                .filter(dto -> dto.getScrid() != null && !blockedScrids.contains(dto.getScrid()))
                .filter(dto -> dto.getScrcrsid() == null || !blockedCourseIds.contains(dto.getScrcrsid()))
                .filter(dto -> dto.getScrtcrid() == null || !blockedTcrIds.contains(dto.getScrtcrid()))
                .peek(dto -> dto.setRegType("AUDIT"))
                .collect(Collectors.toList());
    }

    public List<CourseRegistrationDTO> getAdditionalRegisteredCourses(Long srgId, Long semesterId, Long studentId) {
        List<CourseRegistrationDTO> coreRows = getCoreCoursesBySemester(semesterId);
        List<CourseRegistrationDTO> backlogRows = getBacklogCoursesForStudentBySemester(studentId, semesterId);
        List<CourseRegistrationDTO> gradeImprovementRows = getGradeImprovementCoursesForStudentBySemester(studentId, semesterId);
        List<CourseRegistrationDTO> projectRows = getProjectInternshipCoursesBySemester(semesterId);

        List<StudentRegistrationCourses> registered = registrationCoursesRepo.findBySrgId(srgId);
        if (registered.isEmpty()) {
            return List.of();
        }

        Map<Long, StudentRegistrationCourses> byScrid = registered.stream()
                .collect(Collectors.toMap(StudentRegistrationCourses::getSrcscrid, Function.identity(), (a, b) -> a));

        List<CourseRegistrationDTO> auditRows = getAuditCoursesForStudentBySemester(
                studentId,
                semesterId,
                coreRows,
                backlogRows,
                gradeImprovementRows,
                projectRows,
                List.of());

        Map<Long, CourseRegistrationDTO> candidateByScrid = new LinkedHashMap<>();
        for (CourseRegistrationDTO row : backlogRows) {
            candidateByScrid.put(row.getScrid(), row);
        }
        for (CourseRegistrationDTO row : gradeImprovementRows) {
            candidateByScrid.putIfAbsent(row.getScrid(), row);
        }
        for (CourseRegistrationDTO row : projectRows) {
            candidateByScrid.putIfAbsent(row.getScrid(), row);
        }
        for (CourseRegistrationDTO row : auditRows) {
            candidateByScrid.putIfAbsent(row.getScrid(), row);
        }

        List<CourseRegistrationDTO> out = new ArrayList<>();
        for (CourseRegistrationDTO row : candidateByScrid.values()) {
            StudentRegistrationCourses rc = byScrid.get(row.getScrid());
            if (rc != null) {
                row.setSrcid(rc.getSrcid());
                row.setRegType(rc.getSrctype());
                String remarks = rc.getSrcfield1();
                row.setRemarks(isBlank(remarks) ? rc.getSrctype() : remarks);
                row.setIsDrop(false);
                out.add(row);
            }
        }
        return out;
    }

    public List<CourseRegistrationDTO> getAdditionalRegisteredCourses(
            Long srgId,
            Long semesterId,
            Long studentId,
            List<CourseRegistrationDTO> coreRows,
            List<CourseRegistrationDTO> backlogRows,
            List<CourseRegistrationDTO> gradeImprovementRows,
            List<CourseRegistrationDTO> projectRows) {
        List<StudentRegistrationCourses> registered = registrationCoursesRepo.findBySrgId(srgId);
        if (registered.isEmpty()) {
            return List.of();
        }

        Map<Long, StudentRegistrationCourses> byScrid = registered.stream()
                .collect(Collectors.toMap(StudentRegistrationCourses::getSrcscrid, Function.identity(), (a, b) -> a));

        List<CourseRegistrationDTO> existingRows = new ArrayList<>();
        for (StudentRegistrationCourses rc : registered) {
            CourseRegistrationDTO row = new CourseRegistrationDTO();
            row.setScrid(rc.getSrcscrid());
            existingRows.add(row);
        }
        List<CourseRegistrationDTO> auditRows = getAuditCoursesForStudentBySemester(
            studentId,
            semesterId,
            coreRows,
            backlogRows,
            gradeImprovementRows,
            projectRows,
            List.of());

        Map<Long, CourseRegistrationDTO> candidateByScrid = new LinkedHashMap<>();
        for (CourseRegistrationDTO row : backlogRows) {
            candidateByScrid.put(row.getScrid(), row);
        }
        for (CourseRegistrationDTO row : gradeImprovementRows) {
            candidateByScrid.putIfAbsent(row.getScrid(), row);
        }
        for (CourseRegistrationDTO row : projectRows) {
            candidateByScrid.putIfAbsent(row.getScrid(), row);
        }
        for (CourseRegistrationDTO row : auditRows) {
            candidateByScrid.putIfAbsent(row.getScrid(), row);
        }

        List<CourseRegistrationDTO> out = new ArrayList<>();
        for (CourseRegistrationDTO row : candidateByScrid.values()) {
            StudentRegistrationCourses rc = byScrid.get(row.getScrid());
            if (rc != null) {
                row.setSrcid(rc.getSrcid());
                row.setRegType(rc.getSrctype());
                String regType = rc.getSrctype();
                String remarks = rc.getSrcfield1();
                row.setRemarks(isBlank(remarks) || !remarks.equalsIgnoreCase(regType) ? regType : remarks);
                row.setIsDrop(false);
                out.add(row);
            }
        }
        return out;
    }

    @Transactional
    public void saveAdditionalCourses(
            Long studentId,
            Long semesterId,
            List<CourseRegistrationDTO> additionalCourseDTOs,
            Long userId) {
        if (additionalCourseDTOs == null || additionalCourseDTOs.isEmpty()) {
            return;
        }

        StudentRegistrations registration = getExistingRegistration(studentId, semesterId);
        if (registration == null) {
            registration = new StudentRegistrations();
            registration.setSrgid(getNextSrgId());
            registration.setSrgstdid(studentId);
            registration.setSrgstrid(semesterId);
            registration.setSrgregdate(LocalDate.now());
            registration.setSrgcreatedby(userId);
            registration.setSrgcreatedat(LocalDateTime.now());
            registration.setSrgrowstate(1L);
            registration = registrationRepo.save(registration);
        }

        for (CourseRegistrationDTO dto : additionalCourseDTOs) {
            List<StudentRegistrationCourses> existingRows = registrationCoursesRepo
                    .findActiveBySrgIdAndScrid(registration.getSrgid(), dto.getScrid());

            if (Boolean.TRUE.equals(dto.getIsDrop())) {
                for (StudentRegistrationCourses existing : existingRows) {
                    registrationCoursesRepo.softDeleteBySrcid(existing.getSrcid());
                }
                continue;
            }

            if (existingRows.isEmpty()) {
                StudentRegistrationCourses regCourse = new StudentRegistrationCourses();
                regCourse.setSrcid(getNextSrcId());
                regCourse.setSrcsrgid(registration.getSrgid());
                regCourse.setSrctcrid(dto.getScrtcrid());
                String regType = isBlank(dto.getRegType()) ? "PROJECT" : dto.getRegType();
                String remarks = dto.getRemarks();
                String normalizedRemarks = isBlank(remarks) || !remarks.equalsIgnoreCase(regType)
                        ? regType
                        : remarks;
                regCourse.setSrctype(regType);
                regCourse.setSrcscrid(dto.getScrid());
                regCourse.setSrcstatus("ACTIVE");
                regCourse.setSrcfield1(normalizedRemarks);
                regCourse.setOrigCtpid(dto.getCtpid());
                regCourse.setCurrCtpid(dto.getCtpid());
                regCourse.setSrccreatedby(userId);
                regCourse.setSrccreatedat(LocalDateTime.now());
                regCourse.setSrcrowstate(1L);
                registrationCoursesRepo.save(regCourse);
            } else if (dto.getCtpid() != null) {
                for (StudentRegistrationCourses existing : existingRows) {
                    boolean needsUpdate = existing.getOrigCtpid() == null || existing.getCurrCtpid() == null;
                    if (needsUpdate) {
                        if (existing.getOrigCtpid() == null) {
                            existing.setOrigCtpid(dto.getCtpid());
                        }
                        if (existing.getCurrCtpid() == null) {
                            existing.setCurrCtpid(dto.getCtpid());
                        }
                        registrationCoursesRepo.save(existing);
                    }
                }
            }
        }
    }

    private List<CourseRegistrationDTO> mapCourseRows(List<Object[]> rows) {
        return rows.stream().map(row -> {
            CourseRegistrationDTO dto = new CourseRegistrationDTO();
            dto.setScrid(row[0] == null ? null : ((Number) row[0]).longValue());
            dto.setScrcrsid(row[1] == null ? null : ((Number) row[1]).longValue());
            dto.setScrtcrid(row[2] == null ? null : ((Number) row[2]).longValue());
            dto.setCourseCode(row[3] == null ? null : row[3].toString().trim());
            dto.setCourseTitle(row[4] == null ? null : row[4].toString().trim());

            BigDecimal credits = null;
            if (row[5] instanceof BigDecimal val) {
                credits = val;
            } else if (row[5] != null) {
                credits = new BigDecimal(row[5].toString());
            }
            dto.setCredits(credits);

            Long ctpid = row[6] == null ? null : ((Number) row[6]).longValue();
            dto.setCtpid(ctpid);

            String ctpCode = row[7] == null ? null : row[7].toString().trim();
            String crsCat = row[8] == null ? null : row[8].toString().trim();
            String courseTypeFallback = row[9] == null ? null : row[9].toString().trim();

            String courseTypeCode = isBlank(ctpCode)
                    ? (isBlank(courseTypeFallback) ? "CORE" : courseTypeFallback)
                    : ctpCode;
            String courseTypeCategory = isBlank(crsCat) ? "CORE" : crsCat;

            dto.setCourseTypeCode(courseTypeCode);
            dto.setCourseType(courseTypeCategory);

            dto.setRegType("REGULAR");
            dto.setRemarks("");
            dto.setIsDrop(false);

            return dto;
        }).collect(Collectors.toList());
    }

    public StudentRegistrations getExistingRegistration(Long studentId, Long semesterId) {
        return registrationRepo.getsrgbystdidandstrid(studentId, semesterId);
    }

    public Long getNextSrgId() {
        Long maxId = registrationRepo.findMaxSrgid().orElse(0L);
        return maxId + 1;
    }

    public Long getNextSrcId() {
        Long maxId = registrationCoursesRepo.findMaxSrcId();
        return maxId + 1;
    }

    @Transactional
    public StudentRegistrations saveStudentRegistration(
            Long studentId,
            Long semesterId,
            List<CourseRegistrationDTO> courseDTOs,
            Long userId) {
        StudentRegistrations registration = getExistingRegistration(studentId, semesterId);
        if (registration == null) {
            registration = new StudentRegistrations();
            registration.setSrgid(getNextSrgId());
            registration.setSrgstdid(studentId);
            registration.setSrgstrid(semesterId);
            registration.setSrgcreatedby(userId);
            registration.setSrgcreatedat(LocalDateTime.now());
            registration.setSrgrowstate(1L);
        }
        registration.setSrgregdate(LocalDate.now());
        StudentRegistrations savedRegistration = registrationRepo.save(registration);

        for (CourseRegistrationDTO courseDTO : courseDTOs) {
            if (courseDTO.getSrcid() != null) {
                if (Boolean.TRUE.equals(courseDTO.getIsDrop())) {
                    registrationCoursesRepo.softDeleteBySrcid(courseDTO.getSrcid());
                } else if (courseDTO.getCtpid() != null) {
                    registrationCoursesRepo.findBySrcid(courseDTO.getSrcid()).ifPresent(existing -> {
                        boolean needsUpdate = existing.getOrigCtpid() == null || existing.getCurrCtpid() == null;
                        if (needsUpdate) {
                            if (existing.getOrigCtpid() == null) {
                                existing.setOrigCtpid(courseDTO.getCtpid());
                            }
                            if (existing.getCurrCtpid() == null) {
                                existing.setCurrCtpid(courseDTO.getCtpid());
                            }
                            registrationCoursesRepo.save(existing);
                        }
                    });
                }
                continue;
            }

            if (!Boolean.TRUE.equals(courseDTO.getIsDrop())) {
                StudentRegistrationCourses regCourse = new StudentRegistrationCourses();
                regCourse.setSrcid(getNextSrcId());
                regCourse.setSrcsrgid(savedRegistration.getSrgid());
                regCourse.setSrctcrid(courseDTO.getScrtcrid());
                regCourse.setSrctype(isBlank(courseDTO.getRegType()) ? "REGULAR" : courseDTO.getRegType());
                regCourse.setSrcscrid(courseDTO.getScrid());
                regCourse.setSrcstatus("ACTIVE");
                regCourse.setSrcfield1(courseDTO.getRemarks());
                regCourse.setOrigCtpid(courseDTO.getCtpid());
                regCourse.setCurrCtpid(courseDTO.getCtpid());
                regCourse.setSrccreatedby(userId);
                regCourse.setSrccreatedat(LocalDateTime.now());
                regCourse.setSrcrowstate(1L);

                registrationCoursesRepo.save(regCourse);
            }
        }

        return savedRegistration;
    }

    public List<StudentRegistrationCourses> getRegistrationCourses(Long srgId) {
        return registrationCoursesRepo.findBySrgId(srgId);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
