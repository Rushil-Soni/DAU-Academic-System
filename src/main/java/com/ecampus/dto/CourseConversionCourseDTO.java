package com.ecampus.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseConversionCourseDTO {

    private Long srcid;
    private Long tcrid;
    private Long origCtpid;
    private Long currCtpid;
    private String origCtpcode;
    private String currCtpcode;
    private String crscode;
    private String crsname;
    private String creditHours;
    private boolean dropdownEnabled;
    private boolean changedFromOriginal;
    private List<CourseTypeOptionDTO> options = new ArrayList<>();

    public Long getSrcid() {
        return srcid;
    }

    public void setSrcid(Long srcid) {
        this.srcid = srcid;
    }

    public Long getTcrid() {
        return tcrid;
    }

    public void setTcrid(Long tcrid) {
        this.tcrid = tcrid;
    }

    public Long getOrigCtpid() {
        return origCtpid;
    }

    public void setOrigCtpid(Long origCtpid) {
        this.origCtpid = origCtpid;
    }

    public Long getCurrCtpid() {
        return currCtpid;
    }

    public void setCurrCtpid(Long currCtpid) {
        this.currCtpid = currCtpid;
    }

    public String getOrigCtpcode() {
        return origCtpcode;
    }

    public void setOrigCtpcode(String origCtpcode) {
        this.origCtpcode = origCtpcode;
    }

    public String getCurrCtpcode() {
        return currCtpcode;
    }

    public void setCurrCtpcode(String currCtpcode) {
        this.currCtpcode = currCtpcode;
    }

    public String getCrscode() {
        return crscode;
    }

    public void setCrscode(String crscode) {
        this.crscode = crscode;
    }

    public String getCrsname() {
        return crsname;
    }

    public void setCrsname(String crsname) {
        this.crsname = crsname;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public boolean isDropdownEnabled() {
        return dropdownEnabled;
    }

    public void setDropdownEnabled(boolean dropdownEnabled) {
        this.dropdownEnabled = dropdownEnabled;
    }

    public boolean isChangedFromOriginal() {
        return changedFromOriginal;
    }

    public void setChangedFromOriginal(boolean changedFromOriginal) {
        this.changedFromOriginal = changedFromOriginal;
    }

    public List<CourseTypeOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<CourseTypeOptionDTO> options) {
        this.options = options;
    }
}
