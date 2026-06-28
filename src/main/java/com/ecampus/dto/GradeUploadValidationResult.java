package com.ecampus.dto;

import java.util.ArrayList;
import java.util.List;

public class GradeUploadValidationResult {

    private List<StudentGradeDTO> validGrades = new ArrayList<>();
    private List<StudentGradeDTO> uploadedButNotRegistered = new ArrayList<>();
    private List<StudentGradeDTO> registeredButGradeMissing = new ArrayList<>();

    private int totalCsvRows;
    private int validCount;
    private int unregisteredCount;
    private int missingGradeCount;

    public List<StudentGradeDTO> getValidGrades() {
        return validGrades;
    }

    public void setValidGrades(List<StudentGradeDTO> validGrades) {
        this.validGrades = validGrades;
    }

    public List<StudentGradeDTO> getUploadedButNotRegistered() {
        return uploadedButNotRegistered;
    }

    public void setUploadedButNotRegistered(List<StudentGradeDTO> uploadedButNotRegistered) {
        this.uploadedButNotRegistered = uploadedButNotRegistered;
    }

    public List<StudentGradeDTO> getRegisteredButGradeMissing() {
        return registeredButGradeMissing;
    }

    public void setRegisteredButGradeMissing(List<StudentGradeDTO> registeredButGradeMissing) {
        this.registeredButGradeMissing = registeredButGradeMissing;
    }

    public int getTotalCsvRows() {
        return totalCsvRows;
    }

    public void setTotalCsvRows(int totalCsvRows) {
        this.totalCsvRows = totalCsvRows;
    }

    public int getValidCount() {
        return validCount;
    }

    public void setValidCount(int validCount) {
        this.validCount = validCount;
    }

    public int getUnregisteredCount() {
        return unregisteredCount;
    }

    public void setUnregisteredCount(int unregisteredCount) {
        this.unregisteredCount = unregisteredCount;
    }

    public int getMissingGradeCount() {
        return missingGradeCount;
    }

    public void setMissingGradeCount(int missingGradeCount) {
        this.missingGradeCount = missingGradeCount;
    }
}