package com.gpms.mapper;

import com.gpms.po.Achievement;

import java.math.BigDecimal;
import java.util.List;

public interface AchievementMapper {
    int deleteByPrimaryKey(Integer achId);

    int insert(Achievement record);

    int insertSelective(Achievement record);

    Achievement selectByPrimaryKey(Integer achId);

    int updateByPrimaryKeySelective(Achievement record);

    int updateByPrimaryKey(Achievement record);
    
    List<Achievement> selectByStuNo(String stuNo);

	int gradeStudentByTeaNo(String teaNo,String stuNo, BigDecimal point, String comment);
	
	int gradeStudentByComAcc(String teaNo,String stuNo ,BigDecimal point, String comment);
}