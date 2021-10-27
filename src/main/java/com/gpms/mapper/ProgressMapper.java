package com.gpms.mapper;

import com.gpms.po.Progress;

public interface ProgressMapper {
    int deleteByPrimaryKey(Integer progId);

    int insert(Progress record);

    int insertSelective(Progress record);

    Progress selectByPrimaryKey(Integer progId);

    int updateByPrimaryKeySelective(Progress record);

    int updateByPrimaryKey(Progress record);
}