package com.gpms.mapper;

import com.gpms.po.Accletter;

import java.util.List;

public interface AccletterMapper {
    int deleteByPrimaryKey(Integer lalId);

    int insert(Accletter record);

    int insertSelective(Accletter record);

    Accletter selectByPrimaryKey(Integer lalId);

    int updateByPrimaryKeySelective(Accletter record);

    int updateByPrimaryKey(Accletter record);
    
    // �����µ�ʵϰ���ܺ�
    int insertAccltter(Accletter record);
    
    // ��ѯ
    List<Accletter> selectByStuNo(String stuNo);
}