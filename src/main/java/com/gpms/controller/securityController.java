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
			listObject.setMsg("ע��ɹ���");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
		listObject.setMsg("ע��ʧ�ܣ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}

	/**
	 * ��ȡ��֤��ͼƬ
	 * @param reques
	 * @param response
	 */
	@RequestMapping(value="/api/userLogin/Verification")
	public void getVerificationCode(HttpServletRequest reques,HttpServletResponse response){

		if(securityServiceImpl.getVerificationCodeService(reques,response)){
			response.setContentType("image/jpeg");//������Ӧ����,������������������ΪͼƬ
			response.setHeader("Pragma", "No-cache");//������Ӧͷ��Ϣ�������������Ҫ���������
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
		}else{

		}  
	}
	/**
	 * ��˾���Ƿ����
	 * @param response
	 * @param request
	 * @param comAccount
	 */
	@RequestMapping(value="/api/userLogin/ComIsEx")
	public void companyIsEx(HttpServletResponse response,HttpServletRequest request,String comAccount){
		if (securityServiceImpl.companyIsEx(comAccount)) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("������");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("����");
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
			listObject.setMsg("��ǰ�����˻���¼ϵͳ��");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("��֤ͨ��");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


	/**
	 * �û���¼
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
			listObject.setMsg("��ǰ�����˻���¼ϵͳ��");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		//������Ĳ����п����򷵻ش���code2
		if (account == null || pwd == null || code == null) {
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("�Ƿ����ʣ�");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}

		// ��֤��֤��
//		(String) session.getAttribute("code")
		if (!code.equalsIgnoreCase((String) session.getAttribute("code"))) {
			listObject.setCode(StatusCode.CODE_ERROR_EXIST_OPERATION);
			listObject.setMsg("��֤�����!");
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		// ����ͨ���������һ����¼���˻�
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
				listObject.setMsg("���ʳɹ�");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

				return;
			} catch (AuthenticationException e) {
				listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
				listObject.setMsg("��¼ʧ�ܣ�����ԭ��<br>1.�û������������<br>"
						+ "2.�û�δ���");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
				return;
			}

		}

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("�ظ���¼��Ŷ��");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		System.err.println("after-login-session: "+session.getId()+" : "+session.getAttribute("onlineUser"));
	}

	/**
	 * �û��ǳ�
	 * @paramsession
	 */
	@RequestMapping(value="/api/userLogin/userLogout")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		//			request.getSession().invalidate();
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();

		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("���ʳɹ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}

	/**
	 * ��ҳ���ʼ��
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
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"��ҳ\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-stu.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\""+((Student) session.getAttribute("onlineUser")).getStuName() +"\",\"type\":\"ѧ����\",\"child\":[\"page/student-setting.html\",\"page/student-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"ʵϰ����\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"��ҳ\",\"href\":\"page/welcome-stu.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"������Ϣ\",\"href\":\"page/student-setting.html\",\"icon\":\"fa fa-user\",\"target\":\"_self\"},{\"title\":\"ʵϰ��ҵ\",\"href\":\"page/companyInfo.html\",\"icon\":\"fa fa-briefcase\",\"target\":\"_self\",\"child\":[{\"title\":\"��ҵ��Ϣ\",\"href\":\"page/companyInfo.html\",\"icon\":\"fa fa-building\",\"target\":\"_self\"}]},{\"title\":\"ʵϰ��Ϣ\",\"href\":\"\",\"icon\":\"fa fa-graduation-cap\",\"target\":\"_self\",\"child\":[{\"title\":\"���Ҽ���\",\"href\":\"page/selfcomment.html\",\"icon\":\"fa fa-check\",\"target\":\"_self\"},{\"title\":\"��Ŀ��Ϣ\",\"href\":\"page/internship.html\",\"icon\":\"fa fa-coffee\",\"target\":\"_self\"},{\"title\":\"��־����\",\"href\":\"page/editor.html\",\"icon\":\"fa fa-clipboard\",\"target\":\"_self\"},{\"title\":\"������ɼ�\",\"href\":\"page/achievement.html\",\"icon\":\"fa fa-pie-chart\",\"target\":\"_self\"}]},{\"title\":\"�����ĵ�\",\"href\":\"page/table.html\",\"icon\":\"fa fa-folder-o\",\"target\":\"_self\",\"child\":[{\"title\":\"���ܺ��ϴ�\",\"href\":\"page/upload.html\",\"icon\":\"fa fa-upload\",\"target\":\"_self\"},{\"title\":\"�ĵ�����\",\"href\":\"page/download.html\",\"icon\":\"fa fa-download\",\"target\":\"_self\"}]}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("��ȡ�ɹ�");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}
				if (role.get(0).equals("Teacher")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"��ҳ\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-tea.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\" " +  ((Teacher) session.getAttribute("onlineUser")).getTeaName() + "\",\"type\":\"��ʦ��\",\"child\":[\"page/teacher-setting.html\",\"page/teacher-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"ʵϰ����\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"��ҳ\",\"href\":\"page/welcome-tea.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"������Ϣ\",\"href\":\"page/teacher-setting\",\"icon\":\"fa fa-user\",\"target\":\"_self\"},{\"title\":\"ѧ������\",\"href\":\"\",\"icon\":\"fa fa-group\",\"target\":\"_self\",\"child\":[{\"title\":\"ѧ������\",\"href\":\"page/studentInfoTeacher.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"ʵϰ����\",\"href\":\"page/table.html\",\"icon\":\"fa fa-gears\",\"target\":\"_self\",\"child\":[{\"title\":\"ʵϰ����\",\"href\":\"page/timeSetting.html\",\"icon\":\"fa fa-gear\",\"target\":\"_self\"},{\"title\":\"ʵϰ��Ϣ\",\"href\":\"page/gradeTeacher.html\",\"icon\":\"fa fa-bookmark\",\"target\":\"_self\"}]},{\"title\":\"���Ϲ���\",\"href\":\"page/table.html\",\"icon\":\"fa fa-folder-o\",\"target\":\"_self\",\"child\":[{\"title\":\"�ĵ�ģ��\",\"href\":\"page/uploadExampleFiles.html\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"}]}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("��ȡ�ɹ�");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}
				if (role.get(0).equals("Company")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"��ҳ\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-com.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\""+((Company) session.getAttribute("onlineUser")).getComName()   +"\",\"type\":\"��ҵ��\",\"child\":[\"page/company-setting.html\",\"page/company-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"ʵϰ����\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"��ҳ\",\"href\":\"page/welcome-com.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"��ҵ��Ϣ\",\"href\":\"page/company-setting.html\",\"icon\":\"fa fa-user\",\"target\":\"_self\"},{\"title\":\"��ҵʵϰ��\",\"href\":\"page/studentInfoCompany.html\",\"icon\":\"fa fa-graduation-cap\",\"target\":\"_self\"},{\"title\":\"��־����\",\"href\":\"page/journalCompany.html\",\"icon\":\"fa fa-file-text\",\"target\":\"_self\"},{\"title\":\"ʵϰ����\",\"href\":\"page/gradeCompany.html\",\"icon\":\"fa fa-pie-chart\",\"target\":\"_self\"}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("��ȡ�ɹ�");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}
				if (role.get(0).equals("Admin")) {
					menuList.add("{\"clearInfo\":{\"clearUrl\":\"api/clear.json\"},\"homeInfo\":{\"title\":\"��ҳ\",\"icon\":\"fa fa-home\",\"href\":\"page/welcome-admin.html?mpi=m-p-i-0\"},\"logoInfo\":{\"title\":\"GPMS\",\"image\":\"images/logo.png\",\"href\":\"\"},\"userInfo\":{\"userName\":\""+ ((Admin) session.getAttribute("onlineUser")).getAdminName()+"\",\"type\":\"����Ա\",\"child\":[\"page/admin-setting.html\",\"page/admin-password.html\"]},\"menuInfo\":{\"currency\":{\"title\":\"�������\",\"icon\":\"fa fa-address-book\",\"child\":["
							+ "{\"title\":\"��ҳ\",\"href\":\"page/welcome-admin.html\",\"icon\":\"fa fa-home\",\"target\":\"_self\"},{\"title\":\"רҵ��Ϣ\",\"href\":\"\",\"icon\":\"fa fa-flask\",\"target\":\"_self\",\"child\":[{\"title\":\"רҵ����\",\"href\":\"page/major.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"�༶��Ϣ\",\"href\":\"page/classAdmin.html\",\"icon\":\"fa fa-group\",\"target\":\"_self\"},{\"title\":\"��ҵ��Ϣ\",\"href\":\"page/companyInfoAdmin.html\",\"icon\":\"fa fa-briefcase\",\"target\":\"_self\"},{\"title\":\"ϵͳ�˻�\",\"href\":\"page/table.html\",\"icon\":\"fa fa-user-circle\",\"target\":\"_self\",\"child\":[{\"title\":\"ѧ���˻�\",\"href\":\"page/studentInfoAdmin.html\",\"icon\":\"fa fa-graduation-cap\",\"target\":\"_self\"},{\"title\":\"��ʦ�˻�\",\"href\":\"page/teacherInfoAdmin.html\",\"icon\":\"fa fa-anchor\",\"target\":\"_self\"},{\"title\":\"��ҵ�˻�\",\"href\":\"page/companyInfo2Admin.html\",\"icon\":\"fa fa-user\",\"target\":\"_self\"}]},{\"title\":\"ʵϰ����\",\"href\":\"page/table.html\",\"icon\":\"fa fa-gears\",\"target\":\"_self\",\"child\":[{\"title\":\"ʵϰ����\",\"href\":\"page/timeSettingAdmin.html\",\"icon\":\"fa fa-gear\",\"target\":\"_self\"},{\"title\":\"ʵϰ��Ϣ\",\"href\":\"page/gradeAdmin.html\",\"icon\":\"fa fa-bookmark\",\"target\":\"_self\"}]}]}}}");
					listObject.setCode(StatusCode.CODE_SUCCESS);
					listObject.setMsg("��ȡ�ɹ�");
					listObject.setData(menuList);
					ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
					return;
				}

			}

			return;
		}
		listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
		listObject.setMsg("û��¼����ûȨ�޷���");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


	@RequestMapping(value="/api/userLogin/notAccount")
	public void notAccount(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		System.out.println(request.getSession().getId());
		listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
		listObject.setMsg("û��¼����ûȨ�޷���");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


	@RequestMapping(value="/api/userLogin/notLogin")
	public void notLogin(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		listObject.setContent(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT, "��û�е�¼������ʱ���޶������ǳ���");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));

	}

	@RequestMapping(value="/api/")
	public void test(HttpServletRequest request,HttpServletResponse response) {
		ListObject listObject = new ListObject();
		System.out.println(request.getSession().getId());
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("���ʳɹ�");
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
		sendEmailUtil sendEmailUtil = new sendEmailUtil(email, "��֤����", "<h3>��ӭʹ��gpmsϵͳ�����������֤��Ϊ��</h3>"
				+ "<div style='color:#FFB800;font-weight: bold;font-size: 30px;'>"+code+"</div"
				+ "><div style='font-size: 12px;color: #aaa;text-align: right;padding-right: 25px;padding-bottom: 10px;'>ϵͳ�ʼ�������ظ�</div>");
		sendEmailUtil.send();
		//		request.getSession().setAttribute("code", code);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("���ʳɹ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	@RequestMapping(value="api/userLogin/Invitation")
	public void Invitation(String email,HttpServletRequest request,HttpServletResponse response) {
		
		sendEmailUtil sendEmailUtil = new sendEmailUtil(email, "��֤����",
				"<h3>" + ((Student)request.getSession().getAttribute("onlineUser")).getStuName() + "����������GPMSϵͳ</h3>"
				+ "<a style='color:#FFB800;font-weight: bold;font-size: 30px;' target='_blank' href='http://localhost/gpmsss/page/companyRegister.html' tager=''>�������ȥע��</a>"
				+ "<div style='font-size: 12px;color: #aaa;text-align: right;padding-right: 25px;padding-bottom: 10px;'>ϵͳ�ʼ�������ظ�</div>");
		sendEmailUtil.send();
		//		request.getSession().setAttribute("code", code);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("���ʳɹ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
}
