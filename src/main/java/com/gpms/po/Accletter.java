package com.gpms.po;

import java.io.Serializable;
import java.util.Date;

public class Accletter implements Serializable {
    private Integer lalId;

    private String lalOriginalName;

    private String lalNewName;

    private String lalPath;

    private String lalRelativePath;

    private String lalSize;

    private String lalType;

    private Date lalUpdatetime;

    private Boolean lalDelFlag;

    private String lalStuNo;

    private static final long serialVersionUID = 1L;

    public Integer getLalId() {
        return lalId;
    }

    public void setLalId(Integer lalId) {
        this.lalId = lalId;
    }

    public String getLalOriginalName() {
        return lalOriginalName;
    }

    public void setLalOriginalName(String lalOriginalName) {
        this.lalOriginalName = lalOriginalName == null ? null : lalOriginalName.trim();
    }

    public String getLalNewName() {
        return lalNewName;
    }

    public void setLalNewName(String lalNewName) {
        this.lalNewName = lalNewName == null ? null : lalNewName.trim();
    }

    public String getLalPath() {
        return lalPath;
    }

    public void setLalPath(String lalPath) {
        this.lalPath = lalPath == null ? null : lalPath.trim();
    }

    public String getLalRelativePath() {
        return lalRelativePath;
    }

    public void setLalRelativePath(String lalRelativePath) {
        this.lalRelativePath = lalRelativePath == null ? null : lalRelativePath.trim();
    }

    public String getLalSize() {
        return lalSize;
    }

    public void setLalSize(String lalSize) {
        this.lalSize = lalSize == null ? null : lalSize.trim();
    }

    public String getLalType() {
        return lalType;
    }

    public void setLalType(String lalType) {
        this.lalType = lalType == null ? null : lalType.trim();
    }

    public Date getLalUpdatetime() {
        return lalUpdatetime;
    }

    public void setLalUpdatetime(Date lalUpdatetime) {
        this.lalUpdatetime = lalUpdatetime;
    }

    public Boolean getLalDelFlag() {
        return lalDelFlag;
    }

    public void setLalDelFlag(Boolean lalDelFlag) {
        this.lalDelFlag = lalDelFlag;
    }

    public String getLalStuNo() {
        return lalStuNo;
    }

    public void setLalStuNo(String lalStuNo) {
        this.lalStuNo = lalStuNo == null ? null : lalStuNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lalId=").append(lalId);
        sb.append(", lalOriginalName=").append(lalOriginalName);
        sb.append(", lalNewName=").append(lalNewName);
        sb.append(", lalPath=").append(lalPath);
        sb.append(", lalRelativePath=").append(lalRelativePath);
        sb.append(", lalSize=").append(lalSize);
        sb.append(", lalType=").append(lalType);
        sb.append(", lalUpdatetime=").append(lalUpdatetime);
        sb.append(", lalDelFlag=").append(lalDelFlag);
        sb.append(", lalStuNo=").append(lalStuNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}