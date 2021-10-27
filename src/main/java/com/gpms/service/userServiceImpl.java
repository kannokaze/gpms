package com.gpms.service;

import com.gpms.mapper.AdminMapper;
import com.gpms.mapper.CompanyMapper;
import com.gpms.mapper.StudentMapper;
import com.gpms.mapper.TeacherMapper;
import com.gpms.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class userServiceImpl {

	@Autowired
	private StudentMapper stuDao;

	@Autowired
	private TeacherMapper teaDao;

	@Autowired
	private CompanyMapper comDao;

	@Autowired
	private AdminMapper adminDao;

	/*
	 * �÷�����֤ѧ���û�
	 */

	public boolean studentLogin(String u_name, String u_password,
			HttpSession session) {
		Student stu = stuDao.selectByNumAndPwd(u_name,u_password);
		if (stu != null) {
			session.setAttribute("login_user", stu);
			return true;
		}
		return false;
	}

	/*
	 * �÷�����֤��ʦ��¼
	 */
	public boolean teacher(String u_name, String u_password, HttpSession session) {

		return false;

	}

}
