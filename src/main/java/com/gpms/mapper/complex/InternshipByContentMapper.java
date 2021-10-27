package com.gpms.mapper.complex;

import com.gpms.po.complex.InternshipByContent;

import java.util.List;

public interface InternshipByContentMapper {
	
	// 通过企业号查询实习信息
	List<InternshipByContent> selectInternshipByComAccount(String comAccount);
	
	// 通过学号查询实习信息
	List<InternshipByContent> selectInternshipByStuNo(String stuNo);
	
	// 通过指导老师号查询实习信息
	List<InternshipByContent> selectInternshipByTeaNo(String teaNo);
}
