package com.ecampus.dto;

import java.math.BigDecimal;

public class OverallCourseTypeProgressDTO {

    private Long ctpid;
    private String ctpcode;
    private String ctpname;
    private String crscat;
    private long minCourses;
    private long completedCourses;
    private long extraCourses;
    private int coursePercentage;
    private String courseProgressColor;
    private BigDecimal minCredits;
    private BigDecimal completedCredits;
    private BigDecimal extraCredits;
    private int creditPercentage;
    private String creditProgressColor;

    public OverallCourseTypeProgressDTO(Long ctpid, String ctpcode, String ctpname, String crscat,
                                        long minCourses, long completedCourses,
                                        BigDecimal minCredits, BigDecimal completedCredits) {
        this.ctpid = ctpid;
        this.ctpcode = ctpcode;
        this.ctpname = ctpname;
        this.crscat = crscat;
        this.minCourses = minCourses;
        this.completedCourses = completedCourses;
        this.minCredits = normalize(minCredits);
        this.completedCredits = normalize(completedCredits);
        this.extraCourses = Math.max(0L, completedCourses - minCourses);
        this.extraCredits = this.completedCredits.compareTo(this.minCredits) > 0
                ? this.completedCredits.subtract(this.minCredits)
                : BigDecimal.ZERO;
        this.coursePercentage = calculatePercentage(completedCourses, minCourses);
        this.creditPercentage = calculatePercentage(this.completedCredits, this.minCredits);
        this.courseProgressColor = resolveColor(completedCourses, minCourses, this.coursePercentage);
        this.creditProgressColor = resolveColor(this.completedCredits, this.minCredits, this.creditPercentage);
    }

    private static BigDecimal normalize(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    private static int calculatePercentage(long completed, long required) {
        if (required <= 0) {
            return 100;
        }
        return (int) Math.min(100, (completed * 100) / required);
    }

    private static int calculatePercentage(BigDecimal completed, BigDecimal required) {
        if (required.compareTo(BigDecimal.ZERO) <= 0) {
            return 100;
        }
        return completed.multiply(BigDecimal.valueOf(100))
                .divide(required, 0, java.math.RoundingMode.DOWN)
                .min(BigDecimal.valueOf(100))
                .intValue();
    }

    private static String resolveColor(long completed, long required, int percentage) {
        if (completed >= required) {
            return "success";
        }
        if (percentage >= 50) {
            return "info";
        }
        if (percentage > 0) {
            return "warning";
        }
        return "danger";
    }

    private static String resolveColor(BigDecimal completed, BigDecimal required, int percentage) {
        if (completed.compareTo(required) >= 0) {
            return "success";
        }
        if (percentage >= 50) {
            return "info";
        }
        if (percentage > 0) {
            return "warning";
        }
        return "danger";
    }

    private static String formatDecimal(BigDecimal value) {
        BigDecimal normalized = normalize(value).stripTrailingZeros();
        if (normalized.scale() < 0) {
            normalized = normalized.setScale(0);
        }
        return normalized.toPlainString();
    }

    public boolean isCourseRequirementMet() {
        return completedCourses >= minCourses;
    }

    public boolean isCreditRequirementMet() {
        return completedCredits.compareTo(minCredits) >= 0;
    }

    public boolean isFullySatisfied() {
        return isCourseRequirementMet() && isCreditRequirementMet();
    }

    public boolean isStarted() {
        return completedCourses > 0 || completedCredits.compareTo(BigDecimal.ZERO) > 0;
    }

    public long getRemainingCourses() {
        return Math.max(0L, minCourses - completedCourses);
    }

    public BigDecimal getRemainingCredits() {
        return minCredits.subtract(completedCredits).max(BigDecimal.ZERO);
    }

    public String getMinCreditsDisplay() {
        return formatDecimal(minCredits);
    }

    public String getCompletedCreditsDisplay() {
        return formatDecimal(completedCredits);
    }

    public String getExtraCreditsDisplay() {
        return formatDecimal(extraCredits);
    }

    public String getRemainingCreditsDisplay() {
        return formatDecimal(getRemainingCredits());
    }

    public Long getCtpid() {
        return ctpid;
    }

    public String getCtpcode() {
        return ctpcode;
    }

    public String getCtpname() {
        return ctpname;
    }

    public String getCrscat() {
        return crscat;
    }

    public long getMinCourses() {
        return minCourses;
    }

    public long getCompletedCourses() {
        return completedCourses;
    }

    public long getExtraCourses() {
        return extraCourses;
    }

    public int getCoursePercentage() {
        return coursePercentage;
    }

    public String getCourseProgressColor() {
        return courseProgressColor;
    }

    public BigDecimal getMinCredits() {
        return minCredits;
    }

    public BigDecimal getCompletedCredits() {
        return completedCredits;
    }

    public BigDecimal getExtraCredits() {
        return extraCredits;
    }

    public int getCreditPercentage() {
        return creditPercentage;
    }

    public String getCreditProgressColor() {
        return creditProgressColor;
    }
}