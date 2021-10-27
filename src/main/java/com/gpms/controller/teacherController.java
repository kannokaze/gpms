package com.gpms.controller;


import com.gpms.po.Config;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import com.gpms.service.InternshipServiceImpl;
import com.gpms.service.informationServiceImpl;
import com.gpms.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class teacherController {
	@Autowired
	InternshipServiceImpl internshipServiceImpl;
	@Autowired
	informationServiceImpl informationServiceImpl;

	@RequestMapping(value="/api/tea/myInfoInTea", method = RequestMethod.GET)
	public void myInfoInTea(HttpServletRequest request,HttpServletResponse response) {
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
		List<Teacher> myList  = informationServiceImpl.searchTeacherInfoByStuNo(teaNo);

		ListObject listObject = new ListObject();
		if (myList.isEmpty() || null == myList) {
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("��ѯ���Ϊ��");
			listObject.setData(myList);
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(myList);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}

	@RequestMapping(value="/api/tea/modifyMyInfo")
	public void modifyMyInfo(Teacher teacher,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
		if (!teaNo.equals(teacher.getTeaNo())) {
			listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
			listObject.setMsg("��������!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		if (informationServiceImpl.updateTeacherInfoByTeaNo(teacher) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("�޸ĳɹ�!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("����ԭ���޸�ʧ��!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/tea/modifyMyPwd")
	public void modifyMyPwdByOldPwd(HttpServletRequest request,HttpServletResponse response,String oldPwd,String newPwd){
		ListObject listObject = new ListObject();
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
		List<Teacher> teacherList = informationServiceImpl.searchTeacherInfoByStuNo(teaNo);
		if (teacherList.get(0).getTeaPassword().equals(oldPwd)) {

			teacherList.get(0).setTeaPassword(newPwd);
			if (informationServiceImpl.updateTeacherInfoByTeaNo(teacherList.get(0)) > 0) {
				listObject.setCode(StatusCode.CODE_SUCCESS);
				listObject.setMsg("�޸ĳɹ�!");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
				return;
			}
			listObject.setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("�������!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}
		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("��������!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	@RequestMapping(value="/api/tea/modifyMyPwd2")
	public void modifyMyPwdByCode(HttpServletRequest request,HttpServletResponse response,String code,String newPwd){
		ListObject listObject = new ListObject();
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();	
		List<Teacher> teacherList = informationServiceImpl.searchTeacherInfoByStuNo(teaNo);


		teacherList.get(0).setTeaPassword(newPwd);
		if (informationServiceImpl.updateTeacherInfoByTeaNo(teacherList.get(0)) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("�޸ĳɹ�!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("��������!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	
	@RequestMapping(value="api/tea/searchStudentInfoByStage", method = RequestMethod.GET)
	public void searchStudentsInfo(HttpServletRequest request,HttpServletResponse response,String stage) {

		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
		List<Student> studentList = informationServiceImpl.searchStudentInfoByStageAndTeaNo(stage,teaNo);

		ListObject listObject = new ListObject();
		if (studentList.isEmpty() || null == studentList) {
			listObject .setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("��ѯ���Ϊ��");
			listObject.setData(studentList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(studentList);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}	

	@RequestMapping(value="api/tea/setDeadline")
	public void setDeadLine(HttpServletRequest request,HttpServletResponse response,String stage,String lasttime) {

		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();

		ListObject listObject = new ListObject();
		if (!internshipServiceImpl.setDeadline(stage, new Date(Long.valueOf(lasttime)), teaNo)) {
			listObject .setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("�½�ʧ��");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("�½��ɹ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}

	@RequestMapping(value="api/tea/changeDeadline")
	public void changeDeadLine(HttpServletRequest request,HttpServletResponse response,String stage,String lasttime,Integer id) {

		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
		Config config = new Config();
		config.setConfId(id);
		config.setConfStage(stage);
		config.setConfLasttime(new Date(Long.valueOf(lasttime)));
		config.setConfOperator(teaNo);
		ListObject listObject = new ListObject();
		if (!internshipServiceImpl.changeDeadline(config)) {
			listObject .setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("�޸�ʧ��");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("�޸ĳɹ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}
	@RequestMapping(value="/api/tea/verifiyMail")
	public void verifiyMail(HttpServletRequest request,HttpServletResponse response,String code){
		ListObject listObject = new ListObject();
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();	
		List<Teacher> studentList = informationServiceImpl.searchTeacherInfoByStuNo(teaNo);
		if (studentList.size() > 0) {

			new sendEmailUtil(studentList.get(0).getTeaEmail(), "��֤����", "<h3>��ӭʹ��gpmsϵͳ�����������֤��Ϊ��</h3>"
					+ "<div style='color:#FFB800;font-weight: bold;font-size: 30px;'>"+code+"</div"
					+ "><div style='font-size: 12px;color: #aaa;text-align: right;padding-right: 25px;padding-bottom: 10px;'>ϵͳ�ʼ�������ظ�</div>").send();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("���ͳɹ�!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("��������!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	
	@RequestMapping(value="/api/tea/grade")
	public void gradeStu(HttpServletRequest request,HttpServletResponse response,String stuNo,String point,String comment) {
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();	
		ListObject listObject = new ListObject();
		System.out.println(point);
		if (internshipServiceImpl.gradeStudentByTeaNo(teaNo,stuNo,point,comment)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("���ֳɹ�!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("����ʧ��!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}
//	
//	@RequestMapping(value="/api/search/internshipInfoByStuNo")
//	public void searchInternshipByStuNo(String stuNo,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
//		List<InternshipByContent> contentList = internshipServiceImpl.searchInternshipByStuNo(request, stuNo);
//
//		ListObject listObject = new ListObject();
//		listObject.setCode(StatusCode.CODE_SUCCESS);
//		listObject.setMsg("���ʳɹ�!");
//		listObject.setData(contentList);
//		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
//	}
	
}
