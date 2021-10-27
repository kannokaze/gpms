package com.gpms.po.complex;

import com.gpms.po.MajorDirection;

import java.io.Serializable;
import java.util.List;

public class MajorAndDirection implements Serializable {
    private Integer majId;

    private String majCode;

    private String majName;

    private String majSign;
    
    private String dirName;
    
    private String dirSign;
    
    private Integer dirMarId;

    private List<MajorDirection> majorDirectionList;

	private MajorDirection majorDirection;

	private static final long serialVersionUID = 1L;

	public Integer getDirMarId() {
		return dirMarId;
	}

	public String getDirName() {
		return dirName;
	}

	public String getDirSign() {
		return dirSign;
	}

	public String getMajCode() {
        return majCode;
    }
    
    public Integer getMajId() {
        return majId;
    }

	public String getMajName() {
        return majName;
    }

	public MajorDirection getMajorDirection() {
		return majorDirection;
	}

	public List<MajorDirection> getMajorDirectionList() {
		return majorDirectionList;
	}
	
    public String getMajSign() {
        return majSign;
    }

	public void setDirMarId(Integer dirMarId) {
		this.dirMarId = dirMarId;
	}



    public void setDirName(String dirName) {
		this.dirName = dirName;
	}

    public void setDirSign(String dirSign) {
		this.dirSign = dirSign;
	}

    public void setMajCode(String majCode) {
        this.majCode = majCode == null ? null : majCode.trim();
    }

    public void setMajId(Integer majId) {
        this.majId = majId;
    }

    public void setMajName(String majName) {
        this.majName = majName == null ? null : majName.trim();
    }

    public void setMajorDirection(MajorDirection majorDirection) {
		this.majorDirection = majorDirection;
	}

    public void setMajorDirectionList(List<MajorDirection> majorDirectionList) {
		this.majorDirectionList = majorDirectionList;
	}

    public void setMajSign(String majSign) {
        this.majSign = majSign == null ? null : majSign.trim();
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
        sb.append(", majorDirectionList=").append(majorDirectionList);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}