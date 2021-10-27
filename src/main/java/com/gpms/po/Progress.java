package com.gpms.po;

import java.io.Serializable;
import java.util.Date;

public class Progress implements Serializable {
    private Integer progId;

    private String progName;

    private String progDuty;

    private Date progStarttime;

    private Date progEndtime;

    private Integer progProId;
    
    

    private static final long serialVersionUID = 1L;

    public Integer getProgId() {
        return progId;
    }

    public void setProgId(Integer progId) {
        this.progId = progId;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName == null ? null : progName.trim();
    }

    public String getProgDuty() {
        return progDuty;
    }

    public void setProgDuty(String progDuty) {
        this.progDuty = progDuty == null ? null : progDuty.trim();
    }

    public Date getProgStarttime() {
        return progStarttime;
    }

    public void setProgStarttime(Date progStarttime) {
        this.progStarttime = progStarttime;
    }

    public Date getProgEndtime() {
        return progEndtime;
    }

    public void setProgEndtime(Date progEndtime) {
        this.progEndtime = progEndtime;
    }

    public Integer getProgProId() {
        return progProId;
    }

    public void setProgProId(Integer progProId) {
        this.progProId = progProId;
    }


	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", progId=").append(progId);
        sb.append(", progName=").append(progName);
        sb.append(", progDuty=").append(progDuty);
        sb.append(", progStarttime=").append(progStarttime);
        sb.append(", progEndtime=").append(progEndtime);
        sb.append(", progProId=").append(progProId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}