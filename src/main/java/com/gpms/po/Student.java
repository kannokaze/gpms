package com.gpms.po;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer stuId;

    private String stuNo;

    private String stuName;

    private String stuPassword;

    private String stuPhone;

    private String stuEmail;

    private Integer stuGender;

    private String stuAddress;

    private String stuState;

    private String stuStage;

    private String stuClassNo;

    private String stuTeaNo;

    private String stuMajCode;

    private String stuDirCode;
    
    private String className;

    private String teaName;

    private String majName;
    
    private String dirName;

    private Integer stuSchId;
    
    private String scState;

    private static final long serialVersionUID = 1L;

    public String getClassName() {
		return className;
	}

    public String getDirName() {
		return dirName;
	}

    public String getMajName() {
		return majName;
	}

    public String getStuAddress() {
        return stuAddress;
    }

    public String getStuClassNo() {
        return stuClassNo;
    }

    public String getStuDirCode() {
        return stuDirCode;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public Integer getStuGender() {
        return stuGender;
    }

    public Integer getStuId() {
        return stuId;
    }

    public String getStuMajCode() {
        return stuMajCode;
    }

    public String getStuName() {
        return stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public Integer getStuSchId() {
        return stuSchId;
    }

    public String getStuStage() {
        return stuStage;
    }

    public String getStuState() {
        return stuState;
    }

    public String getStuTeaNo() {
        return stuTeaNo;
    }

    public String getTeaName() {
		return teaName;
	}

    public void setClassName(String className) {
		this.className = className;
	}

    public void setDirName(String dirName) {
		this.dirName = dirName;
	}

    public void setMajName(String majName) {
		this.majName = majName;
	}

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress == null ? null : stuAddress.trim();
    }

    public void setStuClassNo(String stuClassNo) {
        this.stuClassNo = stuClassNo == null ? null : stuClassNo.trim();
    }

    public void setStuDirCode(String stuDirCode) {
        this.stuDirCode = stuDirCode == null ? null : stuDirCode.trim();
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail == null ? null : stuEmail.trim();
    }

    public void setStuGender(Integer stuGender) {
        this.stuGender = stuGender;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public void setStuMajCode(String stuMajCode) {
        this.stuMajCode = stuMajCode == null ? null : stuMajCode.trim();
    }

	public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

	public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
    }

	public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword == null ? null : stuPassword.trim();
    }

	public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone == null ? null : stuPhone.trim();
    }

	public void setStuSchId(Integer stuSchId) {
        this.stuSchId = stuSchId;
    }

	public void setStuStage(String stuStage) {
        this.stuStage = stuStage == null ? null : stuStage.trim();
    }

	public void setStuState(String stuState) {
        this.stuState = stuState == null ? null : stuState.trim();
    }

	public void setStuTeaNo(String stuTeaNo) {
        this.stuTeaNo = stuTeaNo == null ? null : stuTeaNo.trim();
    }

    public void setTeaName(String teaName) {
		this.teaName = teaName;
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
        sb.append(", stuId=").append(stuId);
        sb.append(", stuNo=").append(stuNo);
        sb.append(", stuName=").append(stuName);
        sb.append(", stuPassword=").append(stuPassword);
        sb.append(", stuPhone=").append(stuPhone);
        sb.append(", stuEmail=").append(stuEmail);
        sb.append(", stuGender=").append(stuGender);
        sb.append(", stuAddress=").append(stuAddress);
        sb.append(", stuState=").append(stuState);
        sb.append(", stuStage=").append(stuStage);
        sb.append(", stuClassNo=").append(stuClassNo);
        sb.append(", stuTeaNo=").append(stuTeaNo);
        sb.append(", stuMajCode=").append(stuMajCode);
        sb.append(", stuDirCode=").append(stuDirCode);
        sb.append(", stuSchId=").append(stuSchId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}