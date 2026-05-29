package com.ecampus.model;

import java.io.Serializable;
import java.util.Objects;

public class Egcrstt1Id implements Serializable {

    private Long tcrid;
    private Long studId;

    public Egcrstt1Id() {}

    public Egcrstt1Id(Long tcrid, Long studId) {
        this.tcrid = tcrid;
        this.studId = studId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Egcrstt1Id that)) return false;
        return Objects.equals(tcrid, that.tcrid) &&
               Objects.equals(studId, that.studId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tcrid, studId);
    }
}