package com.gpms.controller;

import com.gpms.po.*;
import com.gpms.service.informationServiceImpl;
import com.gpms.util.JsonUtils;
import com.gpms.util.ListObject;
import com.gpms.util.ResponseUtils;
import com.gpms.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ResponseBody
public class informationController {
	
	ListObject listObject;
	
	@Autowired
	informationServiceImpl informationServiceImpl;
	
	public informationController() {
		listObject = new ListObject();
	}
	
	@RequestMapping(value="api/deleter/deleterMajor")
	public void deleterMajor() {
		// TODO Auto-generated method stub

	}
	
	@RequestMapping(value="api/create/createMajor")
	public void insertMajor() {
		// TODO Auto-generated method stub

	}
	
	@RequestMapping(value="api/search/searchMajor")
	public void seachMajor(HttpServletResponse response) {
		List<Major> MD = null;
		MD = informationServiceImpl.searchMajor();
//		Map<String, Object> map = new HashMap<String,Object>();
//		map.put("majId", 1);
//		map.put("majCode", "10");
//		map.put("majName", "专业方向");
//		map.put("majSign", "");
//		map.put("dirCode",  "");
//		map.put("dirName", "");
//		map.put("dirSign", "");
//		map.put("dirMarCode", -1);
//		Major major = new Major();
//		major.setMajName("专业方向");
////		major.setMajCode("-1");
//		major.setDirMarCode("-1");
//		MD.add((Major) major);
//		
		
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(MD);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		System.err.println(MD);
		response.setContentType("application/json");
	}
	
	@RequestMapping(value="api/search/searchCompanyForStu")
	public void seachCompanyForStu(HttpServletRequest request,HttpServletResponse response) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<Company> companies = null;
		companies = informationServiceImpl.searchCompanyForStu(stuNo);

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="api/search/searchCompanyForStuByKey")
	public void seachCompanyForStuByKey(Company company,HttpServletRequest request,HttpServletResponse response) {
		String stuNo = (String) ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<RelationStuCom> companies = null;
		companies = informationServiceImpl.searchCompanyForStu(company,stuNo);

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	
	@RequestMapping(value="/api/search/searchJournal")
	public void searchJournalByStuNo(String stuNo,HttpServletRequest request,HttpServletResponse response) {
		
		List<Journal> journalList = informationServiceImpl.searchJournalByStuNo(stuNo);
		
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(journalList);
//		listObject.setTime();
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	/**
	 * 查询学生表信息
	 * @param student
	 * @param response
	 */
	
	@RequestMapping(value="api/search/studentList", method = RequestMethod.GET)
	public void searchStudentsInfo(Student student,int page,int limit,HttpServletResponse response) {
		List<Student> studentList = null;
		System.out.println(student.toString());
//		if (student == null) {
//			studentList = informationServiceImpl.searchStudentInfo(page,limit);
//		}else{
			studentList = informationServiceImpl.searchStudentInfo(student,page,limit);
//		}
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
	
	@RequestMapping(value="api/update/updateMajor")
	public void updateMajor() {
		// TODO Auto-generated method stub

	}
}
