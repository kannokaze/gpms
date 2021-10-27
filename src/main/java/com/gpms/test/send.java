package com.gpms.test;

import com.gpms.util.sendEmailUtil;


public class send {
	public static void main(String[] args) {
		sendEmailUtil msEmailUtil = new sendEmailUtil("1131429439@qq.com", "²âÊÔÓÊ¼ş","²âÊÔ²âÊÔ£¡£¡£¡");
		System.err.println(msEmailUtil.send());
	}
}
