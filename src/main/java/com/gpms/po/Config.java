package com.gpms.po;

import java.io.Serializable;
import java.util.Date;

public class Config implements Serializable {
    private Integer confId;

    private String confBizeType;

    private String confStage;

    private Date confLasttime;

    private String confOperator;

    private static final long serialVersionUID = 1L;

    public Integer getConfId() {
        return confId;
    }

    public void setConfId(Integer confId) {
        this.confId = confId;
    }

    public String getConfBizeType() {
        return confBizeType;
    }

    public void setConfBizeType(String confBizeType) {
        this.confBizeType = confBizeType == null ? null : confBizeType.trim();
    }

    public String getConfStage() {
        return confStage;
    }

    public void setConfStage(String confStage) {
        this.confStage = confStage == null ? null : confStage.trim();
    }

    public Date getConfLasttime() {
        return confLasttime;
    }

    public void setConfLasttime(Date confLasttime) {
        this.confLasttime = confLasttime;
    }

    public String getConfOperator() {
        return confOperator;
    }

    public void setConfOperator(String confOperator) {
        this.confOperator = confOperator == null ? null : confOperator.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", confId=").append(confId);
        sb.append(", confBizeType=").append(confBizeType);
        sb.append(", confStage=").append(confStage);
        sb.append(", confLasttime=").append(confLasttime);
        sb.append(", confOperator=").append(confOperator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}