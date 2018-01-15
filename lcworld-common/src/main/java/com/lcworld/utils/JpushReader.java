package com.lcworld.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JpushReader {
	private static Properties properties;

	// 得到PropertiesReader的实例
	public static String getProperty(String key) {
		if (properties == null) {
			synchronized (JpushReader.class) {
				if (properties == null) {
					InputStream is = JpushReader.class.getClassLoader().getResourceAsStream("jpush.properties");
					properties = new Properties();
					try {
						properties.load(is);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return properties.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(JpushReader.getProperty("jpush.user.MASTER_SECRET"));
	}
}
