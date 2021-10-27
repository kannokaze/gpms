package com.gpms.mapper;

import com.gpms.po.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    // ��ѯ����ѧ����Ϣ
    List<Student> selectAllStudentInfo(@Param("startIndex") int startIndex,@Param("limit") int limit);

    // ���ɱ��
    List<Student> selectAllStudentInfoForExcel();

    Student selectByNumAndPwd(@Param("stuNo") String stuNo,@Param("pwd") String pwd);
    
    Student selectByPrimaryKey(Integer stuId);

    // ��ѧ�Ų�ѯѧ����Ϣ
    List<Student> selectStudentByStuNo(String stuNo);
    
    List<Student> selectMyInfoByStuNo(String stuNo);
    
    List<Student> selectStudentByStage(String stage);
    
    // ��������ѯѧ����Ϣ
    List<Student> selectStudentInfo(@Param("student") Student student,@Param("startIndex") int startIndex,@Param("limit") int limit);
    
    List<Student> selectStudentInfo1(Student student);
    
    int updateByPrimaryKey(Student record);
    
    int updateByPrimaryKeySelective(Student record);
    
    // ��ѧ���޸�ѧ����Ϣ
    int updateByStuNo(Student student);
    
    // �����ںͽ�ʦ��
	List<Student> selectStudentInfoByStageAndTeaNo(String stage, String teaNo);

	// 
	List<Student> selectStudentInfoByStage(String stage);
}