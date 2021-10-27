package com.gpms.service;

import com.gpms.mapper.CompanyMapper;
import com.gpms.mapper.StudentMapper;
import com.gpms.po.Company;
import com.gpms.po.Student;
import com.gpms.util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class securityServiceImpl {
	@Autowired
	public CompanyMapper companyMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	VerificationCode verificationCode;
	
	/**
	 * 激活新企业用户
	 * @param code
	 * @param company
	 * @return
	 */
	public boolean activeUser(String code,Company company) {
		if(companyMapper.updateByPrimaryKey(company)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 企业注册
	 * @param company
	 * @param email
	 * @return
	 */
	public boolean doRegister(Company company) {
		//保存成功则通过线程的方式给用户发送一封邮件
		if(companyMapper.insert(company)>0){
//			new sendEmailUtil(email, code);
			return true;
		}
		return false;
	}
	
	public boolean companyIsEx(String account) {
		if (companyMapper.selectByAccount(account).size() > 0) {
			return false;
		}
		return true;
	}
	
	public boolean agreeCompany(String account) {
		if (companyMapper.agreeCompany(account) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean getVerificationCodeService(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		
		verificationCode = new VerificationCode();
		System.out.println(session.getId());
		BufferedImage image = verificationCode.getImage();
		session.setAttribute("code",verificationCode.getText());
		boolean flat = true;
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			flat = false;
		}
		return flat;
	}
	
	/**
	 * 设置新密码
	 * @param request
	 * @param response
	 */
	public void resetPassword(HttpServletRequest request,HttpServletResponse response) {
		if (request.getSession().getAttribute("user_login") != null) {
			
		}

	}
	
	/**
	 * 用户登录
	 * @param session
	 * @param stuNo
	 * @param pwd
	 * @return 
	 */
	public boolean userLogin(HttpSession session,String stuNo,String pwd) {
		Student userStudent = studentMapper.selectByNumAndPwd(stuNo, pwd);
		boolean isTrue = false;
		if (userStudent != null) {
			session.setAttribute("onlineUser", userStudent);
			isTrue = true;
		}
		return isTrue;
	}
	
	public boolean verification() {
		
		return true;
	}

	public int createStudentInfo(Student student) {
		return studentMapper.insertSelective(student);
	}

	public int updateStudentInfo(Student student) {
		return studentMapper.updateByStuNo(student);
	}

	
}
