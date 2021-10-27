package com.gpms.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class Achievement implements Serializable {
    private Integer achId;

    private String achTeaComment;

    private String achComComment;

    private BigDecimal achTeaScore;

    private BigDecimal achComScore;

    private BigDecimal achLastScore;

    private String achStatus;

    private Integer achStuNo;

    private static final long serialVersionUID = 1L;

    public Integer getAchId() {
        return achId;
    }

    public void setAchId(Integer achId) {
        this.achId = achId;
    }

    public String getAchTeaComment() {
        return achTeaComment;
    }

    public void setAchTeaComment(String achTeaComment) {
        this.achTeaComment = achTeaComment == null ? null : achTeaComment.trim();
    }

    public String getAchComComment() {
        return achComComment;
    }

    public void setAchComComment(String achComComment) {
        this.achComComment = achComComment == null ? null : achComComment.trim();
    }

    public BigDecimal getAchTeaScore() {
        return achTeaScore;
    }

    public void setAchTeaScore(BigDecimal achTeaScore) {
        this.achTeaScore = achTeaScore;
    }

    public BigDecimal getAchComScore() {
        return achComScore;
    }

    public void setAchComScore(BigDecimal achComScore) {
        this.achComScore = achComScore;
    }

    public BigDecimal getAchLastScore() {
        return achLastScore;
    }

    public void setAchLastScore(BigDecimal achLastScore) {
        this.achLastScore = achLastScore;
    }

    public String getAchStatus() {
        return achStatus;
    }

    public void setAchStatus(String achStatus) {
        this.achStatus = achStatus == null ? null : achStatus.trim();
    }

    public Integer getAchStuNo() {
        return achStuNo;
    }

    public void setAchStuNo(Integer achStuNo) {
        this.achStuNo = achStuNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", achId=").append(achId);
        sb.append(", achTeaComment=").append(achTeaComment);
        sb.append(", achComComment=").append(achComComment);
        sb.append(", achTeaScore=").append(achTeaScore);
        sb.append(", achComScore=").append(achComScore);
        sb.append(", achLastScore=").append(achLastScore);
        sb.append(", achStatus=").append(achStatus);
        sb.append(", achStuNo=").append(achStuNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}