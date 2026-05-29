package com.ecampus.dto;

public class CourseTypeProgressDTO {

    private Long ctpid;
    private String ctpcode;
    private String ctpname;
    private String crscat;
    private long requiredCount;
    private long completedCount;
    private long extraCount;
    private int percentage;
    private String progressColor;

    public CourseTypeProgressDTO() {}

    public CourseTypeProgressDTO(Long ctpid, String ctpcode, String ctpname, String crscat,
                                  long requiredCount, long completedCount) {
        this.ctpid = ctpid;
        this.ctpcode = ctpcode;
        this.ctpname = ctpname;
        this.crscat = crscat;
        this.requiredCount = requiredCount;
        this.completedCount = completedCount;
        this.extraCount = completedCount > requiredCount ? completedCount - requiredCount : 0;
        this.percentage = requiredCount > 0 ? (int) Math.min(100, (completedCount * 100) / requiredCount) : (completedCount >= 0 ? 100 : 0);

        if (completedCount >= requiredCount) {
            this.progressColor = "success";
        } else if (percentage >= 50) {
            this.progressColor = "info";
        } else if (percentage > 0) {
            this.progressColor = "warning";
        } else {
            this.progressColor = "danger";
        }
    }

    public Long getCtpid() { return ctpid; }
    public void setCtpid(Long ctpid) { this.ctpid = ctpid; }

    public String getCtpcode() { return ctpcode; }
    public void setCtpcode(String ctpcode) { this.ctpcode = ctpcode; }

    public String getCtpname() { return ctpname; }
    public void setCtpname(String ctpname) { this.ctpname = ctpname; }

    public String getCrscat() { return crscat; }
    public void setCrscat(String crscat) { this.crscat = crscat; }

    public long getRequiredCount() { return requiredCount; }
    public void setRequiredCount(long requiredCount) { this.requiredCount = requiredCount; }

    public long getCompletedCount() { return completedCount; }
    public void setCompletedCount(long completedCount) { this.completedCount = completedCount; }

    public int getPercentage() { return percentage; }
    public void setPercentage(int percentage) { this.percentage = percentage; }

    public long getExtraCount() { return extraCount; }
    public void setExtraCount(long extraCount) { this.extraCount = extraCount; }

    public String getProgressColor() { return progressColor; }
    public void setProgressColor(String progressColor) { this.progressColor = progressColor; }
}
