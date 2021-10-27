package com.gpms.mapper;

import com.gpms.po.Journal;

import java.util.List;

public interface JournalMapper {
    int deleteByPrimaryKey(Integer jourId);

    int insert(Journal record);

    int insertSelective(Journal record);

    Journal selectByPrimaryKey(Integer jourId);

    int updateByPrimaryKeySelective(Journal record);

    int updateByPrimaryKeyWithBLOBs(Journal record);

    int updateByPrimaryKey(Journal record);
    
    // 通过学号查询日志列表
    List<Journal> searchJournalByStuNo(String stuNo);
    
    int saveJour(Journal record);
    
    int saveJourNew(Journal record);
    
    int upJour(Journal record);
    
    
    
    int upJourNew(Journal record);

    // 查询公司所属实习生日志
	List<Journal> searchJournalByComAcc(String comAcc, String stage);
	
	int gradeJour(Journal record);
	
}