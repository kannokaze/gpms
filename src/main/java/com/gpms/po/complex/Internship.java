package com.gpms.po.complex;

import java.io.Serializable;
import java.util.Date;

public class Internship implements Serializable {
    private Integer lcId;

    private String lcName;

    private String lcContent;

    private Integer lcStuId;
    
    private String lcSelfcomment;
    
    private Integer proId;

    private String proName;

    private String proSign;

    private Date proStarttime;

    private Date proEndtime;

    private Integer proConId;

    private Integer proComId;

    private Integer progId;

    private String progName;

    private String progDuty;

    private Date progStarttime;

    private Date progEndtime;

    private Integer progProId;

    private Integer progStuId;
    

    private static final long serialVersionUID = 1L;

    public String getLcContent() {
        return lcContent;
    }

    public Integer getLcId() {
        return lcId;
    }

    public String getLcName() {
        return lcName;
    }

    public Integer getLcStuId() {
        return lcStuId;
    }

    public Integer getProComId() {
		return proComId;
	}

    public Integer getProConId() {
		return proConId;
	}

    public Date getProEndtime() {
		return proEndtime;
	}

    public String getProgDuty() {
		return progDuty;
	}

    public Date getProgEndtime() {
		return progEndtime;
	}

	public Integer getProgId() {
		return progId;
	}

	public String getProgName() {
		return progName;
	}

	public Integer getProgProId() {
		return progProId;
	}

	public Date getProgStarttime() {
		return progStarttime;
	}

	public Integer getProgStuId() {
		return progStuId;
	}

	public Integer getProId() {
		return proId;
	}

	public String getProName() {
		return proName;
	}

	public String getProSign() {
		return proSign;
	}

	public Date getProStarttime() {
		return proStarttime;
	}

	public void setLcContent(String lcContent) {
        this.lcContent = lcContent == null ? null : lcContent.trim();
    }

	public void setLcId(Integer lcId) {
        this.lcId = lcId;
    }

	public void setLcName(String lcName) {
        this.lcName = lcName == null ? null : lcName.trim();
    }

	public void setLcStuId(Integer lcStuId) {
        this.lcStuId = lcStuId;
    }

	public void setProComId(Integer proComId) {
		this.proComId = proComId;
	}


	public void setProConId(Integer proConId) {
		this.proConId = proConId;
	}

	public String getLcSelfcomment() {
		return lcSelfcomment;
	}

	public void setLcSelfcomment(String lcSelfcomment) {
		this.lcSelfcomment = lcSelfcomment;
	}

	public void setProEndtime(Date proEndtime) {
		this.proEndtime = proEndtime;
	}

	public void setProgDuty(String progDuty) {
		this.progDuty = progDuty;
	}

	public void setProgEndtime(Date progEndtime) {
		this.progEndtime = progEndtime;
	}

	public void setProgId(Integer progId) {
		this.progId = progId;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public void setProgProId(Integer progProId) {
		this.progProId = progProId;
	}

	public void setProgStarttime(Date progStarttime) {
		this.progStarttime = progStarttime;
	}

	public void setProgStuId(Integer progStuId) {
		this.progStuId = progStuId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setProSign(String proSign) {
		this.proSign = proSign;
	}

	public void setProStarttime(Date proStarttime) {
		this.proStarttime = proStarttime;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lcId=").append(lcId);
        sb.append(", lcName=").append(lcName);
        sb.append(", lcContent=").append(lcContent);
        sb.append(", lcStuId=").append(lcStuId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}