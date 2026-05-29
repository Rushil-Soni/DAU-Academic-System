package com.ecampus.model;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationOpenForId implements Serializable {

    private Long termid;
    private Long batchid;
    private String registrationtype;

    public RegistrationOpenForId() {}

    public RegistrationOpenForId(Long termid, Long batchid, String registrationtype) {
        this.termid = termid;
        this.batchid = batchid;
        this.registrationtype = registrationtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationOpenForId)) return false;
        RegistrationOpenForId that = (RegistrationOpenForId) o;
        return Objects.equals(termid, that.termid) &&
               Objects.equals(batchid, that.batchid) &&
               Objects.equals(registrationtype, that.registrationtype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termid, batchid, registrationtype);
    }
}