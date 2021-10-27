package com.gpms.controller;

import com.gpms.mapper.CompanyMapper;
import com.gpms.po.Company;
import com.gpms.po.Journal;
import com.gpms.po.RelationStuCom;
import com.gpms.po.Student;
import com.gpms.service.InternshipServiceImpl;
import com.gpms.service.informationServiceImpl;
import com.gpms.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class companyController {
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	InternshipServiceImpl internshipServiceImpl;
	@Autowired
	informationServiceImpl informationServiceImpl;
	
	@RequestMapping(value="/api/com/myInfoInCom", method = RequestMethod.GET)
	public void myInfoInCom(HttpServletRequest request,HttpServletResponse response) {
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		List<Company> myList  = informationServiceImpl.searchCompanyInfoByComAcc(comAcc);

		ListObject listObject = new ListObject();
		if (myList.isEmpty() || null == myList) {
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("查询结果为空");
			listObject.setData(myList);
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(myList);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}

	@RequestMapping(value="/api/com/modifyMyInfo")
	public void modifyMyInfo(Company company,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		if (!comAcc.equals(company.getComAccount())) {
			listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
			listObject.setMsg("参数错误!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		if (informationServiceImpl.updateCompanyInfoBycomAcc(company) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("不明原因修改失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/com/modifyMyPwd")
	public void modifyMyPwdByOldPwd(HttpServletRequest request,HttpServletResponse response,String oldPwd,String newPwd){
		ListObject listObject = new ListObject();
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		List<Company> companyList = informationServiceImpl.searchCompanyInfoByComAcc(comAcc);
		if (companyList.get(0).getComPassword().equals(oldPwd)) {

			companyList.get(0).setComPassword(newPwd);
			if (informationServiceImpl.updateCompanyInfoBycomAcc(companyList.get(0)) > 0) {
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

	@RequestMapping(value="/api/com/modifyMyPwd2")
	public void modifyMyPwdByCode(HttpServletRequest request,HttpServletResponse response,String code,String newPwd){
		ListObject listObject = new ListObject();
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		List<Company> companyList = informationServiceImpl.searchCompanyInfoByComAcc(comAcc);


		companyList.get(0).setComPassword(newPwd);
		if (informationServiceImpl.updateCompanyInfoBycomAcc(companyList.get(0)) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("参数错误!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/com/verifiyMail")
	public void verifiyMail(HttpServletRequest request,HttpServletResponse response,String code){
		ListObject listObject = new ListObject();
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		List<Company> studentList = informationServiceImpl.searchCompanyInfoByComAcc(comAcc);
		if (studentList.size() > 0) {

			new sendEmailUtil(studentList.get(0).getComEmail(), "验证邮箱", "<h3>欢迎使用gpms系统，你的邮箱验证码为：</h3>"
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
	
	@RequestMapping(value="/api/com/applyJoinCom")
	public void applyJoinCom(HttpServletRequest request,HttpServletResponse response,String stuNo) {
		ListObject listObject = new ListObject();
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		RelationStuCom relationStuCom = new RelationStuCom();
		relationStuCom.setScComAcc(comAcc);
		relationStuCom.setScStuNo(stuNo);
		relationStuCom.setScState("1");
		if (internshipServiceImpl.joinCom(relationStuCom) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("同意成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		} 
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("同意失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/com/refuseJoinCom")
	public void refuseJoinCom(HttpServletRequest request,HttpServletResponse response,String stuNo) {
		ListObject listObject = new ListObject();
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		RelationStuCom relationStuCom = new RelationStuCom();
		relationStuCom.setScComAcc(comAcc);
		relationStuCom.setScStuNo(stuNo);
		relationStuCom.setScState("2");
		if (internshipServiceImpl.joinCom(relationStuCom) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("同意成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		} 
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("同意失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/com/searchStudentForCom")
	public void seachCompanyForCom(HttpServletRequest request,HttpServletResponse response) {
		List<RelationStuCom> companies = null;
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		companies = informationServiceImpl.searchStudentsForCom(comAcc);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/com/searchStudentForComByKey")
	public void seachCompanyForComByKey(String stage,HttpServletRequest request,HttpServletResponse response) {
		List<RelationStuCom> companies = null;
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		companies = informationServiceImpl.selectAllForCompanyByStage(comAcc,stage);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	
	@RequestMapping(value="api/com/searchStudentInfoByStage", method = RequestMethod.GET)
	public void searchStudentsInfo(HttpServletRequest request,HttpServletResponse response,String stage) {

		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		List<Student> studentList = informationServiceImpl.searchStudentInfoByStageAndComAcc(stage,comAcc);

		ListObject listObject = new ListObject();
		if (studentList.isEmpty() || null == studentList) {
			listObject .setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("查询结果为空");
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
	
	@RequestMapping(value="/api/com/grade")
	public void gradeStu(HttpServletRequest request,HttpServletResponse response,String stuNo,String point,String comment) {
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		ListObject listObject = new ListObject();
		
		if (internshipServiceImpl.gradeStudentByComAcc(comAcc,stuNo,point,comment)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("评分成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("评分失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}
	
	@RequestMapping(value="/api/com/searchJournal")
	public void searchJournalByComAcc(String stage,HttpServletRequest request,HttpServletResponse response) {
		
		String comAcc =  ((Company) request.getSession().getAttribute("onlineUser")).getComAccount();
		
		List<Journal> journalList = informationServiceImpl.searchJournalByComAcc(comAcc,stage);
		
		ListObject listObject = new ListObject();
		listObject .setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("ok");
		listObject.setData(journalList);
//		listObject.setTime();
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/com/gradeJournal")
	public void gradeJournalByStuNo(String jourId,String jourScore,String jourComment,HttpServletRequest request,HttpServletResponse response) {
		
		BigDecimal jourScore1=new BigDecimal(jourScore);
		Journal journal = new Journal();
		journal.setJourScore(jourScore1);
		journal.setJourComment(jourComment);
		journal.setJourId(Integer.valueOf(jourId));
		
		if (internshipServiceImpl.gradeJournalByStuNo(journal) > 0) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("ok");
//			listObject.setData(journalList);
//			listObject.setTime();
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		
		ListObject listObject = new ListObject();
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("error");
//		listObject.setData(journalList);
//		listObject.setTime();
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
}
