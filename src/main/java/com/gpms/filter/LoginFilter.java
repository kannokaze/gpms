package com.gpms.filter;

import com.gpms.util.JsonUtils;
import com.gpms.util.ListObject;
import com.gpms.util.ResponseUtils;
import com.gpms.util.StatusCode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	ListObject listObject;
	
	
	public LoginFilter() {
		listObject = new ListObject();
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI();
		String ctxPath = request.getContextPath();
		

		String targetURL = currentURL.substring(ctxPath.length());
		
		HttpSession session = request.getSession(false);
		if (targetURL.indexOf("/api/userLogin") < 0) {
			
			if (session == null || session.getAttribute("onlineUser") == null) {
				// ����ֱ����ӦcodeΪ δ��¼��
				ListObject listObject = new ListObject();
				listObject.setContent(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT, "��û�е�¼������ʱ���޶������ǳ���");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
				System.out.println("δ��¼���أ�");
				return;
			} else {
				// �����ʾ��ȷ����ȥѰ����һ��������������ڣ������������ҳ����ת
				System.out.println("����ͨ����");
				chain.doFilter(request, response);
				return;
			}
		}else {
			chain.doFilter(request, response);
			return;
		}
		


	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}