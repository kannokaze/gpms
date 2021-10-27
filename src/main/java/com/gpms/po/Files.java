package com.gpms.po;

import java.io.Serializable;
import java.util.Date;

public class Files implements Serializable {
    private Integer fileId;

    private String fileTitle;

    private String fileOriginalName;

    private String fileNewName;

    private String filePath;

    private String fileRelativePath;

    private String fileSize;

    private String fileType;

    private String fileSign;

    private Boolean fileDelFlag;

    private Date fileUpdateTime;

    private String fileUpper;

    private static final long serialVersionUID = 1L;

    public Boolean getFileDelFlag() {
        return fileDelFlag;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFileNewName() {
        return fileNewName;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileRelativePath() {
        return fileRelativePath;
    }

    public String getFileSign() {
        return fileSign;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public String getFileType() {
        return fileType;
    }

    public Date getFileUpdateTime() {
        return fileUpdateTime;
    }

    public String getFileUpper() {
        return fileUpper;
    }

    public void setFileDelFlag(Boolean fileDelFlag) {
        this.fileDelFlag = fileDelFlag;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setFileNewName(String fileNewName) {
        this.fileNewName = fileNewName == null ? null : fileNewName.trim();
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName == null ? null : fileOriginalName.trim();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath == null ? null : fileRelativePath.trim();
    }

    public void setFileSign(String fileSign) {
        this.fileSign = fileSign == null ? null : fileSign.trim();
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle == null ? null : fileTitle.trim();
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public void setFileUpdateTime(Date fileUpdateTime) {
        this.fileUpdateTime = fileUpdateTime;
    }

    public void setFileUpper(String fileUpper) {
        this.fileUpper = fileUpper == null ? null : fileUpper.trim();
    }


	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileTitle=").append(fileTitle);
        sb.append(", fileOriginalName=").append(fileOriginalName);
        sb.append(", fileNewName=").append(fileNewName);
        sb.append(", filePath=").append(filePath);
        sb.append(", fileRelativePath=").append(fileRelativePath);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileType=").append(fileType);
        sb.append(", fileSign=").append(fileSign);
        sb.append(", fileDelFlag=").append(fileDelFlag);
        sb.append(", fileUpdateTime=").append(fileUpdateTime);
        sb.append(", fileUpper=").append(fileUpper);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}