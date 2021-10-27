package com.gpms.po;

import java.io.Serializable;

public class Class implements Serializable {
    private Integer classId;

    private String classNo;

    private String className;

    private String classMajCode;
    
    private String majName;

    private static final long serialVersionUID = 1L;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo == null ? null : classNo.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassMajCode() {
        return classMajCode;
    }

    public void setClassMajCode(String classMajCode) {
        this.classMajCode = classMajCode == null ? null : classMajCode.trim();
    }

    
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classId=").append(classId);
        sb.append(", classNo=").append(classNo);
        sb.append(", className=").append(className);
        sb.append(", classMajCode=").append(classMajCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getMajName() {
		return majName;
	}

	public void setMajName(String majName) {
		this.majName = majName;
	}
}