package com.gpms.po;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer adminId;

    private String adminAccount;

    private String adminName;

    private String adminPassword;

    private String adminRelaction;

    private String adminPosition;

    private String adminType;

    private static final long serialVersionUID = 1L;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount == null ? null : adminAccount.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminRelaction() {
        return adminRelaction;
    }

    public void setAdminRelaction(String adminRelaction) {
        this.adminRelaction = adminRelaction == null ? null : adminRelaction.trim();
    }

    public String getAdminPosition() {
        return adminPosition;
    }

    public void setAdminPosition(String adminPosition) {
        this.adminPosition = adminPosition == null ? null : adminPosition.trim();
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType == null ? null : adminType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminId=").append(adminId);
        sb.append(", adminAccount=").append(adminAccount);
        sb.append(", adminName=").append(adminName);
        sb.append(", adminPassword=").append(adminPassword);
        sb.append(", adminRelaction=").append(adminRelaction);
        sb.append(", adminPosition=").append(adminPosition);
        sb.append(", adminType=").append(adminType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}