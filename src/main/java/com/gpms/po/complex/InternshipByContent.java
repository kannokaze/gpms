package com.gpms.po.complex;

import com.gpms.po.Project;

import java.io.Serializable;
import java.util.List;

public class InternshipByContent implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private Integer lcId;

    private String lcName;

    private String lcContent;
    
    private String lcSelfcomment;

    private String lcStuNo;

    private List<Project> projectList;
    
//    private Project project;
//    
//    private Progress progress;

    public InternshipByContent() {
	}
     
	public String getLcContent() {
		return lcContent;
	}

	public Integer getLcId() {
		return lcId;
	}

	public String getLcName() {
		return lcName;
	}


	public List<Project> getProjectList() {
		return projectList;
	}

	public void setLcContent(String lcContent) {
		this.lcContent = lcContent;
	}

	public void setLcId(Integer lcId) {
		this.lcId = lcId;
	}

	public void setLcName(String lcName) {
		this.lcName = lcName;
	}


	public String getLcStuNo() {
		return lcStuNo;
	}

	public String getLcSelfcomment() {
		return lcSelfcomment;
	}

	public void setLcSelfcomment(String lcSelfcomment) {
		this.lcSelfcomment = lcSelfcomment;
	}

	public void setLcStuNo(String lcStuNo) {
		this.lcStuNo = lcStuNo;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
//
//	public Progress getProgress() {
//		return progress;
//	}
//
//	public void setProgress(Progress progress) {
//		this.progress = progress;
//	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
