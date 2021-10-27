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
				// 这里直接响应code为 未登录码
				ListObject listObject = new ListObject();
				listObject.setContent(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT, "您没有登录，或因长时间无动作而登出！");
				ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
				System.out.println("未登录拦截！");
				return;
			} else {
				// 这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转
				System.out.println("正常通过！");
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