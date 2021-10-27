package com.gpms.mapper;

import com.gpms.po.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    // 查询所有学生信息
    List<Student> selectAllStudentInfo(@Param("startIndex") int startIndex,@Param("limit") int limit);

    // 生成表格
    List<Student> selectAllStudentInfoForExcel();

    Student selectByNumAndPwd(@Param("stuNo") String stuNo,@Param("pwd") String pwd);
    
    Student selectByPrimaryKey(Integer stuId);

    // 按学号查询学生信息
    List<Student> selectStudentByStuNo(String stuNo);
    
    List<Student> selectMyInfoByStuNo(String stuNo);
    
    List<Student> selectStudentByStage(String stage);
    
    // 按条件查询学生信息
    List<Student> selectStudentInfo(@Param("student") Student student,@Param("startIndex") int startIndex,@Param("limit") int limit);
    
    List<Student> selectStudentInfo1(Student student);
    
    int updateByPrimaryKey(Student record);
    
    int updateByPrimaryKeySelective(Student record);
    
    // 按学号修改学生信息
    int updateByStuNo(Student student);
    
    // 按届期和教师号
	List<Student> selectStudentInfoByStageAndTeaNo(String stage, String teaNo);

	// 
	List<Student> selectStudentInfoByStage(String stage);
}