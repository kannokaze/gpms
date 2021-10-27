package com.gpms.po;

import java.io.Serializable;

public class RelationStuCom implements Serializable {
	
    private Integer scId;

    private String scStuNo;

    private String scComAcc;
    
    private String scState;
    
    private static final long serialVersionUID = 1L;

    public String getScComAcc() {
        return scComAcc;
    }

    public Integer getScId() {
        return scId;
    }

    public String getScStuNo() {
        return scStuNo;
    }

    public void setScComAcc(String scComAcc) {
        this.scComAcc = scComAcc == null ? null : scComAcc.trim();
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public void setScStuNo(String scStuNo) {
        this.scStuNo = scStuNo == null ? null : scStuNo.trim();
    }

 
	public String getScState() {
		return scState;
	}

	public void setScState(String scState) {
		this.scState = scState;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scId=").append(scId);
        sb.append(", scStuNo=").append(scStuNo);
        sb.append(", scComAcc=").append(scComAcc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}