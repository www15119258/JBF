package com.cangzhitao.jbf.datasource.interceptor;

import com.cangzhitao.jbf.datasource.annotation.DataSourceSelect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by lihui on 2017/5/6.
 */
@Component
@Aspect
@Order(value=1)
public class DataSourceInterceptor {

    //
    //@Before("execution(* com.cangzhitao.jbf.security.mapper.UserMapper.*(..))")
    @Before("execution(* com.cangzhitao.jbf.datasource.mapper.BaseMapper+.*(..))")
    public void setDataSource(JoinPoint joinPoint) {
        String dataSource = getDataSource(joinPoint);
        DataSourceContextHolder.setDataSource(dataSource);
    }

    private String getDataSource(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DataSourceSelect dataSourceSelect = method.getAnnotation(DataSourceSelect.class);
        if(dataSourceSelect!=null) {
            return dataSourceSelect.value();
        }
        dataSourceSelect = method.getDeclaringClass().getAnnotation(DataSourceSelect.class);
        if(dataSourceSelect!=null) {
            return dataSourceSelect.value();
        }
        return "default";
    }


}
