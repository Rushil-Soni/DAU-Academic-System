package com.ecampus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "coursetypeconversion", schema = "ec2")
@IdClass(CourseTypeConversionId.class)
public class CourseTypeConversion {

    @Id
    @Column(name = "orig_ctpid")
    private Long origCtpid;

    @Id
    @Column(name = "allowed_ctpid")
    private Long allowedCtpid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orig_ctpid", referencedColumnName = "ctpid", insertable = false, updatable = false)
    private CourseTypes originalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allowed_ctpid", referencedColumnName = "ctpid", insertable = false, updatable = false)
    private CourseTypes allowedType;

    public Long getOrigCtpid() { return origCtpid; }

    public void setOrigCtpid(Long origCtpid) { this.origCtpid = origCtpid; }

    public Long getAllowedCtpid() { return allowedCtpid; }

    public void setAllowedCtpid(Long allowedCtpid) { this.allowedCtpid = allowedCtpid; }

    public CourseTypes getOriginalCourseType() { return originalType; }

    public CourseTypes getCurrentCourseType() { return allowedType; }
}