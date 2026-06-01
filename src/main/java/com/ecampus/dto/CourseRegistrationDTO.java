package com.ecampus.dto;

import java.math.BigDecimal;

/**
 * DTO for displaying course information during student registration
 */
public class CourseRegistrationDTO {
    
    private Long srcid;                   // StudentRegistrationCourses ID (existing row)
    private Long scrid;                    // SemesterCourses ID
    private Long scrcrsid;                 // Courses ID
    private Long scrtcrid;                 // Term Courses ID
    private String courseCode;             // Course code (from Courses.crscode)
    private String courseTitle;            // Course title (from Courses.crstitle)
    private BigDecimal credits;            // Credit points (from Courses.crscreditpoints)
    private Long ctpid;                     // CourseTypes ID
    private String courseType;             // Course type (from CourseTypes.crscat)
    private String courseTypeCode;         // Course type code (from CourseTypes.ctpcode)
    private String regType;                // Registration type (REGULAR/BACKLOG/GRADE-IMPROVEMENT/AUDIT)
    private String remarks;                // Registration remarks
    private Boolean isDrop;                // Mark for dropping
    
    // Getters and Setters
    public Long getSrcid() { return srcid; }
    public void setSrcid(Long srcid) { this.srcid = srcid; }

    public Long getScrid() { return scrid; }
    public void setScrid(Long scrid) { this.scrid = scrid; }
    
    public Long getScrcrsid() { return scrcrsid; }
    public void setScrcrsid(Long scrcrsid) { this.scrcrsid = scrcrsid; }
    
    public Long getScrtcrid() { return scrtcrid; }
    public void setScrtcrid(Long scrtcrid) { this.scrtcrid = scrtcrid; }
    
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    
    public BigDecimal getCredits() { return credits; }
    public void setCredits(BigDecimal credits) { this.credits = credits; }

    public Long getCtpid() { return ctpid; }
    public void setCtpid(Long ctpid) { this.ctpid = ctpid; }
    
    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }
    
    public String getCourseTypeCode() { return courseTypeCode; }
    public void setCourseTypeCode(String courseTypeCode) { this.courseTypeCode = courseTypeCode; }
    
    public String getRegType() { return regType; }
    public void setRegType(String regType) { this.regType = regType; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public Boolean getIsDrop() { return isDrop; }
    public void setIsDrop(Boolean isDrop) { this.isDrop = isDrop; }
}
