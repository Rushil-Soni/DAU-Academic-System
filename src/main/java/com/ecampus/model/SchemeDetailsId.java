package com.ecampus.model;

import java.io.Serializable;
import java.util.Objects;

public class SchemeDetailsId implements Serializable {

    private Long schemeId;
    private Long splid;

    public SchemeDetailsId() {}

    public SchemeDetailsId(Long schemeId, Long splid) {
        this.schemeId = schemeId;
        this.splid = splid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchemeDetailsId that)) return false;
        return Objects.equals(schemeId, that.schemeId)
                && Objects.equals(splid, that.splid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeId, splid);
    }
}
