package com.ecampus.dto;

public class CourseTypeOptionDTO {

    private Long ctpid;
    private String ctpcode;

    public CourseTypeOptionDTO() {
    }

    public CourseTypeOptionDTO(Long ctpid, String ctpcode) {
        this.ctpid = ctpid;
        this.ctpcode = ctpcode;
    }

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
}
