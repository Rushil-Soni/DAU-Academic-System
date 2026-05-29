package com.ecampus.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lieucoursestudents", schema = "ec2")
public class LieuCourseStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lcsid")
    private Integer lcsid;

    @Column(name = "lcslcrid")
    private Integer lcslcrid;

    @Column(name = "lcsstdid")
    private Integer lcsstdid;

    @Column(name = "lcsfield1")
    private String lcsfield1;

    @Column(name = "lcsfield2")
    private String lcsfield2;

    @Column(name = "lcsfield3")
    private String lcsfield3;

    @Column(name = "lcsfield4")
    private BigDecimal lcsfield4;

    @Column(name = "lcsfield5")
    private LocalDateTime lcsfield5;

    @Column(name = "lcscreatedby", nullable = false)
    private String lcscreatedby;

    @Column(name = "lcscreatedat", nullable = false)
    private LocalDateTime lcscreatedat;

    @Column(name = "lcslastupdatedby")
    private String lcslastupdatedby;

    @Column(name = "lcslastupdatedat")
    private LocalDateTime lcslastupdatedat;

    @Column(name = "lcsrowstate", nullable = false)
    private Integer lcsrowstate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "lcslcrid", referencedColumnName = "lcrid",insertable = false, updatable = false, nullable = false)
    private LieuCourses lieuCourse;

    @ManyToOne
    @JoinColumn(name = "lcsstdid", referencedColumnName = "stdid",insertable = false, updatable = false, nullable = false)
    private Students student;

    // ===== Getters and Setters =====

    public Integer getLcsid() {
        return lcsid;
    }

    public void setLcsid(Integer lcsid) {
        this.lcsid = lcsid;
    }

    public Integer getLcslcrid() {
        return lcslcrid;
    }

    public void setLcslcrid(Integer lcslcrid) {
        this.lcslcrid = lcslcrid;
    }

    public Integer getLcsstdid() {
        return lcsstdid;
    }

    public void setLcsstdid(Integer lcsstdid) {
        this.lcsstdid = lcsstdid;
    }

    public LieuCourses getLieuCourse() {
        return lieuCourse;
    }

    public void setLieuCourse(LieuCourses lieuCourse) {
        this.lieuCourse = lieuCourse;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public String getLcsfield1() {
        return lcsfield1;
    }

    public void setLcsfield1(String lcsfield1) {
        this.lcsfield1 = lcsfield1;
    }

    public String getLcsfield2() {
        return lcsfield2;
    }

    public void setLcsfield2(String lcsfield2) {
        this.lcsfield2 = lcsfield2;
    }

    public String getLcsfield3() {
        return lcsfield3;
    }

    public void setLcsfield3(String lcsfield3) {
        this.lcsfield3 = lcsfield3;
    }

    public BigDecimal getLcsfield4() {
        return lcsfield4;
    }

    public void setLcsfield4(BigDecimal lcsfield4) {
        this.lcsfield4 = lcsfield4;
    }

    public LocalDateTime getLcsfield5() {
        return lcsfield5;
    }

    public void setLcsfield5(LocalDateTime lcsfield5) {
        this.lcsfield5 = lcsfield5;
    }

    public String getLcscreatedby() {
        return lcscreatedby;
    }

    public void setLcscreatedby(String lcscreatedby) {
        this.lcscreatedby = lcscreatedby;
    }

    public LocalDateTime getLcscreatedat() {
        return lcscreatedat;
    }

    public void setLcscreatedat(LocalDateTime lcscreatedat) {
        this.lcscreatedat = lcscreatedat;
    }

    public String getLcslastupdatedby() {
        return lcslastupdatedby;
    }

    public void setLcslastupdatedby(String lcslastupdatedby) {
        this.lcslastupdatedby = lcslastupdatedby;
    }

    public LocalDateTime getLcslastupdatedat() {
        return lcslastupdatedat;
    }

    public void setLcslastupdatedat(LocalDateTime lcslastupdatedat) {
        this.lcslastupdatedat = lcslastupdatedat;
    }

    public Integer getLcsrowstate() {
        return lcsrowstate;
    }

    public void setLcsrowstate(Integer lcsrowstate) {
        this.lcsrowstate = lcsrowstate;
    }
}