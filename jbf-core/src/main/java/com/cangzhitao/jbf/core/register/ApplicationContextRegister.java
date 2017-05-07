package com.cangzhitao.jbf.core.register;

import com.cangzhitao.jbf.core.util.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class ApplicationContextRegister implements ApplicationContextAware {

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.setApplicationContext(applicationContext);
	}

}
