package com.ecampus.dto;

public record SemesterCoursesViewDTO(
        String term,
        String batchsem,
        String crscode,
        String crsname,
        String crstype,
        String credithours
) {}
