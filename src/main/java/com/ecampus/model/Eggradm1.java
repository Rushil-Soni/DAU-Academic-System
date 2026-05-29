package com.ecampus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "eggradm1", schema = "ec2")
public class Eggradm1 {

    @Id
    @Column(name = "grad_id")
    private Long gradId;

    @Column(name = "grad_lt")
    private String gradLt;

    @Column(name = "grad_pt")
    private BigDecimal gradPt;

    @Column(name = "creat_by")
    private Long creatBy;

    @Column(name = "creat_dt")
    private LocalDateTime creatDt;

    @Column(name = "updat_by")
    private Long updatBy;

    @Column(name = "updat_dt")
    private LocalDateTime updatDt;

    @Column(name = "row_st")
    private Long rowSt;


    // Getters and Setters

    public Long getGradId() { return gradId; }
    public void setGradId(Long gradId) { this.gradId = gradId; }

    public String getGradLt() { return gradLt; }
    public void setGradLt(String gradLt) { this.gradLt = gradLt; }

    public BigDecimal getGradPt() { return gradPt; }
    public void setGradPt(BigDecimal gradPt) { this.gradPt = gradPt; }

    public Long getCreatBy() { return creatBy; }
    public void setCreatBy(Long creatBy) { this.creatBy = creatBy; }

    public LocalDateTime getCreatDt() { return creatDt; }
    public void setCreatDt(LocalDateTime creatDt) { this.creatDt = creatDt; }

    public Long getUpdatBy() { return updatBy; }
    public void setUpdatBy(Long updatBy) { this.updatBy = updatBy; }

    public LocalDateTime getUpdatDt() { return updatDt; }
    public void setUpdatDt(LocalDateTime updatDt) { this.updatDt = updatDt; }

    public Long getRowSt() { return rowSt; }
    public void setRowSt(Long rowSt) { this.rowSt = rowSt; }
}