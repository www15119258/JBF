package com.cangzhitao.jbf.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class SpringUtil {
	
	private static ApplicationContext ac = null;
	
	public static ApplicationContext getApplicationContext() {
		return ac;
	}
	
	public static void setApplicationContext(ApplicationContext ac) {
		SpringUtil.ac = ac;
	}
	
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}
	

}
