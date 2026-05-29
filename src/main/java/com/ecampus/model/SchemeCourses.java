package com.ecampus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "schemecourses", schema="ec2")
@IdClass(SchemeCoursesId.class)
public class SchemeCourses {

    // ---- PRIMARY KEY ----

    @Id
    @Column(name = "scheme_id")
    private Long schemeId;

    @Id
    @Column(name = "splid")
    private Long splid;

    @Id
    @Column(name = "term_name")
    private String termName;

    @Id
    @Column(name = "program_year")
    private Long programYear;

    @Id
    @Column(name = "course_sr_no")
    private Long courseSrNo;

    // ---- NON-PK ----

    @Column(name = "sem_no")
    private Long semNo;

    @Column(name = "semester_name")
    private String semesterName;

    @Column(name = "term_seq_no")
    private Long termSeqNo;

    // ---- FOREIGN KEY TO CourseTypes ----

    @Column(name = "ctpid")
    private Long ctpid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctpid", insertable = false, updatable = false)
    private CourseTypes courseType;

    // ---- FOREIGN KEY TO Courses ----

    @Column(name = "crsid")
    private Long crsid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crsid", insertable = false, updatable = false)
    private Courses course;

    // ---- COURSE INFO ----

    @Column(name = "course_title")
    private String courseTitle;

    // ---- getters & setters ----

    public Long getSchemeId() {
        return schemeId;
    }
    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public Long getSplid() { return splid; }
    public void setSplid(Long splid) { this.splid = splid; }

    public String getTermName() { return termName; }
    public void setTermName(String termName) { this.termName = termName; }

    public Long getProgramYear() { return programYear; }
    public void setProgramYear(Long programYear) { this.programYear = programYear; }

    public Long getCourseSrNo() {
        return courseSrNo;
    }
    public void setCourseSrNo(Long courseSrNo) {
        this.courseSrNo = courseSrNo;
    }

    public Long getSemNo() {
        return semNo;
    }
    public void setSemNo(Long semNo) {
        this.semNo = semNo;
    }

    public String getSemesterName() { return semesterName; }
    public void setSemesterName(String semesterName) { this.semesterName = semesterName; }

    public Long getTermSeqNo() { return termSeqNo; }
    public void setTermSeqNo(Long termSeqNo) { this.termSeqNo = termSeqNo; }

    public Long getCtpid() { return ctpid; }
    public void setCtpid(Long ctpid) { this.ctpid = ctpid; }

    public CourseTypes getCourseType() { return courseType; }
    public void setCourseType(CourseTypes courseType) { this.courseType = courseType; }

    public Long getCrsid() { return crsid; }
    public void setCrsid(Long crsid) { this.crsid = crsid; }

    public Courses getCourse() { return course; }
    public void setCourse(Courses course) { this.course = course; }

    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
