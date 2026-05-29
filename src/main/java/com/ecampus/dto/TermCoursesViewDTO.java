package com.ecampus.dto;

public record TermCoursesViewDTO(
        String ayrname,
        String term,
        String crscode,
        String crsname,
        String crstype,
        String credithours
) {}
