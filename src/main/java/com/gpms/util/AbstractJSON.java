package com.gpms.util;
 
import java.util.Date;


/*
 * ����AbstractJSON(JSON���ݵ���Ӧ���� )
 */
public class AbstractJSON {
	private String code;                            //��Ӧ״̬�� 
	private String msg;                             //��Ӧ״̬����  
	private Long time = new Date().getTime();       //ʱ���  
//	private String count;
 
//	public String getCount() {
//		return count;
//	}
//
//	public void setCount(String count) {
//		this.count = count;
//	}

	public String getCode() {
		return code;
	}
 
	public String getMsg() {
		return msg;
	}
 
	public Long getTime() {
		return time;
	}
 
	public void setCode(String code) {
		this.code = code;
	}
 
	public void setContent(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
 
	public void setMsg(String msg) {
		this.msg = msg;
	}
 
	public void setStatusObject(AbstractJSON statusObject) {
		this.code = statusObject.getCode();
		this.msg = statusObject.getMsg();
	}
 
	public void setTime(Long time) {
		this.time = time;
	}
}