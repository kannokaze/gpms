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

	//设置最后提交实习资料期限
	int setDeadline(String stage,Date date,String teaNo);
	// 设置最后评价期限
	int setDeadlineOnComment(String stage,Date data,String teaNo);


	int changeDeadline(Config record);
	int changeDeadlineOnComment(Config record);
	// 通过学号获取最后实习资料期限
	List<Config> getDeadline(String stuNo);

	// 通过指导老师号/企业号获取最后评价期限
	List<Config> getDeadlineOnComment();
	List<Config> getDeadlineOnCommentByStage(String stage);
	List<Config> getDeadlineOnCommentByTeaNo(String teaNo);
	List<Config> getDeadlineOnCommentByComAcc(String comAcc);

	// 通过指导老师号获取最后期限
	List<Config> getDeadlineByTeaNo(String teaNo);



//	List<Config> getDeadlineOnComment();
}