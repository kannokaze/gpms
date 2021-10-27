package com.gpms.mapper;

import com.gpms.po.Files;

import java.util.List;

public interface FilesMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer fileId);
    
    // 查询
    List<Files> selectByUpper(String fileUpper);

    int updateByPrimaryKey(Files record);
    
    int updateByPrimaryKeySelective(Files record);

	// 教师上传文件
    int uploadFilesByTeaNo(Files record);
    
    // 删除文件
	int changeFileFlag(String fileId, String teaNo);
}