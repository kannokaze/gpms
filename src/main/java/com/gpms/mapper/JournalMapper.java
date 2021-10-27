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
    
    // ͨ��ѧ�Ų�ѯ��־�б�
    List<Journal> searchJournalByStuNo(String stuNo);
    
    int saveJour(Journal record);
    
    int saveJourNew(Journal record);
    
    int upJour(Journal record);
    
    
    
    int upJourNew(Journal record);

    // ��ѯ��˾����ʵϰ����־
	List<Journal> searchJournalByComAcc(String comAcc, String stage);
	
	int gradeJour(Journal record);
	
}