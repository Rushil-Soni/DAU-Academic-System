package com.ecampus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "slotpreferences", schema = "ec2")
public class SlotPreferences {

    @Id
    @Column(name = "sp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spId;

    @Column(name = "sid")
    private Long sid;

    @Column(name = "slot")
    private Long slot;

    @Column(name = "pref_index")
    private Long prefIndex;

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "stdid", insertable = false, updatable = false)
    private Students student;

    // Getters and Setters

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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
}