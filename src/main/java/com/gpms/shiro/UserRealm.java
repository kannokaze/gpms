package com.gpms.shiro;

import com.gpms.mapper.AdminMapper;
import com.gpms.mapper.CompanyMapper;
import com.gpms.mapper.StudentMapper;
import com.gpms.mapper.TeacherMapper;
import com.gpms.po.Admin;
import com.gpms.po.Company;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @todo 自定义 Realm,查询数据库并返回正确的数据
 * @author Hans
 * @time 2018下午6:11:20
 *
 */
public class UserRealm extends AuthorizingRealm{

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	AdminMapper adminMapper;

	/**
	 * @see 认证登录，查询数据库，如果该用户名正确，得到正确的数据，并返回正确的数据
	 * 		AuthenticationInfo的实现类SimpleAuthenticationInfo保存正确的用户信息
	 *    
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo info = null;
		//1.将token转换为UsernamePasswordToken
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
		//2.获取token中的登录账户
		String userCode = userToken.getUsername();
		char[] pwdString = userToken.getPassword();

		System.out.println("认证-------------------------------------");
		//3.查询数据库，是否存在指定的用户名和密码的用户(主键/账户/密码/账户状态/盐)
		
		Object stu= studentMapper.selectByNumAndPwd(userCode,String.valueOf(pwdString));
		if (stu != null) {
			System.out.println("学生登录");
			info = new SimpleAuthenticationInfo(stu,((Student)stu).getStuPassword(),"Student");
			return info;
		}
		
		Object tea= teacherMapper.selectByAccountAndPwd(userCode,String.valueOf(pwdString));
		if(tea !=null){
			info = new SimpleAuthenticationInfo(tea, ((Teacher) tea).getTeaPassword(),"Teacher");
			return info;
		}
		
		Object com= companyMapper.selectByAccountAndPwd(userCode,String.valueOf(pwdString));
		if (com !=null) {
			if (((Company) com).getComState().equals("0")) {
				System.out.println("企业登录"+com);
				info = new SimpleAuthenticationInfo(com, ((Company) com).getComPassword(),"Company");
				return info;
			}
			return info;
		}
			
		Object admin= adminMapper.selectByAccountAndPwd(userCode,String.valueOf(pwdString));
		System.out.println(admin);
		if (admin != null) {
			info = new SimpleAuthenticationInfo(admin, ((Admin) admin).getAdminPassword(),"Admin");
			return info;
		}

		//5. 返回给调用login(token)方法
		return info;
	}

	/**
	 * @see 授权，在配有缓存的情况下，只加载一次。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//当前登录用户，账号
		Set<String> roles = new HashSet<String>();
		roles.addAll(principals.getRealmNames());
		System.out.println(principals.toString());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		System.out.println("授权："+info);
		return info;
	}

}