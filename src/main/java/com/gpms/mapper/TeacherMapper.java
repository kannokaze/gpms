package com.gpms.mapper;

import com.gpms.po.Teacher;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer teaId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer teaId);

    int updateByPrimaryKey(Teacher record);

    int updateByPrimaryKeySelective(Teacher record);

	Teacher selectByAccountAndPwd(String account, String pwd);

	List<Teacher> selectByTeaNo(String teaNo);
	
	List<Teacher> selectAllByKey(Teacher teacher);

	int updateByTeaNo(Teacher teacher);
}