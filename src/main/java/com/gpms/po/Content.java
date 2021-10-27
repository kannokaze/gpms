package com.gpms.po;

import java.io.Serializable;

public class Content implements Serializable {
    private Integer lcId;

    private String lcName;

    private String lcContent;
    
    private String lcSelfcomment;

    private String lcStuNo;

    private static final long serialVersionUID = 1L;

    public Integer getLcId() {
        return lcId;
    }

    public void setLcId(Integer lcId) {
        this.lcId = lcId;
    }

    public String getLcName() {
        return lcName;
    }

    public void setLcName(String lcName) {
        this.lcName = lcName == null ? null : lcName.trim();
    }

    public String getLcContent() {
        return lcContent;
    }

    public void setLcContent(String lcContent) {
        this.lcContent = lcContent == null ? null : lcContent.trim();
    }

    public String getLcStuNo() {
        return lcStuNo;
    }

    public void setLcStuNo(String lcStuNo) {
        this.lcStuNo = lcStuNo == null ? null : lcStuNo.trim();
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
        sb.append(", lcStuNo=").append(lcStuNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getLsSelfcomment() {
		return lcSelfcomment;
	}

	public void setLsSelfcomment(String lsSelfcomment) {
		this.lcSelfcomment = lsSelfcomment;
	}
}