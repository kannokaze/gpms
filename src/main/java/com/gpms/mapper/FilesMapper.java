package com.gpms.mapper;

import com.gpms.po.Files;

import java.util.List;

public interface FilesMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer fileId);
    
    // ��ѯ
    List<Files> selectByUpper(String fileUpper);

    int updateByPrimaryKey(Files record);
    
    int updateByPrimaryKeySelective(Files record);

	// ��ʦ�ϴ��ļ�
    int uploadFilesByTeaNo(Files record);
    
    // ɾ���ļ�
	int changeFileFlag(String fileId, String teaNo);
}