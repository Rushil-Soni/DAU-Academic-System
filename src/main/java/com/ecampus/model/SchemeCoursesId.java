package com.ecampus.model;

import java.io.Serializable;
import java.util.Objects;

public class SchemeCoursesId implements Serializable {

    private Long schemeId;
    private Long splid;
    private String termName;
    private Long programYear;
    private Long courseSrNo;

    public SchemeCoursesId() {}

    public SchemeCoursesId(Long schemeId, Long splid, String termName, Long programYear, Long courseSrNo) {
        this.schemeId = schemeId;
        this.splid = splid;
        this.termName = termName;
        this.programYear = programYear;
        this.courseSrNo = courseSrNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchemeCoursesId that)) return false;
        return Objects.equals(schemeId, that.schemeId)
                && Objects.equals(splid, that.splid)
                && Objects.equals(termName, that.termName)
                && Objects.equals(programYear, that.programYear)
                && Objects.equals(courseSrNo, that.courseSrNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeId, splid, termName, programYear, courseSrNo);
    }
}
