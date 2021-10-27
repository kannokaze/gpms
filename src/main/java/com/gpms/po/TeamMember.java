package com.gpms.po;

import java.io.Serializable;

public class TeamMember implements Serializable {
    private Integer tmId;

    private String tmDuty;

    private String tmName;

    private String tmTeamCode;

    private static final long serialVersionUID = 1L;

    public Integer getTmId() {
        return tmId;
    }

    public void setTmId(Integer tmId) {
        this.tmId = tmId;
    }

    public String getTmDuty() {
        return tmDuty;
    }

    public void setTmDuty(String tmDuty) {
        this.tmDuty = tmDuty == null ? null : tmDuty.trim();
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName == null ? null : tmName.trim();
    }

    public String getTmTeamCode() {
        return tmTeamCode;
    }

    public void setTmTeamCode(String tmTeamCode) {
        this.tmTeamCode = tmTeamCode == null ? null : tmTeamCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tmId=").append(tmId);
        sb.append(", tmDuty=").append(tmDuty);
        sb.append(", tmName=").append(tmName);
        sb.append(", tmTeamCode=").append(tmTeamCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}