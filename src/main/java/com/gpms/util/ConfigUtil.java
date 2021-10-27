package com.gpms.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigUtil {
	private static Properties properties = new Properties();

	public static String getProperty(String propertiesName,String name) {
		String value = null;
		try {
			properties = PropertiesLoaderUtils.loadAllProperties(propertiesName);
			//			System.out.println(new String(properties.getProperty(name).getBytes("iso-8859-1"), "gbk"));
			value =  new String(properties.getProperty(name).getBytes("iso-8859-1"), "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void list() {
		Enumeration<?> en = properties.propertyNames(); //得到配置文件的名字
		while(en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = properties.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}
	}
	
	public static void removeProperty(String propertiesName,String key) throws Exception {
		if (properties.getProperty(key) != null) {
			properties.remove(key);
		}else {
			throw new Exception("key is undefind");
		}
	}

	public static void setProperty(String propertiesName,String name,String value){
		try {
			properties = PropertiesLoaderUtils.loadAllProperties(propertiesName);
			properties.setProperty(name, value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
