package com.gpms.controller;

import com.gpms.po.*;
import com.gpms.po.complex.InternshipByContent;
import com.gpms.service.InternshipServiceImpl;
import com.gpms.service.informationServiceImpl;
import com.gpms.util.JsonUtils;
import com.gpms.util.ListObject;
import com.gpms.util.ResponseUtils;
import com.gpms.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class internshipController {
	
	@Autowired
	InternshipServiceImpl internshipServiceImpl;
	
	@Autowired
	informationServiceImpl informationServiceImpl;
	
	@RequestMapping(value="/api/config/deadline")
	public void getDeadline(HttpServletRequest request,HttpServletResponse response,String key) {
		String stuNo = ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<Config> deadlineConfigs = internshipServiceImpl.getDeadline(stuNo);
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(deadlineConfigs);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	
	@RequestMapping(value="/api/config/deadlineComment")
	public void getCommentDeadline(HttpServletRequest request,HttpServletResponse response,String stage) {
//		String stuNo = ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<Config> deadlineConfigs = internshipServiceImpl.getDeadlineOnComment();
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(deadlineConfigs);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/config/deadlineCommentByNow")
	public void getCommentDeadlineByStage(HttpServletRequest request,HttpServletResponse response,String stage) {
//		String stuNo = ((Student) request.getSession().getAttribute("onlineUser")).getStuNo();
		List<Config> deadlineConfigs = internshipServiceImpl.getDeadlineOnComment(stage);
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(deadlineConfigs);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/search/internshipInfoByKey")
	public void searchInternshipByKey(HttpServletRequest request,HttpServletResponse response,String key,String type) {
		List<InternshipByContent> contentList = null;
		if (type != null) {
			if (type.equals("tea")) {
				contentList = internshipServiceImpl.selectInternshipByTeaNo(request, key);
			}else {
				contentList = internshipServiceImpl.selectInternshipByComAcc(request, key);
			}
		}
		
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(contentList);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/search/internshipInfo")
	public void searchInternshipByStuNo(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String stuNo = ((Student) session.getAttribute("onlineUser")).getStuNo();

		List<InternshipByContent> contentList = internshipServiceImpl.searchInternshipByStuNo(request, stuNo);

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(contentList);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
	}
	

	@RequestMapping(value="/api/search/achievement")
	public void searchAchievementByStuNo(String stuNo,HttpServletRequest request,HttpServletResponse response,HttpSession session) {

		List<Achievement> achievements = internshipServiceImpl.searchAchievement(stuNo);
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(achievements);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
	}
	
	@RequestMapping(value="/api/config/getConfig")
	public void searchConfig(HttpServletRequest request,HttpServletResponse response) {
		String teaNo =  ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
		
		List<Config> configs = internshipServiceImpl.getDeadlineByTeaNo(teaNo);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(configs);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
	}
	
	@RequestMapping(value="/api/admin/getConfig")
	public void searchConfigOnComment(HttpServletRequest request,HttpServletResponse response,String stage) {
		
		List<Config> configs = internshipServiceImpl.getDeadlineOnComment();
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(configs);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
	}
	
	@RequestMapping(value="/api/search/internshipInfoByStuNo")
	public void searchInternshipByStuNo(String stuNo,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		

		List<InternshipByContent> contentList = internshipServiceImpl.searchInternshipByStuNo(request, stuNo);

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功!");
		listObject.setData(contentList);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	
	}
	
	@RequestMapping(value="/api/search/searchCompanyByStuNo")
	public void searchMyCompanyByStuNo(String stuNo,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		
		List<RelationStuCom> data = informationServiceImpl.searchMyCompanyByStuNo(stuNo);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("保存成功!");
		listObject.setData(data);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;

	}
}
