package com.gpms.mapper;

import com.gpms.po.Config;

import java.util.Date;
import java.util.List;

public interface ConfigMapper {
	int deleteByPrimaryKey(Integer confId);

	int insert(Config record);

	int insertSelective(Config record);

	Config selectByPrimaryKey(Integer confId);

	int updateByPrimaryKeySelective(Config record);

	int updateByPrimaryKey(Config record);

	//��������ύʵϰ��������
	int setDeadline(String stage,Date date,String teaNo);
	// ���������������
	int setDeadlineOnComment(String stage,Date data,String teaNo);


	int changeDeadline(Config record);
	int changeDeadlineOnComment(Config record);
	// ͨ��ѧ�Ż�ȡ���ʵϰ��������
	List<Config> getDeadline(String stuNo);

	// ͨ��ָ����ʦ��/��ҵ�Ż�ȡ�����������
	List<Config> getDeadlineOnComment();
	List<Config> getDeadlineOnCommentByStage(String stage);
	List<Config> getDeadlineOnCommentByTeaNo(String teaNo);
	List<Config> getDeadlineOnCommentByComAcc(String comAcc);

	// ͨ��ָ����ʦ�Ż�ȡ�������
	List<Config> getDeadlineByTeaNo(String teaNo);



//	List<Config> getDeadlineOnComment();
}