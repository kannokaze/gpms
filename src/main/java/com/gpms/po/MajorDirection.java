package com.gpms.po;

import java.io.Serializable;

public class MajorDirection implements Serializable {
    private Integer dirId;

    private String dirCode;

    private String dirName;

    private String dirSign;

    private String dirMarCode;

    private static final long serialVersionUID = 1L;

    public Integer getDirId() {
        return dirId;
    }

    public void setDirId(Integer dirId) {
        this.dirId = dirId;
    }

    public String getDirCode() {
        return dirCode;
    }

    public void setDirCode(String dirCode) {
        this.dirCode = dirCode == null ? null : dirCode.trim();
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName == null ? null : dirName.trim();
    }

    public String getDirSign() {
        return dirSign;
    }

    public void setDirSign(String dirSign) {
        this.dirSign = dirSign == null ? null : dirSign.trim();
    }

    public String getDirMarCode() {
        return dirMarCode;
    }

    public void setDirMarCode(String dirMarCode) {
        this.dirMarCode = dirMarCode == null ? null : dirMarCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dirId=").append(dirId);
        sb.append(", dirCode=").append(dirCode);
        sb.append(", dirName=").append(dirName);
        sb.append(", dirSign=").append(dirSign);
        sb.append(", dirMarCode=").append(dirMarCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}