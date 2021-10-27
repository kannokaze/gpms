package com.gpms.mapper;

import com.gpms.po.School;

public interface SchoolMapper {
    int deleteByPrimaryKey(Integer schId);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer schId);

    int updateByPrimaryKey(School record);

    int updateByPrimaryKeySelective(School record);
}