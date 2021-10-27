package com.gpms.service;

import com.gpms.mapper.*;
import com.gpms.po.Class;
import com.gpms.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class informationServiceImpl {

	@Autowired
	StudentMapper studentMapper;

	@Autowired
	MajorMapper majorMapper;

	@Autowired
	JournalMapper journalMapper;

	@Autowired
	AccletterMapper accletterMapper;

	@Autowired
	RelationStuComMapper relationStuComMapper;

	@Autowired
	AchievementMapper achievementMapper;

	@Autowired
	ClassMapper classMapper;

	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	TeacherMapper teacherMapper;

	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	MajorDirectionMapper majorDirectionMapper;

	public List<Journal> searchJournalByStuNo(String stuNo) {

		return journalMapper.searchJournalByStuNo(stuNo);
	}
	
	public List<Journal> searchJournalByComAcc(String comAcc,String stage) {

		return journalMapper.searchJournalByComAcc(comAcc,stage);
	}

	public List<Major> searchMajor() {
		return majorMapper.selectMajorAndDirection();
	}
	public List<Company> searchCompanyForAdmin(){
		return companyMapper.selectAll();
	}
	
	public List<Company> searchCompanyForAdminByKey(Company company) {
		return companyMapper.selectAllByKey(company);
	}
	
	public List<Teacher> searchTeacherForAdminByKey(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherMapper.selectAllByKey(teacher);
	}

	public List<Company> searchCompanyForStu(String stuNo){
		return relationStuComMapper.selectAllForStudentByStuNo(stuNo);
	}
	
	public List<RelationStuCom> searchCompanyForStu(Company company,String stuNo) {
		return relationStuComMapper.selectCompaniesForStudentByKey(company,stuNo);
	}

	public List<RelationStuCom> searchStudentsForCom(String comAcc) {
		return relationStuComMapper.selectAllForCompany(comAcc);
	}

	public List<RelationStuCom> selectAllForCompanyByStage(String comAcc,
			String stage) {
		return relationStuComMapper.selectAllForCompanyByStage(comAcc,stage);
	}

	public List<Student> searchStudentInfo(int page, int limit) {
		int startIndex = (page-1)*limit;
		return studentMapper.selectAllStudentInfo(startIndex,limit);
	}

	public List<Student> searchStudentInfo(Student student,int page, int limit) {
		int startIndex = (page-1)*limit;
		return studentMapper.selectStudentInfo(student,startIndex,limit);
	}

	public List<Student> searchStudentInfoByStageAndTeaNo(String stage,String teaNO) {
		return studentMapper.selectStudentInfoByStageAndTeaNo(stage,teaNO);
	}
	public List<Student> searchStudentInfoByStageAndComAcc(String stage,
			String comAcc) {
		return relationStuComMapper.selectStudentInfoByStageAndComAcc(stage,comAcc);
	}
	public List<Student> searchStudentInfoByStuNo(String stuNo) {
		return studentMapper.selectStudentByStuNo(stuNo);
	}
	public List<Student> selectMyInfoByStuNo(String stuNo) {
		return studentMapper.selectMyInfoByStuNo(stuNo);
	}
	public List<com.gpms.po.Class> searchClass() {
		return classMapper.SelectClasses();
	}

	public int updateStudentInfoByStuNo(Student student) {
		// TODO Auto-generated method stub
		return studentMapper.updateByStuNo(student);
	}
	
	
	public int updateTeacherInfoByTeaNo(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherMapper.updateByTeaNo(teacher);
	}
	public List<Accletter> sAccltter(String stuNo) {
		return accletterMapper.selectByStuNo(stuNo);
	}


	public List<Teacher> searchTeacherInfoByStuNo(String teaNo) {

		return teacherMapper.selectByTeaNo(teaNo);
	}

	public List<Company> searchCompanyInfoByComAcc(String comAcc) {
		// TODO Auto-generated method stub
		return companyMapper.selectByAccount(comAcc);
	}

	public int updateCompanyInfoBycomAcc(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.updateCompanyInfoByComAcc(company);
	}

	public List<Admin> searchAdminInfoByAdminAcc(String comAcc) {
		// TODO Auto-generated method stub
		return adminMapper.searchAdminInfoByAdminAcc(comAcc);
	}

	public int updateAdminInfoByAdminAcc(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.updateAdminInfoByAdminAcc(admin);
	}

	public int updateClass(Class class1) {
		// TODO Auto-generated method stub
		return classMapper.insertSelective(class1);
	}

	public int modifyClass(Class class1) {
		// TODO Auto-generated method stub
		return classMapper.updateByPrimaryKeySelective(class1);
	}

	public int removeClass(String classNo) {
		// TODO Auto-generated method stub
		return classMapper.deleteByPrimaryKey(classNo);
	}

	public int updateMajor(Major major) {
		return majorMapper.insertSelective(major);
	}

	public int modifyMajor(Major major) {
		// TODO Auto-generated method stub
		return majorMapper.updateByPrimaryKeySelective(major);
	}

	public int removeMajor(String majCode) {
		// TODO Auto-generated method stub
		return majorMapper.deleteByPrimaryKey(majCode);
	}

	public int updateDirection(MajorDirection majorDirection) {
		// TODO Auto-generated method stub
		return majorDirectionMapper.insertSelective(majorDirection);
	}

	public int modifyDirection(MajorDirection majorDirection) {
		// TODO Auto-generated method stub
		return majorDirectionMapper.updateByPrimaryKeySelective(majorDirection);
	}

	public int removeDirection(String dirNo) {
		// TODO Auto-generated method stub
		return majorDirectionMapper.deleteByPrimaryKey(dirNo);
	}

	public List<RelationStuCom> searchMyCompanyByStuNo(String stuNo) {
		// TODO Auto-generated method stub
		return relationStuComMapper.selectMyCompanyByStuNo(stuNo);
	}

	public List<Student> searchStudentInfoByStage(String stage) {
		return studentMapper.selectStudentInfoByStage(stage);
	}




}
