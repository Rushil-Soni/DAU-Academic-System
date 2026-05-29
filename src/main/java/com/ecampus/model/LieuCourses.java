package com.ecampus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lieucourses", schema = "ec2")
public class LieuCourses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lcrid")
    private Integer lcrid;

    @Column(name = "lcrtcrid")
    private Integer lcrtcrid;

    @Column(name = "lcractivationflag")
    private String lcractivationflag;

    @Column(name = "lcrremarks")
    private String lcrremarks;

    @Column(name = "lcrfield1")
    private String lcrfield1;

    @Column(name = "lcrfield2")
    private String lcrfield2;

    @Column(name = "lcrfield3")
    private String lcrfield3;

    @Column(name = "lcrfield4")
    private Double lcrfield4;

    @Column(name = "lcrfield5")
    private LocalDateTime lcrfield5;

    @Column(name = "lcrcreatedby")
    private String lcrcreatedby;

    @Column(name = "lcrcreatedat")
    private LocalDateTime lcrcreatedat;

    @Column(name = "lcrlastupdatedby")
    private String lcrlastupdatedby;

    @Column(name = "lcrlastupdatedat")
    private LocalDateTime lcrlastupdatedat;

    @Column(name = "lcrrowstate")
    private Integer lcrrowstate;

    // Relationship with TermCourses
    @ManyToOne
    @JoinColumn(name = "lcrtcrid", referencedColumnName = "tcrid", insertable = false, updatable = false)
    private TermCourses termCourse;

    // ===== Getters and Setters =====

    public Integer getLcrid() {
        return lcrid;
    }

    public void setLcrid(Integer lcrid) {
        this.lcrid = lcrid;
    }

    public Integer getLcrtcrid() {
        return lcrtcrid;
    }

    public void setLcrtcrid(Integer lcrtcrid) {
        this.lcrtcrid = lcrtcrid;
    }

    public String getLcractivationflag() {
        return lcractivationflag;
    }

    public void setLcractivationflag(String lcractivationflag) {
        this.lcractivationflag = lcractivationflag;
    }

    public String getLcrremarks() {
        return lcrremarks;
    }

    public void setLcrremarks(String lcrremarks) {
        this.lcrremarks = lcrremarks;
    }

    public String getLcrfield1() {
        return lcrfield1;
    }

    public void setLcrfield1(String lcrfield1) {
        this.lcrfield1 = lcrfield1;
    }

    public String getLcrfield2() {
        return lcrfield2;
    }

    public void setLcrfield2(String lcrfield2) {
        this.lcrfield2 = lcrfield2;
    }

    public String getLcrfield3() {
        return lcrfield3;
    }

    public void setLcrfield3(String lcrfield3) {
        this.lcrfield3 = lcrfield3;
    }

    public Double getLcrfield4() {
        return lcrfield4;
    }

    public void setLcrfield4(Double lcrfield4) {
        this.lcrfield4 = lcrfield4;
    }

    public LocalDateTime getLcrfield5() {
        return lcrfield5;
    }

    public void setLcrfield5(LocalDateTime lcrfield5) {
        this.lcrfield5 = lcrfield5;
    }

    public String getLcrcreatedby() {
        return lcrcreatedby;
    }

    public void setLcrcreatedby(String lcrcreatedby) {
        this.lcrcreatedby = lcrcreatedby;
    }

    public LocalDateTime getLcrcreatedat() {
        return lcrcreatedat;
    }

    public void setLcrcreatedat(LocalDateTime lcrcreatedat) {
        this.lcrcreatedat = lcrcreatedat;
    }

    public String getLcrlastupdatedby() {
        return lcrlastupdatedby;
    }

    public void setLcrlastupdatedby(String lcrlastupdatedby) {
        this.lcrlastupdatedby = lcrlastupdatedby;
    }

    public LocalDateTime getLcrlastupdatedat() {
        return lcrlastupdatedat;
    }

    public void setLcrlastupdatedat(LocalDateTime lcrlastupdatedat) {
        this.lcrlastupdatedat = lcrlastupdatedat;
    }

    public Integer getLcrrowstate() {
        return lcrrowstate;
    }

    public void setLcrrowstate(Integer lcrrowstate) {
        this.lcrrowstate = lcrrowstate;
    }

    public TermCourses getTermCourse() {
        return termCourse;
    }

    public void setTermCourse(TermCourses termCourse) {
        this.termCourse = termCourse;
    }
}