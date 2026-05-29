package com.ecampus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "termcourseavailablefor", schema = "ec2")
public class TermCourseAvailableFor {

    @Id
    @Column(name = "tcaid", nullable = false)
    private Long tcaid;

    @Column(name = "tcatcrid", nullable = false)
    private Long tcatcrid;

    @Column(name = "tcaprgid", nullable = false)
    private Long tcaprgid;

    @Column(name = "tcastatus", nullable = false)
    private String tcastatus;

    @Column(name = "tcacreatedby")
    private Long tcacreatedby;

    @Column(name = "tcacreatedat", nullable = false)
    private LocalDateTime tcacreatedat;

    @Column(name = "tcalastupdatedby")
    private Long tcalastupdatedby;

    @Column(name = "tcalastupdatedat")
    private LocalDateTime tcalastupdatedat;

    @Column(name = "tcarowstate", nullable = false)
    private Long tcarowstate;

    @Column(name = "tcabchid")
    private Long tcabchid;

    @Column(name = "tcaelectivetype")
    private String tcaelectivetype;

    @Column(name = "ctpid")
    private Long ctpid;

    @Column(name = "tca_seats")
    private Long tca_seats;

    @Column(name = "tca_booked")
    private Long tca_booked;

    /* =======================
       Relationships
       ======================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "tcaprgid",
        referencedColumnName = "prgid",
        insertable = false,
        updatable = false
    )
    private Programs program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "tcatcrid",
        referencedColumnName = "tcrid",
        insertable = false,
        updatable = false
    )
    private TermCourses termCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctpid", referencedColumnName = "ctpid", insertable = false, updatable = false)
    private CourseTypes courseType;

    /* =======================
       Getters & Setters
       ======================= */

    public Long getTcaid() {
        return tcaid;
    }

    public void setTcaid(Long tcaid) {
        this.tcaid = tcaid;
    }

    public Long getTcatcrid() {
        return tcatcrid;
    }

    public void setTcatcrid(Long tcatcrid) {
        this.tcatcrid = tcatcrid;
    }

    public Long getTcaprgid() {
        return tcaprgid;
    }

    public void setTcaprgid(Long tcaprgid) {
        this.tcaprgid = tcaprgid;
    }

    public String getTcastatus() {
        return tcastatus;
    }

    public void setTcastatus(String tcastatus) {
        this.tcastatus = tcastatus;
    }

    public Long getTcacreatedby() {
        return tcacreatedby;
    }

    public void setTcacreatedby(Long tcacreatedby) {
        this.tcacreatedby = tcacreatedby;
    }

    public LocalDateTime getTcacreatedat() {
        return tcacreatedat;
    }

    public void setTcacreatedat(LocalDateTime tcacreatedat) {
        this.tcacreatedat = tcacreatedat;
    }

    public Long getTcalastupdatedby() {
        return tcalastupdatedby;
    }

    public void setTcalastupdatedby(Long tcalastupdatedby) {
        this.tcalastupdatedby = tcalastupdatedby;
    }

    public LocalDateTime getTcalastupdatedat() {
        return tcalastupdatedat;
    }

    public void setTcalastupdatedat(LocalDateTime tcalastupdatedat) {
        this.tcalastupdatedat = tcalastupdatedat;
    }

    public Long getTcarowstate() {
        return tcarowstate;
    }

    public void setTcarowstate(Long tcarowstate) {
        this.tcarowstate = tcarowstate;
    }

    public Long getTcabchid() {
        return tcabchid;
    }

    public void setTcabchid(Long tcabchid) {
        this.tcabchid = tcabchid;
    }

    public String getTcaelectivetype() {
        return tcaelectivetype;
    }

    public void setTcaelectivetype(String tcaelectivetype) {
        this.tcaelectivetype = tcaelectivetype;
    }

    public Long getCtpid() {
        return ctpid;
    }

    public void setCtpid(Long ctpid) {
        this.ctpid = ctpid;
    }

    public Long getTca_seats() {
        return tca_seats;
    }

    public void setTca_seats(Long tca_seats) {
        this.tca_seats = tca_seats;
    }

    public Long getTca_booked() {
        return tca_booked;
    }

    public void setTca_booked(Long tca_booked) {
        this.tca_booked = tca_booked;
    }

    public Programs getProgram() {
        return program;
    }

    public TermCourses getTermCourse() {
        return termCourse;
    }

    public CourseTypes getCourseType() {
        return courseType;
    }
}