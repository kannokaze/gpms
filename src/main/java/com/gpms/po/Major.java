package com.gpms.po;

import java.io.Serializable;
import java.util.List;

public class Major implements Serializable {
    private Integer majId;

    private String majCode;

    private String majName;

    private String majSign;
    
    private String dirCode;

    private String dirName;

    private String dirSign;

    private String dirMarCode;
    
    private List<MajorDirection> majorDirectionList;

    private static final long serialVersionUID = 1L;

    public Integer getMajId() {
        return majId;
    }

    public void setMajId(Integer majId) {
        this.majId = majId;
    }

    public String getMajCode() {
        return majCode;
    }

    public void setMajCode(String majCode) {
        this.majCode = majCode == null ? null : majCode.trim();
    }

    public String getMajName() {
        return majName;
    }

    public void setMajName(String majName) {
        this.majName = majName == null ? null : majName.trim();
    }

    public String getMajSign() {
        return majSign;
    }

    public void setMajSign(String majSign) {
        this.majSign = majSign == null ? null : majSign.trim();
    }

    public String getDirCode() {
		return dirCode;
	}

	public void setDirCode(String dirCode) {
		this.dirCode = dirCode;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getDirSign() {
		return dirSign;
	}

	public void setDirSign(String dirSign) {
		this.dirSign = dirSign;
	}

	public String getDirMarCode() {
		return dirMarCode;
	}

	public void setDirMarCode(String dirMarCode) {
		this.dirMarCode = dirMarCode;
	}

	public List<MajorDirection> getMajorDirectionList() {
		return majorDirectionList;
	}

	public void setMajorDirectionList(List<MajorDirection> majorDirectionList) {
		this.majorDirectionList = majorDirectionList;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", majId=").append(majId);
        sb.append(", majCode=").append(majCode);
        sb.append(", majName=").append(majName);
        sb.append(", majSign=").append(majSign);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}