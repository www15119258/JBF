package com.cangzhitao.jbf.datasource;

import com.cangzhitao.jbf.datasource.interceptor.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * Created by lihui on 2017/5/6.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

}
