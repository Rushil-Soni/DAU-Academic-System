package com.ecampus.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseConversionTypeGroupDTO {

    private Long ctpid;
    private String ctpcode;
    private String ctpname;
    private String crscat;
    private long minCourses;
    private long totalCourses;
    private List<CourseConversionCourseDTO> courses = new ArrayList<>();

    public Long getCtpid() {
        return ctpid;
    }

    public void setCtpid(Long ctpid) {
        this.ctpid = ctpid;
    }

    public String getCtpcode() {
        return ctpcode;
    }

    public void setCtpcode(String ctpcode) {
        this.ctpcode = ctpcode;
    }

    public String getCtpname() {
        return ctpname;
    }

    public void setCtpname(String ctpname) {
        this.ctpname = ctpname;
    }

    public String getCrscat() {
        return crscat;
    }

    public void setCrscat(String crscat) {
        this.crscat = crscat;
    }

    public long getMinCourses() {
        return minCourses;
    }

    public void setMinCourses(long minCourses) {
        this.minCourses = minCourses;
    }

    public long getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(long totalCourses) {
        this.totalCourses = totalCourses;
    }

    public List<CourseConversionCourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseConversionCourseDTO> courses) {
        this.courses = courses;
    }

    public long getExcessCourses() {
        return Math.max(0L, totalCourses - minCourses);
    }

    public boolean isOverMin() {
        return totalCourses > minCourses;
    }
}
