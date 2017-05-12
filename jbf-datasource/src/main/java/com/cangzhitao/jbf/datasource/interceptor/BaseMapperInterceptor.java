package com.cangzhitao.jbf.datasource.interceptor;

import com.cangzhitao.jbf.core.util.ReflectUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.*;

/**
 * Created by lihui on 2017/5/10.
 */
@Intercepts({
        @Signature(
            type = Executor.class,
            method = "query",
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
            type = Executor.class,
            method = "update",
            args = {MappedStatement.class, Object.class}
        )
})
public class BaseMapperInterceptor implements Interceptor {

    private Properties properties;

    public Object intercept(Invocation invocation) throws Throwable {
//        Object[] args = invocation.getArgs();
//        MappedStatement ms = (MappedStatement) args[0];
//        Object parameterObject = args[1];
//        RowBounds rowBounds = (RowBounds) args[2];
//        BoundSql boundSql = ms.getBoundSql(parameterObject);
//        boundSql.setAdditionalParameter("tableName", "jbf_user");
//
//        Executor executor = (Executor) invocation.getTarget();
//        return executor.executor.query(countMs, parameterObject, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);

//        System.out.println(invocation.getMethod().getParameterTypes());
//        Object[] args = invocation.getArgs();
//        List<Object> argList = new ArrayList<Object>();
//        argList.addAll(Arrays.asList(args));
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("tableName", "jbf_user");
//        argList.add(1,map);
//        args = argList.toArray();
//        ReflectUtil.setField(invocation, "args", args);
//        System.out.println(invocation.getArgs());
//        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
//        System.out.println(ms.getParameterMap().getParameterMappings());
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
