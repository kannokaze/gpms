package com.gpms.mapper;

import com.gpms.po.MajorDirection;

public interface MajorDirectionMapper {
    int deleteByPrimaryKey(String dirCode);

    int insert(MajorDirection record);

    int insertSelective(MajorDirection record);

    MajorDirection selectByPrimaryKey(Integer dirId);

    int updateByPrimaryKeySelective(MajorDirection record);

    int updateByPrimaryKey(MajorDirection record);
}