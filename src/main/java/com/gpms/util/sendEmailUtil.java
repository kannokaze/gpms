package com.gpms.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class sendEmailUtil{

	/**
	 * 获取发送时间
	 * @param currentTime
	 * @return
	 */
	public static String getNowDate(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	private String email;// 收件人邮箱
	private String content;

	private String title; //邮件名

	public sendEmailUtil(String email,String title, String content) {
		this.email = email;
		this.content = content;
		this.title = title;
	}

	/**
	 * 载入模板内容
	 * @param ContentText
	 * @return
	 */
	private String buildContent(String to,String ContentText) {

		//加载邮件html模板
		String fileName = "post.html";
		InputStream inputStream = null;
		BufferedReader fileReader = null;
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			inputStream = sendEmailUtil.class.getClassLoader().getResourceAsStream(fileName);
			fileReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			while ((line = fileReader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			//            LOGGER.error("读取文件失败，fileName:{}", fileName, e);
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		String date = getNowDate(new Date());
		//填充html模板中的五个参数
		Properties systemProperties = new Properties();
		InputStream in = sendEmailUtil.class.getClassLoader().getResourceAsStream("system.properties");
		// 使用properties对象加载输入流
		try {
			systemProperties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取key对应的value
		String htmlText = MessageFormat.format(buffer.toString(),to , ContentText, 
				date,systemProperties.getProperty("system.protocol")+systemProperties.getProperty("system.host"));
		return htmlText;
	}

	/**
	 * 发送邮件
	 * @return
	 */
	public boolean send() {
		// 1.创建连接对象javax.mail.Session
		// 2.创建邮件对象 javax.mail.Message
		// 3.发送一封激活邮件
		String from = "1131429439@qq.com";// 发件人电子邮箱
		String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

		Properties properties = System.getProperties();// 获取系统属性

		properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
		properties.setProperty("mail.smtp.auth", "true");// 打开认证

		boolean flat = true; //发送成功返回true；

		try {
			//QQ邮箱需要下面这段代码，163邮箱不需要
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);


			// 1.获取默认session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("1131429439@qq.com", "oalwdgceseyobaea"); // 发件人邮箱账号、授权码
				}
			});

			// 2.创建邮件对象
			Message message = new MimeMessage(session);
			// 2.1设置发件人
			message.setFrom(new InternetAddress(from,"+"));
			// 2.2设置接收人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 2.3设置邮件主题
			message.setSubject(title);

			// 2.4设置邮件头部和内容
			message.setHeader("Content-Type", "text/html; charset=utf-8");
			message.setContent(buildContent(email,content), "text/html;charset=utf-8");
			// 3.发送邮件

			//			Transport transport = session.getTransport();
			Transport.send(message);

		} catch (Exception e) {
			flat = false;
			e.printStackTrace();
		} finally{
			//			Transport.close();
		}
		return flat;
	}


}