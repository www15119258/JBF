package com.cangzhitao.jbf.codegenerator.database;

import com.cangzhitao.jbf.core.util.SpringUtil;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * Created by lihui on 2017/5/12.
 */
public class JavaToTable {

    private static SqlSessionTemplate sqlSessionTemplate;

    public static SqlSessionTemplate getSqlSessionTemplate() {
        if(sqlSessionTemplate==null) {
            synchronized (JavaToTable.class) {
                if(sqlSessionTemplate!=null) {
                    return sqlSessionTemplate;
                }
                sqlSessionTemplate = (SqlSessionTemplate) SpringUtil.getBean("sqlSessionTemplate");
            }
        }
        return sqlSessionTemplate;
    }

    public static boolean isModify(Class c) {
        return true;
    }

    public static void createTable(Class c) {
        SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate();
//        sqlSessionTemplate.
    }

}
