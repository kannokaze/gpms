package com.gpms.po;

import java.io.Serializable;

public class Teacher implements Serializable {
    private Integer teaId;

    private String teaNo;

    private String teaName;

    private String teaPassword;

    private String teaPhone;

    private String teaEmail;

    private String teaState;

    private String teaMajCode;

    private String teaDirCode;

    private Integer teaSchId;

    private static final long serialVersionUID = 1L;

    public String getTeaDirCode() {
        return teaDirCode;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public String getTeaMajCode() {
        return teaMajCode;
    }

    public String getTeaName() {
        return teaName;
    }

    public String getTeaNo() {
        return teaNo;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public Integer getTeaSchId() {
        return teaSchId;
    }

    public String getTeaState() {
        return teaState;
    }

    public void setTeaDirCode(String teaDirCode) {
        this.teaDirCode = teaDirCode == null ? null : teaDirCode.trim();
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail == null ? null : teaEmail.trim();
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public void setTeaMajCode(String teaMajCode) {
        this.teaMajCode = teaMajCode == null ? null : teaMajCode.trim();
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public void setTeaNo(String teaNo) {
        this.teaNo = teaNo == null ? null : teaNo.trim();
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword == null ? null : teaPassword.trim();
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone == null ? null : teaPhone.trim();
    }

    public void setTeaSchId(Integer teaSchId) {
        this.teaSchId = teaSchId;
    }

    public void setTeaState(String teaState) {
        this.teaState = teaState == null ? null : teaState.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", teaId=").append(teaId);
        sb.append(", teaNo=").append(teaNo);
        sb.append(", teaName=").append(teaName);
        sb.append(", teaPassword=").append(teaPassword);
        sb.append(", teaPhone=").append(teaPhone);
        sb.append(", teaEmail=").append(teaEmail);
        sb.append(", teaState=").append(teaState);
        sb.append(", teaMajCode=").append(teaMajCode);
        sb.append(", teaDirCode=").append(teaDirCode);
        sb.append(", teaSchId=").append(teaSchId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}