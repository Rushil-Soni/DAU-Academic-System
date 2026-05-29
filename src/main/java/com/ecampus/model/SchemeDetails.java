package com.ecampus.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "schemedetails", schema = "ec2")
@IdClass(SchemeDetailsId.class)
public class SchemeDetails {

    // ---- COMPOSITE PRIMARY KEY ----

    @Id
    @Column(name = "scheme_id")
    private Long schemeId;

    @Id
    @Column(name = "splid")
    private Long splid;

    // ---- SPECIALIZATION INFO ----

    @Column(name = "splname")
    private String splname;

    @Column(name = "spldesc")
    private String spldesc;

    // ---- RULES / LIMITS ----

    @Column(name = "spl_min_credit_load")
    private Long splMinCreditLoad;

    @Column(name = "spl_max_credit_load")
    private Long splMaxCreditLoad;

    @Column(name = "spl_min_courses")
    private Long splMinCourses;

    @Column(name = "spl_max_courses")
    private Long splMaxCourses;

    @Column(name = "spl_min_cpi", precision = 4, scale = 2)
    private BigDecimal splMinCpi;

    @Column(name = "spl_min_credits")
    private Long splMinCredits;

    // ---- getters & setters ----

    public Long getSchemeId() { return schemeId; }
    public void setSchemeId(Long schemeId) { this.schemeId = schemeId; }

    public Long getSplid() { return splid; }
    public void setSplid(Long splid) { this.splid = splid; }

    public String getSplname() { return splname; }
    public void setSplname(String splname) { this.splname = splname; }

    public String getSpldesc() { return spldesc; }
    public void setSpldesc(String spldesc) { this.spldesc = spldesc; }

    public Long getSplMinCreditLoad() { return splMinCreditLoad; }
    public void setSplMinCreditLoad(Long splMinCreditLoad) { this.splMinCreditLoad = splMinCreditLoad; }

    public Long getSplMaxCreditLoad() { return splMaxCreditLoad; }
    public void setSplMaxCreditLoad(Long splMaxCreditLoad) { this.splMaxCreditLoad = splMaxCreditLoad; }

    public Long getSplMinCourses() { return splMinCourses; }
    public void setSplMinCourses(Long splMinCourses) { this.splMinCourses = splMinCourses; }

    public Long getSplMaxCourses() { return splMaxCourses; }
    public void setSplMaxCourses(Long splMaxCourses) { this.splMaxCourses = splMaxCourses; }

    public BigDecimal getSplMinCpi() { return splMinCpi; }
    public void setSplMinCpi(BigDecimal splMinCpi) { this.splMinCpi = splMinCpi; }

    public Long getSplMinCredits() { return splMinCredits; }
    public void setSplMinCredits(Long splMinCredits) { this.splMinCredits = splMinCredits; }
}
