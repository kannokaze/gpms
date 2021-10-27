package com.gpms.po;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable {
    private Integer teamId;

    private String teamCode;

    private String teamName;

    private String teamSign;

    private Integer teamProId;
    
    private List<TeamMember> teamMemberList;

    private static final long serialVersionUID = 1L;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamSign() {
        return teamSign;
    }

    public void setTeamSign(String teamSign) {
        this.teamSign = teamSign == null ? null : teamSign.trim();
    }

    public Integer getTeamProId() {
        return teamProId;
    }

    public void setTeamProId(Integer teamProId) {
        this.teamProId = teamProId;
    }

    public List<TeamMember> getTeamMemberList() {
		return teamMemberList;
	}

	public void setTeamMemberList(List<TeamMember> teamMemberList) {
		this.teamMemberList = teamMemberList;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", teamId=").append(teamId);
        sb.append(", teamCode=").append(teamCode);
        sb.append(", teamName=").append(teamName);
        sb.append(", teamSign=").append(teamSign);
        sb.append(", teamProId=").append(teamProId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}