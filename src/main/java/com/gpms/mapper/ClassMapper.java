package com.gpms.mapper;

import com.gpms.po.Class;

import java.util.List;

public interface ClassMapper {
    int deleteByPrimaryKey(String classNo);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
    
    List<Class> SelectAll();
    
    List<Class> SelectClasses();
}