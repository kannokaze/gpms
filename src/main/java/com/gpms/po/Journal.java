package com.gpms.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Journal implements Serializable {
    private Integer jourId;

    private String jourTitle;

    private Date jourUploadtime;

    private Date jourLastmodified;

    private Date jourSavetime;

    private String jourStatus;

    private String jourStuNo;

    private String jourContent;
    
    private BigDecimal jourScore;
    
    private String jourComment;
    
	private String stuNo;

    private String stuName;

    private String stuPhone;

    private String stuEmail;

    private Integer stuGender;

    private String stuAddress;

    private String stuState;

    private String stuStage;
    
    public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public Integer getStuGender() {
		return stuGender;
	}

	public void setStuGender(Integer stuGender) {
		this.stuGender = stuGender;
	}

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	public String getStuState() {
		return stuState;
	}

	public void setStuState(String stuState) {
		this.stuState = stuState;
	}

	public String getStuStage() {
		return stuStage;
	}

	public void setStuStage(String stuStage) {
		this.stuStage = stuStage;
	}



    private static final long serialVersionUID = 1L;

    public Integer getJourId() {
        return jourId;
    }

    public void setJourId(Integer jourId) {
        this.jourId = jourId;
    }

    public String getJourTitle() {
        return jourTitle;
    }

    public void setJourTitle(String jourTitle) {
        this.jourTitle = jourTitle == null ? null : jourTitle.trim();
    }

    public Date getJourUploadtime() {
        return jourUploadtime;
    }

    public void setJourUploadtime(Date jourUploadtime) {
        this.jourUploadtime = jourUploadtime;
    }

    public Date getJourLastmodified() {
        return jourLastmodified;
    }

    public void setJourLastmodified(Date jourLastmodified) {
        this.jourLastmodified = jourLastmodified;
    }

    public Date getJourSavetime() {
        return jourSavetime;
    }

    public void setJourSavetime(Date jourSavetime) {
        this.jourSavetime = jourSavetime;
    }

    public String getJourStatus() {
        return jourStatus;
    }

    public void setJourStatus(String jourStatus) {
        this.jourStatus = jourStatus == null ? null : jourStatus.trim();
    }

    public String getJourStuNo() {
        return jourStuNo;
    }

    public void setJourStuNo(String jourStuNo) {
        this.jourStuNo = jourStuNo == null ? null : jourStuNo.trim();
    }

    public String getJourContent() {
        return jourContent;
    }

    public BigDecimal getJourScore() {
		return jourScore;
	}

	public void setJourScore(BigDecimal jourScore) {
		this.jourScore = jourScore;
	}

	public String getJourComment() {
		return jourComment;
	}

	public void setJourComment(String jourComment) {
		this.jourComment = jourComment;
	}

	public void setJourContent(String jourContent) {
        this.jourContent = jourContent == null ? null : jourContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jourId=").append(jourId);
        sb.append(", jourTitle=").append(jourTitle);
        sb.append(", jourUploadtime=").append(jourUploadtime);
        sb.append(", jourLastmodified=").append(jourLastmodified);
        sb.append(", jourSavetime=").append(jourSavetime);
        sb.append(", jourStatus=").append(jourStatus);
        sb.append(", jourStuNo=").append(jourStuNo);
        sb.append(", jourContent=").append(jourContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}