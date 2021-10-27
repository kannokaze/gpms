package com.gpms.mapper;

import com.gpms.po.Content;

public interface ContentMapper {
    int deleteByPrimaryKey(Integer lcId);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Integer lcId);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    // 
	int updateByPrimaryByStuNo(Content content1);
}