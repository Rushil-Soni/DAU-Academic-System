package com.ecampus.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lieucoursemappings", schema = "ec2")
public class LieuCourseMappings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lcmid")
    private Integer lcmid;

    @Column(name = "lcmlcrid")
    private Integer lcmlcrid;

    @Column(name = "lcmtcrid")
    private Integer lcmtcrid;

    @Column(name = "lcmactivationflag", nullable = false)
    private String lcmactivationflag;

    @Column(name = "lcmfield1")
    private String lcmfield1;

    @Column(name = "lcmfield2")
    private String lcmfield2;

    @Column(name = "lcmfield3")
    private String lcmfield3;

    @Column(name = "lcmfield4")
    private BigDecimal lcmfield4;

    @Column(name = "lcmfield5")
    private LocalDateTime lcmfield5;

    @Column(name = "lcmcreatedby", nullable = false)
    private String lcmcreatedby;

    @Column(name = "lcmcreatedat", nullable = false)
    private LocalDateTime lcmcreatedat;

    @Column(name = "lcmlastupdatedby")
    private String lcmlastupdatedby;

    @Column(name = "lcmlastupdatedat")
    private LocalDateTime lcmlastupdatedat;

    @Column(name = "lcmrowstate", nullable = false)
    private Integer lcmrowstate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "lcmlcrid", referencedColumnName = "lcrid", insertable = false, updatable = false, nullable = false)
    private LieuCourses lieuCourse;

    @ManyToOne
    @JoinColumn(name = "lcmtcrid", referencedColumnName = "tcrid", insertable = false, updatable = false, nullable = false)
    private TermCourses termCourse;

    // ===== Getters and Setters =====

    public Integer getLcmid() {
        return lcmid;
    }

    public void setLcmid(Integer lcmid) {
        this.lcmid = lcmid;
    }

    public Integer getLcmtcrid() {
        return lcmtcrid;
    }

    public void setLcmtcrid(Integer lcmtcrid) {
        this.lcmtcrid = lcmtcrid;
    }

    public Integer getLcmlcrid() {
        return lcmlcrid;
    }

    public void setLcmlcrid(Integer lcmlcrid) {
        this.lcmlcrid = lcmlcrid;
    }

    public LieuCourses getLieuCourse() {
        return lieuCourse;
    }

    public void setLieuCourse(LieuCourses lieuCourse) {
        this.lieuCourse = lieuCourse;
    }

    public TermCourses getTermCourse() {
        return termCourse;
    }

    public void setTermCourse(TermCourses termCourse) {
        this.termCourse = termCourse;
    }

    public String getLcmactivationflag() {
        return lcmactivationflag;
    }

    public void setLcmactivationflag(String lcmactivationflag) {
        this.lcmactivationflag = lcmactivationflag;
    }

    public String getLcmfield1() {
        return lcmfield1;
    }

    public void setLcmfield1(String lcmfield1) {
        this.lcmfield1 = lcmfield1;
    }

    public String getLcmfield2() {
        return lcmfield2;
    }

    public void setLcmfield2(String lcmfield2) {
        this.lcmfield2 = lcmfield2;
    }

    public String getLcmfield3() {
        return lcmfield3;
    }

    public void setLcmfield3(String lcmfield3) {
        this.lcmfield3 = lcmfield3;
    }

    public BigDecimal getLcmfield4() {
        return lcmfield4;
    }

    public void setLcmfield4(BigDecimal lcmfield4) {
        this.lcmfield4 = lcmfield4;
    }

    public LocalDateTime getLcmfield5() {
        return lcmfield5;
    }

    public void setLcmfield5(LocalDateTime lcmfield5) {
        this.lcmfield5 = lcmfield5;
    }

    public String getLcmcreatedby() {
        return lcmcreatedby;
    }

    public void setLcmcreatedby(String lcmcreatedby) {
        this.lcmcreatedby = lcmcreatedby;
    }

    public LocalDateTime getLcmcreatedat() {
        return lcmcreatedat;
    }

    public void setLcmcreatedat(LocalDateTime lcmcreatedat) {
        this.lcmcreatedat = lcmcreatedat;
    }

    public String getLcmlastupdatedby() {
        return lcmlastupdatedby;
    }

    public void setLcmlastupdatedby(String lcmlastupdatedby) {
        this.lcmlastupdatedby = lcmlastupdatedby;
    }

    public LocalDateTime getLcmlastupdatedat() {
        return lcmlastupdatedat;
    }

    public void setLcmlastupdatedat(LocalDateTime lcmlastupdatedat) {
        this.lcmlastupdatedat = lcmlastupdatedat;
    }

    public Integer getLcmrowstate() {
        return lcmrowstate;
    }

    public void setLcmrowstate(Integer lcmrowstate) {
        this.lcmrowstate = lcmrowstate;
    }
}