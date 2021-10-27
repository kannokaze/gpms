package com.gpms.mapper;

import com.gpms.po.Company;
import com.gpms.po.RelationStuCom;
import com.gpms.po.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationStuComMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(RelationStuCom record);

    int insertSelective(RelationStuCom record);

    RelationStuCom selectByPrimaryKey(Integer scId);

    int updateByPrimaryKey(RelationStuCom record);

    int updateByPrimaryKeySelective(RelationStuCom record);
    
    List<RelationStuCom> selectMyCompanyByStuNo(String stuNo);
    
    // 无条件查询
    List<Company> selectAllForStudentByStuNo(String stuNo);
    
    // 条件查询 
    List<RelationStuCom> selectAllForCompany(String comAcc);
    
    List<RelationStuCom> selectAllForCompanyByStage(String comAcc,String stage);

	int updateByComAccAndStuNo(RelationStuCom record);

	List<Student> selectStudentInfoByStageAndComAcc(String stage, String comAcc);

	List<RelationStuCom> selectCompaniesForStudentByKey(@Param("company")Company company, @Param("stuNo")String stuNo);
}