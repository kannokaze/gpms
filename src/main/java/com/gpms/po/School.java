package com.gpms.po;

import java.io.Serializable;

public class School implements Serializable {
    private Integer schId;

    private String schCode;

    private String schName;

    private String schSign;

    private static final long serialVersionUID = 1L;

    public String getSchCode() {
        return schCode;
    }

    public Integer getSchId() {
        return schId;
    }

    public String getSchName() {
        return schName;
    }

    public String getSchSign() {
        return schSign;
    }

    public void setSchCode(String schCode) {
        this.schCode = schCode == null ? null : schCode.trim();
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public void setSchName(String schName) {
        this.schName = schName == null ? null : schName.trim();
    }

    public void setSchSign(String schSign) {
        this.schSign = schSign == null ? null : schSign.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", schId=").append(schId);
        sb.append(", schCode=").append(schCode);
        sb.append(", schName=").append(schName);
        sb.append(", schSign=").append(schSign);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}