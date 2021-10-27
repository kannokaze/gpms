package com.gpms.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
/*
 * 响应处理
 */
public class ResponseUtils {
 
	/*
	 * 页面异步回调返回Json
	 */
	public static void outputJson(HttpServletResponse response, Object obj) {
		String s = JsonWriter.toJson(obj, false);
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	/*
	 * 发送内容,使用UTF-8编码
	 */
	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}
 
	/*
	 * 返回json串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		// System.out.print(text);
		render(response, "text/json;charset=UTF-8", text);
	}
	
	/*
	 * 返回文本
	 */
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}
}