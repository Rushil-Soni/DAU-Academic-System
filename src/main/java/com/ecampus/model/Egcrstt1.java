package com.ecampus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "egcrstt1", schema = "ec2")
@IdClass(Egcrstt1Id.class)
public class Egcrstt1 {

    @Id
    @Column(name = "tcrid")
    private Long tcrid;

    @Id
    @Column(name = "stud_id")
    private Long studId;

    @Column(name = "examtype_id")
    private Long examtypeId;

    @Column(name = "obtgr_id")
    private Long obtgrId;

    @Column(name = "obt_credits")
    private BigDecimal obtCredits;

    @Column(name = "crst_field1")
    private String crstField1;

    @Column(name = "creat_by")
    private Long creatBy;

    @Column(name = "creat_dt")
    private LocalDateTime creatDt;

    @Column(name = "updat_by")
    private Long updatBy;

    @Column(name = "updat_dt")
    private LocalDateTime updatDt;

    @Column(name = "row_st")
    private String rowSt;

    @Column(name = "crsid")
    private Long crsid;

    @Column(name = "obt_mks")
    private Float obtMks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obtgr_id", referencedColumnName = "grad_id", insertable = false, updatable = false)
    private Eggradm1 grade;


    // Getters and Setters

    public Long getTcrid() { return tcrid; }
    public void setTcrid(Long tcrid) { this.tcrid = tcrid; }

    public Long getStudId() { return studId; }
    public void setStudId(Long studId) { this.studId = studId; }

    public Long getExamtypeId() { return examtypeId; }
    public void setExamtypeId(Long examtypeId) { this.examtypeId = examtypeId; }

    public Long getObtgrId() { return obtgrId; }
    public void setObtgrId(Long obtgrId) { this.obtgrId = obtgrId; }

    public BigDecimal getObtCredits() { return obtCredits; }
    public void setObtCredits(BigDecimal obtCredits) { this.obtCredits = obtCredits; }

    public String getCrstField1() { return crstField1; }
    public void setCrstField1(String crstField1) { this.crstField1 = crstField1; }

    public Long getCreatBy() { return creatBy; }
    public void setCreatBy(Long creatBy) { this.creatBy = creatBy; }

    public LocalDateTime getCreatDt() { return creatDt; }
    public void setCreatDt(LocalDateTime creatDt) { this.creatDt = creatDt; }

    public Long getUpdatBy() { return updatBy; }
    public void setUpdatBy(Long updatBy) { this.updatBy = updatBy; }

    public LocalDateTime getUpdatDt() { return updatDt; }
    public void setUpdatDt(LocalDateTime updatDt) { this.updatDt = updatDt; }

    public String getRowSt() { return rowSt; }
    public void setRowSt(String rowSt) { this.rowSt = rowSt; }

    public Long getCrsid() { return crsid; }
    public void setCrsid(Long crsid) { this.crsid = crsid; }

    public Float getObtMks() { return obtMks; }
    public void setObtMks(Float obtMks) { this.obtMks = obtMks; }

    public Eggradm1 getGrade() { return grade; }
}