package com.gpms.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {
    private Integer proId;

    private String proName;

    private String proSign;

    private Date proStarttime;

    private Date proEndtime;

    private Integer proConId;

    private String proComAcc;
    
    private Team team;
    
    private List<Progress> progressList;

    private static final long serialVersionUID = 1L;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getProSign() {
        return proSign;
    }

    public void setProSign(String proSign) {
        this.proSign = proSign == null ? null : proSign.trim();
    }

    public Date getProStarttime() {
        return proStarttime;
    }

    public void setProStarttime(Date proStarttime) {
        this.proStarttime = proStarttime;
    }

    public Date getProEndtime() {
        return proEndtime;
    }

    public void setProEndtime(Date proEndtime) {
        this.proEndtime = proEndtime;
    }

    public Integer getProConId() {
        return proConId;
    }

    public void setProConId(Integer proConId) {
        this.proConId = proConId;
    }

    public String getProComAcc() {
        return proComAcc;
    }

    public void setProComAcc(String proComAcc) {
        this.proComAcc = proComAcc == null ? null : proComAcc.trim();
    }

    public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Progress> getProgressList() {
		return progressList;
	}

	public void setProgressList(List<Progress> progressList) {
		this.progressList = progressList;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", proId=").append(proId);
        sb.append(", proName=").append(proName);
        sb.append(", proSign=").append(proSign);
        sb.append(", proStarttime=").append(proStarttime);
        sb.append(", proEndtime=").append(proEndtime);
        sb.append(", proConId=").append(proConId);
        sb.append(", proComAcc=").append(proComAcc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}