package com.ecampus.model;

import java.io.Serializable;
import java.util.Objects;

public class CourseTypeConversionId implements Serializable {

    private Long origCtpid;
    private Long allowedCtpid;

    public CourseTypeConversionId() {}

    public CourseTypeConversionId(Long origCtpid, Long allowedCtpid) {
        this.origCtpid = origCtpid;
        this.allowedCtpid = allowedCtpid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseTypeConversionId that)) return false;
        return Objects.equals(origCtpid, that.origCtpid) &&
               Objects.equals(allowedCtpid, that.allowedCtpid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origCtpid, allowedCtpid);
    }
}