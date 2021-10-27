package com.gpms.mapper.complex;

import com.gpms.po.complex.InternshipByContent;

import java.util.List;

public interface InternshipByContentMapper {
	
	// ͨ����ҵ�Ų�ѯʵϰ��Ϣ
	List<InternshipByContent> selectInternshipByComAccount(String comAccount);
	
	// ͨ��ѧ�Ų�ѯʵϰ��Ϣ
	List<InternshipByContent> selectInternshipByStuNo(String stuNo);
	
	// ͨ��ָ����ʦ�Ų�ѯʵϰ��Ϣ
	List<InternshipByContent> selectInternshipByTeaNo(String teaNo);
}
