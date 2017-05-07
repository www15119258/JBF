package com.cangzhitao.jbf.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	private volatile static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	
	public static String getProperty(String prop, String key) {
		Properties properties = loadProperties(prop);
		return properties.getProperty(key);
	}
	
	public static String getProperty(String prop, String key, String defaultValue) {
		Properties properties = loadProperties(prop);
		return properties.getProperty(key, defaultValue);
	}
	
	public static Properties loadProperties(String prop) {
		Properties properties = propertiesMap.get(prop);
		if(properties!=null) {
			return properties;
		}
		synchronized (PropertiesUtil.class) {
			if(properties==null) {
				properties = new Properties();
				InputStream infile = null;
				try {
					ClassLoader cl = Thread.currentThread().getContextClassLoader();
					infile = cl.getResourceAsStream(prop+".properties");
					properties.load(infile);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(infile!=null) {
						try {
							infile.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				propertiesMap.put(prop, properties);
			}
		}
		return properties;
	}
	
}
