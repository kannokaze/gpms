package com.gpms.po;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
    private Integer comId;

    private String comAccount;

    private String comName;

    private String comPassword;

    private String comAddress;

    private String comTrade;

    private String comManager;

    private String comPhone;

    private String comEmail;

    private Date comRegistime;

    private String comState;
    
    private String scState;

    private static final long serialVersionUID = 1L;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComAccount() {
        return comAccount;
    }

    public void setComAccount(String comAccount) {
        this.comAccount = comAccount == null ? null : comAccount.trim();
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName == null ? null : comName.trim();
    }

    public String getComPassword() {
        return comPassword;
    }

    public void setComPassword(String comPassword) {
        this.comPassword = comPassword == null ? null : comPassword.trim();
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress == null ? null : comAddress.trim();
    }

    public String getComTrade() {
        return comTrade;
    }

    public void setComTrade(String comTrade) {
        this.comTrade = comTrade == null ? null : comTrade.trim();
    }

    public String getComManager() {
        return comManager;
    }

    public void setComManager(String comManager) {
        this.comManager = comManager == null ? null : comManager.trim();
    }

    public String getComPhone() {
        return comPhone;
    }

    public void setComPhone(String comPhone) {
        this.comPhone = comPhone == null ? null : comPhone.trim();
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail == null ? null : comEmail.trim();
    }

    public Date getComRegistime() {
        return comRegistime;
    }

    public void setComRegistime(Date comRegistime) {
        this.comRegistime = comRegistime;
    }

    public String getComState() {
        return comState;
    }

    public void setComState(String comState) {
        this.comState = comState == null ? null : comState.trim();
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
        sb.append(", comId=").append(comId);
        sb.append(", comAccount=").append(comAccount);
        sb.append(", comName=").append(comName);
        sb.append(", comPassword=").append(comPassword);
        sb.append(", comAddress=").append(comAddress);
        sb.append(", comTrade=").append(comTrade);
        sb.append(", comManager=").append(comManager);
        sb.append(", comPhone=").append(comPhone);
        sb.append(", comEmail=").append(comEmail);
        sb.append(", comRegistime=").append(comRegistime);
        sb.append(", comState=").append(comState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}