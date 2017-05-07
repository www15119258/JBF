package com.cangzhitao.jbf.datasource.interceptor;

/**
 * Created by lihui on 2017/5/6.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static Object getDataSource() {
        return contextHolder.get();
    }

    public static void reset() {
        contextHolder.set("default");
    }

}
