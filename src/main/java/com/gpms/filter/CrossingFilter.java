package com.gpms.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import javax.servlet.http.HttpServletRequest;
 
public class CrossingFilter implements Filter {
 
	private boolean isCross = false;
 
	@Override
	public void destroy() {
		isCross = false;
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (isCross) {
//			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//			System.out.println("拦截请求: " + httpServletRequest.getServletPath());
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:81");
//			httpServletResponse.setHeader("Access-Control-Allow-Methods", "*"); // 表示所有请求都有效
			httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			httpServletResponse.setHeader("Access-Control-Max-Age", "0");
			httpServletResponse.setHeader("Access-Control-Allow-Headers",
					"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setHeader("XDomainRequestAllowed", "1");
		}
		chain.doFilter(request, response);
	}
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String isCrossStr = filterConfig.getInitParameter("IsCross");
		isCross = isCrossStr.equals("true") ? true : false;
		System.out.println("跨域开启状态：" + isCrossStr);
	}
}