package com.gpms.mapper;

import com.gpms.po.Major;

import java.util.List;

public interface MajorMapper {
    int deleteByPrimaryKey(String majCode);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majId);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
    
    // ��ѯ����רҵ�ͷ���
    List<Major> selectMajorAndDirection();
}