package com.gpms.test;

import com.gpms.util.sendEmailUtil;


public class send {
	public static void main(String[] args) {
		sendEmailUtil msEmailUtil = new sendEmailUtil("1131429439@qq.com", "�����ʼ�","���Բ��ԣ�����");
		System.err.println(msEmailUtil.send());
	}
}
