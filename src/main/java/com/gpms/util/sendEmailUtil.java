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
	 * ��ȡ����ʱ��
	 * @param currentTime
	 * @return
	 */
	public static String getNowDate(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	private String email;// �ռ�������
	private String content;

	private String title; //�ʼ���

	public sendEmailUtil(String email,String title, String content) {
		this.email = email;
		this.content = content;
		this.title = title;
	}

	/**
	 * ����ģ������
	 * @param ContentText
	 * @return
	 */
	private String buildContent(String to,String ContentText) {

		//�����ʼ�htmlģ��
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
			//            LOGGER.error("��ȡ�ļ�ʧ�ܣ�fileName:{}", fileName, e);
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
		//���htmlģ���е��������
		Properties systemProperties = new Properties();
		InputStream in = sendEmailUtil.class.getClassLoader().getResourceAsStream("system.properties");
		// ʹ��properties�������������
		try {
			systemProperties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ȡkey��Ӧ��value
		String htmlText = MessageFormat.format(buffer.toString(),to , ContentText, 
				date,systemProperties.getProperty("system.protocol")+systemProperties.getProperty("system.host"));
		return htmlText;
	}

	/**
	 * �����ʼ�
	 * @return
	 */
	public boolean send() {
		// 1.�������Ӷ���javax.mail.Session
		// 2.�����ʼ����� javax.mail.Message
		// 3.����һ�⼤���ʼ�
		String from = "1131429439@qq.com";// �����˵�������
		String host = "smtp.qq.com"; // ָ�������ʼ�������smtp.qq.com(QQ)|smtp.163.com(����)

		Properties properties = System.getProperties();// ��ȡϵͳ����

		properties.setProperty("mail.smtp.host", host);// �����ʼ�������
		properties.setProperty("mail.smtp.auth", "true");// ����֤

		boolean flat = true; //���ͳɹ�����true��

		try {
			//QQ������Ҫ������δ��룬163���䲻��Ҫ
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);


			// 1.��ȡĬ��session����
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("1131429439@qq.com", "oalwdgceseyobaea"); // �����������˺š���Ȩ��
				}
			});

			// 2.�����ʼ�����
			Message message = new MimeMessage(session);
			// 2.1���÷�����
			message.setFrom(new InternetAddress(from,"+"));
			// 2.2���ý�����
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 2.3�����ʼ�����
			message.setSubject(title);

			// 2.4�����ʼ�ͷ��������
			message.setHeader("Content-Type", "text/html; charset=utf-8");
			message.setContent(buildContent(email,content), "text/html;charset=utf-8");
			// 3.�����ʼ�

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