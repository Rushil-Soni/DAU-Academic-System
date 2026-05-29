package com.ecampus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "studentcourserequirements", schema = "ec2")
public class StudentCourseRequirements {

    @Id
    @Column(name = "scr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrId;

    @Column(name = "sid")
    private Long sid;

    @Column(name = "elect_type")
    private String electType;

    @Column(name = "count")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "stdid", insertable = false, updatable = false)
    private Students student;

    // Getters and Setters

    public Long getScrId() {
        return scrId;
    }

    public void setScrId(Long scrId) {
        this.scrId = scrId;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getElectType() {
        return electType;
    }

    public void setElectType(String electType) {
        this.electType = electType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}