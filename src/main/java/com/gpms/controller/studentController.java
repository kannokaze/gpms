package com.gpms.controller;

import com.gpms.po.*;
import com.gpms.service.InternshipServiceImpl;
import com.gpms.service.informationServiceImpl;
import com.gpms.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class studentController {

	@Autowired
	informationServiceImpl informationServiceImpl;
	
	@Autowired 
	InternshipServiceImpl internshipServiceImpl;
	@RequestMapping(value="api/stu/myInfoInStu", method = RequestMethod.GET)
	public void searchStudentInfoByStuNo(HttpServletRequest request,HttpServletResponse response) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<Student> studentList = null;
		//		if (student == null) {
		studentList = informationServiceImpl.selectMyInfoByStuNo(stuNo);
		//		}else{
		//			studentList = informationServiceImpl.searchStudentInfo(student,page,limit);
		//		}
		ListObject listObject = new ListObject();
		System.err.println(studentList);
		if (studentList.isEmpty() || null == studentList) {
			
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("查询结果为空");
			listObject.setData(studentList);
			//			listObject.setTime();
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(studentList);
		//		listObject.setTime();
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}
	
	@RequestMapping(value="api/stu/setMyInfo")
	public void updateStudentInfoByStuNo(Student student,HttpServletRequest request,HttpServletResponse response) {
//		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		if (informationServiceImpl.updateStudentInfoByStuNo(student) >= 1) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("ok");
			listObject.setData(null);
//			listObject.setTime();
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}
		
	}
	@RequestMapping(value="/api/search/accltter")
	public void sAccltter(HttpServletRequest request,HttpServletResponse response) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();

		List<Accletter> accletters = informationServiceImpl.sAccltter(stuNo);

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(accletters);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/search/accltterByStuNo")
	public void sAccltter(String stuNo,HttpServletRequest request,HttpServletResponse response) {
		
		List<Accletter> accletters = informationServiceImpl.sAccltter(stuNo);

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(accletters);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	@RequestMapping(value="/api/stu/applyJoinCom")
	public void applyJoinCom(HttpServletRequest request,HttpServletResponse response,String comAccount) {
		ListObject listObject = new ListObject();
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		RelationStuCom relationStuCom = new RelationStuCom();
		relationStuCom.setScComAcc(comAccount);
		relationStuCom.setScStuNo(stuNo);
		relationStuCom.setScState("0");
		if (internshipServiceImpl.applyJoinCom(relationStuCom) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("申请成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		} 
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("失败成功!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}

	@RequestMapping(value="/api/stu/modifyMyInfo")
	public void modifyMyInfo(Student student,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		if (!stuNo.equals(student.getStuNo())) {
			listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
			listObject.setMsg("参数错误!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		if (informationServiceImpl.updateStudentInfoByStuNo(student) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("不明原因修改失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	@RequestMapping(value="/api/stu/modifyMyPwd")
	public void modifyMyPwdByOldPwd(HttpServletRequest request,HttpServletResponse response,String oldPwd,String newPwd){
		ListObject listObject = new ListObject();
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		List<Student> studentList = informationServiceImpl.searchStudentInfoByStuNo(stuNo);
		if (studentList.get(0).getStuPassword().equals(oldPwd)) {

			studentList.get(0).setStuPassword(newPwd);
			if (informationServiceImpl.updateStudentInfoByStuNo(studentList.get(0)) > 0) {
				listObject.setCode(StatusCode.CODE_SUCCESS);
				listObject.setMsg("修改成功!");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
				return;
			}
			listObject.setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("密码错误!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}
		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("参数错误!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	@RequestMapping(value="/api/stu/modifyMyPwd2")
	public void modifyMyPwdByCode(HttpServletRequest request,HttpServletResponse response,String code,String newPwd){
		ListObject listObject = new ListObject();
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		List<Student> studentList = informationServiceImpl.searchStudentInfoByStuNo(stuNo);


		studentList.get(0).setStuPassword(newPwd);
		if (informationServiceImpl.updateStudentInfoByStuNo(studentList.get(0)) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("参数错误!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	@RequestMapping(value="/api/stu/verifiyMail")
	public void verifiyMail(HttpServletRequest request,HttpServletResponse response,String code){
		ListObject listObject = new ListObject();
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		List<Student> studentList = informationServiceImpl.searchStudentInfoByStuNo(stuNo);
		if (studentList.size() > 0) {

			new sendEmailUtil(studentList.get(0).getStuEmail(), "验证邮箱", "<h3>欢迎使用gpms系统，你的邮箱验证码为：</h3>"
					+ "<div style='color:#FFB800;font-weight: bold;font-size: 30px;'>"+code+"</div"
					+ "><div style='font-size: 12px;color: #aaa;text-align: right;padding-right: 25px;padding-bottom: 10px;'>系统邮件，请勿回复</div>").send();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("发送成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("参数错误!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/stu/myCompany")
	public void searchMyCompanyByStuNo(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<RelationStuCom> data = informationServiceImpl.searchMyCompanyByStuNo(stuNo);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("保存成功!");
		listObject.setData(data);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;

	}
	@RequestMapping(value="/api/stu/achievement")
	public void searchAchievementByStuNo(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String stuNo = ((Student) session.getAttribute("onlineUser")).getStuNo();

		List<Achievement> achievements = internshipServiceImpl.searchAchievement(stuNo);
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(achievements);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
	}
	@RequestMapping(value="/api/stu/searchJournal")
	public void searchJournalByStuNo(HttpServletRequest request,HttpServletResponse response) {
		
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		
		List<Journal> journalList = informationServiceImpl.searchJournalByStuNo(stuNo);
		
		
		ListObject listObject = new ListObject();
		listObject .setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(journalList);
//		listObject.setTime();
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/stu/saveJournal")
	public void saveJournal(HttpServletRequest request,HttpServletResponse response,String content,String title,String jourId) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		ListObject listObject = new ListObject();
		Journal journal = new Journal();
		journal.setJourId(Integer.valueOf(jourId));
		journal.setJourSavetime(new Date());
		journal.setJourTitle(title);
		journal.setJourContent(content);
		journal.setJourStatus("0");
		journal.setJourStuNo(stuNo);
		if (internshipServiceImpl.saveJournal(journal) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/stu/saveJournalNew")
	public void saveJournalNew(HttpServletRequest request,HttpServletResponse response,String content,String title) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		ListObject listObject = new ListObject();
		Journal journal = new Journal();
//		journal.setJourId(Integer.valueOf(id));
		journal.setJourSavetime(new Date());
		journal.setJourTitle(title);
		journal.setJourContent(content);
		journal.setJourStatus("0");
		journal.setJourStuNo(stuNo);
		if (internshipServiceImpl.saveJournalNew(journal) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/stu/upJournal")
	public void upJournal(HttpServletRequest request,HttpServletResponse response,String content,String title,String jourId) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		ListObject listObject = new ListObject();
		Journal journal = new Journal();
		journal.setJourId(Integer.valueOf(jourId));
		journal.setJourUploadtime(new Date());
		journal.setJourTitle(title);
		journal.setJourContent(content);
		journal.setJourStatus("1");
		journal.setJourStuNo(stuNo);
		if (internshipServiceImpl.upJournal(journal) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/stu/delJournal")
	public void delJournal(HttpServletRequest request,HttpServletResponse response,String jourId) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		ListObject listObject = new ListObject();
		Journal journal = new Journal();
		journal.setJourId(Integer.valueOf(jourId));
//		journal.setJourUploadtime(new Date());
//		journal.setJourTitle(title);
//		journal.setJourContent(content);
		journal.setJourStatus("2");
//		journal.setJourStuNo(stuNo);
		if (internshipServiceImpl.upJournal(journal) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/ypJournalNew")
	public void upJournalNew(HttpServletRequest request,HttpServletResponse response,String content,String title) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();	
		ListObject listObject = new ListObject();
		Journal journal = new Journal();
//		journal.setJourId(Integer.valueOf(id));
		journal.setJourUploadtime(new Date());
		journal.setJourTitle(title);
		journal.setJourContent(content);
		journal.setJourStatus("1");
		journal.setJourStuNo(stuNo);
		if (internshipServiceImpl.upJournalNew(journal) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/createContent")
	public void upContentNew(HttpServletRequest request,HttpServletResponse response,String content,String title) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		ListObject listObject = new ListObject();
		Content content1= new Content();
		content1.setLcName(title);
		content1.setLcContent(content);
		content1.setLcStuNo(stuNo);
		if (internshipServiceImpl.createContent(content1)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/upContent")
	public void upContent(HttpServletRequest request,HttpServletResponse response,String content,String title) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		ListObject listObject = new ListObject();
		Content content1= new Content();
		content1.setLcName(title);
		content1.setLcContent(content);
		content1.setLcStuNo(stuNo);
		if (internshipServiceImpl.changeContent(content1)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/createSelfcomment")
	public void upSelfcommentNew(HttpServletRequest request,HttpServletResponse response,String Selfcomment) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		ListObject listObject = new ListObject();
		Content content1= new Content();
		content1.setLsSelfcomment(Selfcomment);
		content1.setLcStuNo(stuNo);
		if (internshipServiceImpl.createContent(content1)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/upSelfcomment")
	public void upSelfcomment(HttpServletRequest request,HttpServletResponse response,String Selfcomment) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		ListObject listObject = new ListObject();
		Content content1= new Content();
		content1.setLsSelfcomment(Selfcomment);
		content1.setLcStuNo(stuNo);
		if (internshipServiceImpl.changeContent(content1)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/upProject")
	public void upProject(String proStarttime0,String proEndtime0,Project project,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		project.setProStarttime(new Date(Long.valueOf(proStarttime0)));
		project.setProEndtime(new Date(Long.valueOf(proEndtime0)));
		if (internshipServiceImpl.upProject(project) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/createProject")
	public void upProjectNew(String proStarttime0,String proEndtime0,Project project,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		project.setProStarttime(new Date(Long.valueOf(proStarttime0)));
		project.setProEndtime(new Date(Long.valueOf(proEndtime0)));
		if (internshipServiceImpl.insertProject(project) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		
	}
	
	@RequestMapping(value="/api/stu/delProject")
	public void delProject(String proStarttime0,String proEndtime0,Project project,HttpServletRequest request,HttpServletResponse response) {
		
		ListObject listObject = new ListObject();
		project.setProStarttime(new Date(Long.valueOf(proStarttime0)));
		project.setProEndtime(new Date(Long.valueOf(proEndtime0)));
		if (internshipServiceImpl.delProject(project) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/upProgress")
	public void upProgress(String progStarttime0,String progEndtime0,Progress progress,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
//		
		progress.setProgStarttime(new Date(Long.valueOf(progStarttime0)));
		progress.setProgEndtime(new Date(Long.valueOf(progEndtime0)));
		if (internshipServiceImpl.upProgress(progress) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		
	}
	
	@RequestMapping(value="/api/stu/createProgress")
	public void upProgressNew(String progStarttime0,String progEndtime0,Progress progress,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();

		progress.setProgStarttime(new Date(Long.valueOf(progStarttime0)));
		progress.setProgEndtime(new Date(Long.valueOf(progEndtime0)));
		if (internshipServiceImpl.insertProgress(progress) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/stu/delProgress")
	public void delProgress(String progStarttime0,String progEndtime0,Progress progress,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();

		progress.setProgStarttime(new Date(Long.valueOf(progStarttime0)));
		progress.setProgEndtime(new Date(Long.valueOf(progEndtime0)));
		if (internshipServiceImpl.delProgress(progress) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/stu/upTeam")
	public void upTeam(Team team,HttpServletRequest request,HttpServletResponse response) {
		

	}
	
	@RequestMapping(value="/api/stu/createTeam")
	public void upTeamNew(Team team,HttpServletRequest request,HttpServletResponse response) {
		
		ListObject listObject = new ListObject();
		if (internshipServiceImpl.insertTeam(team) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/stu/delTeam")
	public void delTeam(Team team,HttpServletRequest request,HttpServletResponse response) {
		

	}
	@RequestMapping(value="/api/stu/upTeamMember")
	public void upTeamMember(TeamMember teamMember,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		if (internshipServiceImpl.upTeamMember(teamMember) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/stu/createTeamMember")
	public void upTeamMemberNew(TeamMember teamMember,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		if (internshipServiceImpl.insertTeamMember(teamMember) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	@RequestMapping(value="/api/stu/delTeamMember")
	public void delTeamMember(TeamMember teamMember,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		if (internshipServiceImpl.delTeamMember(teamMember) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("保存成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("保存失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	

}
