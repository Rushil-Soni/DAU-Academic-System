package com.ecampus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coursepreferences", schema = "ec2")
public class CoursePreferences {

    @Id
    @Column(name = "cp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpId;

    @Column(name = "sid")
    private Long sid;

    @Column(name = "slot")
    private Long slot;

    @Column(name = "tcrid")
    private Long tcrid;

    @Column(name = "pref_index")
    private Long prefIndex;

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "stdid", insertable = false, updatable = false)
    private Students student;

    @ManyToOne
    @JoinColumn(name = "tcrid", referencedColumnName = "tcrid", insertable = false, updatable = false)
    private TermCourses termCourse;

    // Getters and Setters

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getTcrid() {
        return tcrid;
    }

    public void setTcrid(Long tcrid) {
        this.tcrid = tcrid;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }

    public Long getPrefIndex() {
        return prefIndex;
    }

    public void setPrefIndex(Long prefIndex) {
        this.prefIndex = prefIndex;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public TermCourses getTermCourse() {
        return termCourse;
    }

    public void setTermCourse(TermCourses termCourse) {
        this.termCourse = termCourse;
    }
}