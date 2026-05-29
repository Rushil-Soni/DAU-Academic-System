package com.ecampus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coursetypes", schema = "ec2")
public class CourseTypes {

    @Id
    @Column(name = "ctpid")
    private Long ctpid;

    @Column(name = "scheme_id")
    private Long schemeId;

    @Column(name = "splid")
    private Long splid;

    @Column(name = "ctpcode")
    private String ctpcode;

    @Column(name = "crscat")
    private String crscat;

    @Column(name = "ctpname")
    private String ctpname;

    @Column(name = "ctpdesc")
    private String ctpdesc;

    @Column(name = "min_courses")
    private Long minCourses;

    @Column(name = "max_courses")
    private Long maxCourses;

    @Column(name = "min_credits")
    private Long minCredits;

    @Column(name = "max_credits")
    private Long maxCredits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(
            name = "scheme_id",
            referencedColumnName = "scheme_id",
            insertable = false,
            updatable = false
        ),
        @JoinColumn(
            name = "splid",
            referencedColumnName = "splid",
            insertable = false,
            updatable = false
        )
    })
    private SchemeDetails schemeDetails;

    public Long getCtpid() { return ctpid; }
    public void setCtpid(Long ctpid) { this.ctpid = ctpid; }

    public Long getSchemeId() { return schemeId; }
    public void setSchemeId(Long schemeId) { this.schemeId = schemeId; }

    public Long getSplid() { return splid; }
    public void setSplid(Long splid) { this.splid = splid; }

    public String getCtpcode() { return ctpcode; }
    public void setCtpcode(String ctpcode) { this.ctpcode = ctpcode; }

    public String getCrscat() { return crscat; }
    public void setCrscat(String crscat) { this.crscat = crscat; }

    public String getCtpname() { return ctpname; }
    public void setCtpname(String ctpname) { this.ctpname = ctpname; }

    public String getCtpdesc() { return ctpdesc; }
    public void setCtpdesc(String ctpdesc) { this.ctpdesc = ctpdesc; }

    public Long getMinCourses() { return minCourses; }
    public void setMinCourses(Long minCourses) { this.minCourses = minCourses; }

    public Long getMaxCourses() { return maxCourses; }
    public void setMaxCourses(Long maxCourses) { this.maxCourses = maxCourses; }

    public Long getMinCredits() { return minCredits; }
    public void setMinCredits(Long minCredits) { this.minCredits = minCredits; }

    public Long getMaxCredits() { return maxCredits; }
    public void setMaxCredits(Long maxCredits) { this.maxCredits = maxCredits; }

    public SchemeDetails getSchemeDetails() { return schemeDetails; }
    public void setSchemeDetails(SchemeDetails schemeDetails) { this.schemeDetails = schemeDetails; }
}
