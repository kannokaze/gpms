package com.gpms.controller;

import com.gpms.po.Admin;
import com.gpms.po.Company;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import com.gpms.service.securityServiceImpl;
import com.gpms.service.userServiceImpl;
import com.gpms.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class securityController {

	@Autowired
	securityServiceImpl securityServiceImpl;

	@Autowired
	userServiceImpl userServiceImpl;

	/**
	 * 
	 * @param company
	 */
	@RequestMapping(value="/api/userLogin/register",method=RequestMethod.POST)
	public void companyRegister(Company company,HttpServletResponse response) {
		// TODO Auto-generated method stub
		company.setComRegistime(new Date());
		company.setComState("1");
		if (securityServiceImpl.doRegister(company)) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("注册成功！");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
		listObject.setMsg("注册失败！");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	/**
	 * 获取验证码图片
	 * @param reques
	 * @param response
	 */
	@RequestMapping(value="/api/userLogin/Verification")
	public void getVerificationCode(HttpServletRequest reques,HttpServletResponse response){

		if(securityServiceImpl.getVerificationCodeService(reques,response)){
			response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
		}else{

		}  
	}
	/**
	 * 公司名是否存在
	 * @param response
	 * @param request
	 * @param comAccount
	 */
	@RequestMapping(value="/api/userLogin/ComIsEx")
	public void companyIsEx(HttpServletResponse response,HttpServletRequest request,String comAccount){
		if (securityServiceImpl.companyIsEx(comAccount)) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("不存在");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("存在");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/api/userLogin/isLogin",method=RequestMethod.GET)
	public void islogin(String account,String pwd,String code,HttpServletRequest request,HttpServletResponse response){

		ListObject listObject = new ListObject();
		Subject currentUser = SecurityUtils.getSubject();
		System.err.println("before-login-session: "+request.getSession().getId()+" : "+request.getSession().getAttribute("onlineUser"));
		Session session = currentUser.getSession();
		if (session.getAttribute("onlineUser") != null) {
			listObject.setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("当前已有账户登录系统！");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("验证通过");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


	/**
	 * 用户登录
	 * @param
	 * @param pwd
	 * @param code
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/api/userLogin",method=RequestMethod.POST)
	public void login(String account,String pwd,String code,HttpServletRequest request,HttpServletResponse response){

		ListObject listObject = new ListObject();

		Subject currentUser = SecurityUtils.getSubject();

		System.err.println("before-login-session: "+request.getSession().getId()+" : "+request.getSession().getAttribute("onlineUser"));

		Session session = currentUser.getSession();
		if (session.getAttribute("onlineUser") != null) {
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("当前已有账户登录系统！");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		//当传入的参数有空置则返回错误code2
		if (account == null || pwd == null || code == null) {
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("非法访问！");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		// 验证验证码
//		(String) session.getAttribute("code")
		if (!code.equalsIgnoreCase((String) session.getAttribute("code"))) {
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("验证码错误!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		// 顶掉通浏览器下上一个登录的账户
		//		if (session.getAttribute("onlineUser") != null) {
		//			currentUser.logout();
		//		}

		if(currentUser.isAuthenticated() == false){
			UsernamePasswordToken token = new UsernamePasswordToken(account,pwd);
			try {
				currentUser.login(token);
				System.out.println(currentUser.getPrincipals().getRealmNames());
				session.setAttribute("onlineUser", currentUser.getPrincipals().getPrimaryPrincipal());
				listObject.setCode(StatusCode.CODE_SUCCESS);
				listObject.setMsg("访问成功");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

				return;
			} catch (AuthenticationException e) {
				listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
				listObject.setMsg("登录失败！可能原因：<br>1.用户名或密码错误<br>"
						+ "2.用户未激活！");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
				return;
			}

		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("重复登录了哦！");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		System.err.println("after-login-session: "+session.getId()+" : "+session.getAttribute("onlineUser"));
	}

	/**
	 * 用户登出
	 * @paramsession
	 */
	@RequestMapping(value="/api/userLogin/userLogout")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		//			request.getSession().invalidate();
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}

	/**
	 * 主页面初始化
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/api/init")
	public void createMenu(HttpServletRequest request,HttpServletResponse response){
		ListObject listObject = new ListObject();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession(); 
		if (session.getAttribute("onlineUser") != null) {
			List<String> menuList = new ArrayList<String>();
			List role= new ArrayList(currentUser.getPrincipals().getRealmNames());
			if (role.get(0) != null) {
				if (role.get(0).equals("Student")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"首页\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-stu.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\""+((Student) session.getAttribute("onlineUser")).getStuName() +"\",\"type\":\"学生端\",\"child\":[\"page/student-setting.html\",\"page/student-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"实习管理\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"首页\",\"href\":\"page/welcome-stu.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"个人信息\",\"href\":\"page/student-setting.html\",\"icon\":\"fa fa-user\",\"target\":\"_self\"},{\"title\":\"实习企业\",\"href\":\"page/companyInfo.html\",\"icon\":\"fa fa-briefcase\",\"target\":\"_self\",\"child\":[{\"title\":\"企业信息\",\"href\":\"page/companyInfo.html\",\"icon\":\"fa fa-building\",\"target\":\"_self\"}]},{\"title\":\"实习信息\",\"href\":\"\",\"icon\":\"fa fa-graduation-cap\",\"target\":\"_self\",\"child\":[{\"title\":\"自我鉴定\",\"href\":\"page/selfcomment.html\",\"icon\":\"fa fa-check\",\"target\":\"_self\"},{\"title\":\"项目信息\",\"href\":\"page/internship.html\",\"icon\":\"fa fa-coffee\",\"target\":\"_self\"},{\"title\":\"日志管理\",\"href\":\"page/editor.html\",\"icon\":\"fa fa-clipboard\",\"target\":\"_self\"},{\"title\":\"评价与成绩\",\"href\":\"page/achievement.html\",\"icon\":\"fa fa-pie-chart\",\"target\":\"_self\"}]},{\"title\":\"资料文档\",\"href\":\"page/table.html\",\"icon\":\"fa fa-folder-o\",\"target\":\"_self\",\"child\":[{\"title\":\"接受函上传\",\"href\":\"page/upload.html\",\"icon\":\"fa fa-upload\",\"target\":\"_self\"},{\"title\":\"文档下载\",\"href\":\"page/download.html\",\"icon\":\"fa fa-download\",\"target\":\"_self\"}]}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("获取成功");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}
				if (role.get(0).equals("Teacher")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"首页\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-tea.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\" " +  ((Teacher) session.getAttribute("onlineUser")).getTeaName() + "\",\"type\":\"教师端\",\"child\":[\"page/teacher-setting.html\",\"page/teacher-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"实习管理\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"首页\",\"href\":\"page/welcome-tea.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"个人信息\",\"href\":\"page/teacher-setting\",\"icon\":\"fa fa-user\",\"target\":\"_self\"},{\"title\":\"学生管理\",\"href\":\"\",\"icon\":\"fa fa-group\",\"target\":\"_self\",\"child\":[{\"title\":\"学生管理\",\"href\":\"page/studentInfoTeacher.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"实习管理\",\"href\":\"page/table.html\",\"icon\":\"fa fa-gears\",\"target\":\"_self\",\"child\":[{\"title\":\"实习控制\",\"href\":\"page/timeSetting.html\",\"icon\":\"fa fa-gear\",\"target\":\"_self\"},{\"title\":\"实习信息\",\"href\":\"page/gradeTeacher.html\",\"icon\":\"fa fa-bookmark\",\"target\":\"_self\"}]},{\"title\":\"资料管理\",\"href\":\"page/table.html\",\"icon\":\"fa fa-folder-o\",\"target\":\"_self\",\"child\":[{\"title\":\"文档模板\",\"href\":\"page/uploadExampleFiles.html\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"}]}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("获取成功");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}
				if (role.get(0).equals("Company")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"首页\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-com.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\""+((Company) session.getAttribute("onlineUser")).getComName()   +"\",\"type\":\"企业端\",\"child\":[\"page/company-setting.html\",\"page/company-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"实习管理\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"首页\",\"href\":\"page/welcome-com.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"企业信息\",\"href\":\"page/company-setting.html\",\"icon\":\"fa fa-user\",\"target\":\"_self\"},{\"title\":\"企业实习生\",\"href\":\"page/studentInfoCompany.html\",\"icon\":\"fa fa-graduation-cap\",\"target\":\"_self\"},{\"title\":\"日志评价\",\"href\":\"page/journalCompany.html\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"},{\"title\":\"实习评价\",\"href\":\"page/gradeCompany.html\",\"icon\":\"fa fa-pie-chart\",\"target\":\"_self\"}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("获取成功");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}
				if (role.get(0).equals("Admin")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"首页\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-admin.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\""+ ((Admin) session.getAttribute("onlineUser")).getAdminName()+"\",\"type\":\"管理员\",\"child\":[\"page/admin-setting.html\",\"page/admin-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"常规管理\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"首页\",\"href\":\"page/welcome-admin.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"专业信息\",\"href\":\"\",\"icon\":\"fa fa-flask\",\"target\":\"_self\",\"child\":[{\"title\":\"专业管理\",\"href\":\"page/major.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"班级信息\",\"href\":\"page/classAdmin.html\",\"icon\":\"fa fa-group\",\"target\":\"_self\"},{\"title\":\"企业信息\",\"href\":\"page/companyInfoAdmin.html\",\"icon\":\"fa fa-briefcase\",\"target\":\"_self\"},{\"title\":\"系统账户\",\"href\":\"page/table.html\",\"icon\":\"fa fa-user-circle\",\"target\":\"_self\",\"child\":[{\"title\":\"学生账户\",\"href\":\"page/studentInfoAdmin.html\",\"icon\":\"fa fa-graduation-cap\",\"target\":\"_self\"},{\"title\":\"导师账户\",\"href\":\"page/teacherInfoAdmin.html\",\"icon\":\"fa fa-anchor\",\"target\":\"_self\"},{\"title\":\"企业账户\",\"href\":\"page/companyInfo2Admin.html\",\"icon\":\"fa fa-user\",\"target\":\"_self\"}]},{\"title\":\"实习管理\",\"href\":\"page/table.html\",\"icon\":\"fa fa-gears\",\"target\":\"_self\",\"child\":[{\"title\":\"实习控制\",\"href\":\"page/timeSettingAdmin.html\",\"icon\":\"fa fa-gear\",\"target\":\"_self\"},{\"title\":\"实习信息\",\"href\":\"page/gradeAdmin.html\",\"icon\":\"fa fa-bookmark\",\"target\":\"_self\"}]}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("获取成功");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}

			}

			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
		listObject.setMsg("没登录或者没权限访问");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


	@RequestMapping(value="/api/userLogin/notAccount")
	public void notAccount(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		System.out.println(request.getSession().getId());
		listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
		listObject.setMsg("没登录或者没权限访问");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


	@RequestMapping(value="/api/userLogin/notLogin")
	public void notLogin(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		listObject.setContent(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT, "您没有登录，或因长时间无动作而登出！");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}

	@RequestMapping(value="/api/")
	public void test(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		System.out.println(request.getSession().getId());
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	@RequestMapping(value="/api/userLogin/verifiyMail")
	public void verifiyMail(String code,String email,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		}).start();
		sendEmailUtil sendEmailUtil = new sendEmailUtil(email, "验证邮箱", "<h3>欢迎使用gpms系统，你的邮箱验证码为：</h3>"
				+ "<div style='color:#FFB800;font-weight: bold;font-size: 30px;'>"+code+"</div"
				+ "><div style='font-size: 12px;color: #aaa;text-align: right;padding-right: 25px;padding-bottom: 10px;'>系统邮件，请勿回复</div>");
		sendEmailUtil.send();
		//		request.getSession().setAttribute("code", code);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="api/userLogin/Invitation")
	public void Invitation(String email,HttpServletRequest request,HttpServletResponse response) {
		
		sendEmailUtil sendEmailUtil = new sendEmailUtil(email, "验证邮箱",
				"<h3>" + ((Student)request.getSession().getAttribute("onlineUser")).getStuName() + "邀请您加入GPMS系统</h3>"
				+ "<a style='color:#FFB800;font-weight: bold;font-size: 30px;' target='_blank' href='http://localhost/gpmsss/page/companyRegister.html' tager=''>点击这里去注册</a>"
				+ "<div style='font-size: 12px;color: #aaa;text-align: right;padding-right: 25px;padding-bottom: 10px;'>系统邮件，请勿回复</div>");
		sendEmailUtil.send();
		//		request.getSession().setAttribute("code", code);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
}
