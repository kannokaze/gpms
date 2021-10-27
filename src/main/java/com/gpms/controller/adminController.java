package com.gpms.controller;

import com.gpms.po.*;
import com.gpms.service.InternshipServiceImpl;
import com.gpms.service.informationServiceImpl;
import com.gpms.service.securityServiceImpl;
import com.gpms.util.JsonUtils;
import com.gpms.util.ListObject;
import com.gpms.util.ResponseUtils;
import com.gpms.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class adminController {
	@Autowired
	informationServiceImpl informationServiceImpl;
	
	@Autowired
	securityServiceImpl securityServiceImpl;
	
	@Autowired
	InternshipServiceImpl internshipServiceImpl;
	
	@RequestMapping(value="/api/admin/myInfoInAdmin", method = RequestMethod.GET)
	public void myInfoInTea(HttpServletRequest request,HttpServletResponse response) {
		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
		List<Admin> myList  = informationServiceImpl.searchAdminInfoByAdminAcc(adminAcc);

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

	@RequestMapping(value="/api/admin/modifyMyInfo")
	public void modifyMyInfo(Admin Admin,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
		if (!adminAcc.equals(Admin.getAdminAccount())) {
			listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
			listObject.setMsg("参数错误!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		if (informationServiceImpl.updateAdminInfoByAdminAcc(Admin) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("不明原因修改失败!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/admin/modifyMyPwd")
	public void modifyMyPwdByOldPwd(HttpServletRequest request,HttpServletResponse response,String oldPwd,String newPwd){
		ListObject listObject = new ListObject();
		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
		List<Admin> AdminList = informationServiceImpl.searchAdminInfoByAdminAcc(adminAcc);
		if (AdminList.get(0).getAdminPassword().equals(oldPwd)) {

			AdminList.get(0).setAdminPassword(newPwd);
			if (informationServiceImpl.updateAdminInfoByAdminAcc(AdminList.get(0)) > 0) {
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

	@RequestMapping(value="/api/admin/modifyMyPwd2")
	public void modifyMyPwdByCode(HttpServletRequest request,HttpServletResponse response,String code,String newPwd){
		ListObject listObject = new ListObject();
		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
		List<Admin> AdminList = informationServiceImpl.searchAdminInfoByAdminAcc(adminAcc);
		AdminList.get(0).setAdminPassword(newPwd);
		if (informationServiceImpl.updateAdminInfoByAdminAcc(AdminList.get(0)) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
		listObject.setMsg("参数错误!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/admin/searchClassInfo")
	public void searchClass(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		
		List<com.gpms.po.Class> classes = informationServiceImpl.searchClass();
		
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		listObject.setData(classes);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="api/admin/searchStudentInfoByStage", method = RequestMethod.GET)
	public void searchStudentsInfo(HttpServletRequest request,HttpServletResponse response,String stage) {

		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
		List<Student> studentList = informationServiceImpl.searchStudentInfoByStage(stage);

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
	
	@RequestMapping(value="/api/admin/agreeCompany")
	public void agreeCompany(HttpServletRequest request,HttpServletResponse response,String account) {
		ListObject listObject = new ListObject();
		
		if (securityServiceImpl.agreeCompany(account)) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("同意成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("同意失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/admin/searchCompany")
	public void searchCompany(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		List<Company> companies = informationServiceImpl.searchCompanyForAdmin();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/admin/searchCompanyByKey")
	public void searchCompanyByKey(Company company,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		List<Company> companies = informationServiceImpl.searchCompanyForAdminByKey(company);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/admin/searchTeacherByKey")
	public void searchTeacherByKey(Teacher teacher,HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		List<Teacher> companies = informationServiceImpl.searchTeacherForAdminByKey(teacher);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		listObject.setData(companies);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="api/admin/setDeadline")
	public void setDeadLine(HttpServletRequest request,HttpServletResponse response,String stage,String lasttime) {

		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
	
		ListObject listObject = new ListObject();
		if (!internshipServiceImpl.setDeadlineOnComment(stage, new Date(Long.valueOf(lasttime)), adminAcc)) {
			listObject .setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("新建失败");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("新建成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}
	
	@RequestMapping(value="/api/admin/changeDeadline")
	public void changeDeadLine(HttpServletRequest request,HttpServletResponse response,String stage,String lasttime,Integer id) {

		String adminAcc =  ((Admin) request.getSession().getAttribute("onlineUser")).getAdminAccount();
		Config config = new Config();
		config.setConfId(id);
		config.setConfStage(stage);
		config.setConfLasttime(new Date(Long.valueOf(lasttime)));
		config.setConfOperator(adminAcc);
		ListObject listObject = new ListObject();
		if (!internshipServiceImpl.changeDeadlineOnComment(config)) {
			listObject .setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("修改失败");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("修改成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		return;
	}
	
	@RequestMapping(value="/api/admin/updateClass")
	public void updateClass(HttpServletRequest request,HttpServletResponse response,String classNo,String className,String majorCode) {
		ListObject listObject = new ListObject();
		com.gpms.po.Class class1 = new com.gpms.po.Class(); 
//		class1.setClassId(classId);
		class1.setClassNo(classNo);
		class1.setClassName(className);
		class1.setClassMajCode(majorCode);
		if (informationServiceImpl.updateClass(class1) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("新建成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("新建失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/admin/modifyClass")
	public void modifyClass(HttpServletRequest request,HttpServletResponse response,String classNo,String className,String majorCode) {
		ListObject listObject = new ListObject();
		com.gpms.po.Class class1 = new com.gpms.po.Class(); 
//		class1.setClassId(classId);
		class1.setClassNo(classNo);
		class1.setClassName(className);
		class1.setClassMajCode(majorCode);
		if (informationServiceImpl.modifyClass(class1) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("修改失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	@RequestMapping(value="/api/admin/removeClass")
	public void removeClass(HttpServletRequest request,HttpServletResponse response,String classNo) {
		ListObject listObject = new ListObject();
			
		if (informationServiceImpl.removeClass(classNo) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}

	
	@RequestMapping(value="/api/admin/updateMajor")
	public void updateMajor(HttpServletRequest request,HttpServletResponse response,String majCode,String majName,String majSign) {
		ListObject listObject = new ListObject();
		Major major = new Major(); 
		major.setMajCode(majCode);
		major.setMajName(majName);
		major.setMajSign(majSign);
		System.err.println(major);
		if (informationServiceImpl.updateMajor(major) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("新建成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("新建失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="/api/admin/modifyMajor")
	public void modifyMajor(HttpServletRequest request,HttpServletResponse response,String majCode,String majName,String majSign) {
		ListObject listObject = new ListObject();

		Major major = new Major(); 
		major.setMajCode(majCode);
		major.setMajName(majName);
		major.setMajSign(majSign);
		if (informationServiceImpl.modifyMajor(major) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("修改失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/admin/removeMajor")
	public void removeMajor(HttpServletRequest request,HttpServletResponse response,String majCode) {
		ListObject listObject = new ListObject();
			
		if (informationServiceImpl.removeMajor(majCode) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	@RequestMapping(value="/api/admin/updateDirection")
	public void updateDirection(HttpServletRequest request,HttpServletResponse response,String dirCode,String dirName,String dirSign,String majCode) {
		ListObject listObject = new ListObject();
		MajorDirection majorDirection = new MajorDirection(); 

		majorDirection.setDirCode(dirCode);
		majorDirection.setDirName(dirName);
		majorDirection.setDirSign(dirSign);
		majorDirection.setDirMarCode(majCode);
		if (informationServiceImpl.updateDirection(majorDirection) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("新建成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("新建失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/admin/modifyDirection")
	public void modifyDirection(HttpServletRequest request,HttpServletResponse response,String dirCode,String dirName,String dirSign) {
		ListObject listObject = new ListObject();
		MajorDirection majorDirection = new MajorDirection(); 

		majorDirection.setDirCode(dirCode);
		majorDirection.setDirName(dirName);
		majorDirection.setDirSign(dirSign);
		if (informationServiceImpl.modifyDirection(majorDirection) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("修改失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/admin/removeDirection")
	public void removeDirection(HttpServletRequest request,HttpServletResponse response,String dirCode) {
		ListObject listObject = new ListObject();
			
		if (informationServiceImpl.removeDirection(dirCode) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("删除成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("删除失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/admin/createStudent")
	public void updateStudentNew(HttpServletRequest request,HttpServletResponse response,Student student) {
		ListObject listObject = new ListObject();
			System.out.println(student);
		if (securityServiceImpl.createStudentInfo(student) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("添加成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("添加失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	
	@RequestMapping(value="/api/admin/upStudent")
	public void updateStudent(HttpServletRequest request,HttpServletResponse response,Student student) {
		ListObject listObject = new ListObject();
			
		if (securityServiceImpl.updateStudentInfo(student) > 0) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("修改成功");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject .setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("修改失败");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}
	

	
}
