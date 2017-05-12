package com.cangzhitao.jbf.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cangzhitao.jbf.core.util.PropertiesUtil;
import com.cangzhitao.jbf.datasource.annotation.MyBatisMapper;
import com.cangzhitao.jbf.datasource.interceptor.BaseMapperInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lihui on 2017/5/6.
 */
@EnableTransactionManagement
@Configuration
public class DataSourceConfiguration implements ApplicationContextAware, EnvironmentAware {

    private Environment env;

    private ApplicationContext applicationContext;

    public void setEnvironment(final Environment env) {
        this.env = env;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public DataSource defaultDataSource() {
        Properties props = new Properties();
        props.put("driverClassName", "com.mysql.jdbc.Driver");
        props.put("url", "jdbc:mysql://localhost:3306/jbf?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        props.put("username", "root");
        props.put("password", "root");
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSourceMap = new HashMap<Object, Object>();
        String[] activeProfiles = env.getActiveProfiles();
        String file = "database";
        if(activeProfiles==null||activeProfiles.length==0) {
            file = "database";
        } else {
            file = file+"-"+activeProfiles[0];
        }
        Properties properties = PropertiesUtil.loadProperties(file);
        Map<String, Map<String, String>> dataSourcePropertiesMap = new HashMap<String, Map<String, String>>();
        Enumeration<Object> keys = properties.keys();
        while(keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            String value = properties.getProperty(key);
            if(key.startsWith("datasource.")) {
                String datasourceName = key.split("\\.")[1];
                Map propertiesMap = dataSourcePropertiesMap.get(datasourceName);
                if(propertiesMap==null) {
                    propertiesMap = new HashMap<String, String>();
                    dataSourcePropertiesMap.put(datasourceName, propertiesMap);
                }
                propertiesMap.put(key.replace("datasource."+datasourceName+".", ""), value);
            }
        }
        for (Map.Entry<String, Map<String, String>> entry : dataSourcePropertiesMap.entrySet()) {
            String dataSourceName = entry.getKey();
            Map<String, String> props = entry.getValue();
            try {
                DataSource ds = DruidDataSourceFactory.createDataSource(props);
                targetDataSourceMap.put(dataSourceName, ds);
                if("default".equals(dataSourceName)) {
                    dataSource.setDefaultTargetDataSource(ds);
                }
                defaultListableBeanFactory.registerSingleton(dataSourceName, ds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dataSource.setTargetDataSources(targetDataSourceMap);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cangzhitao.jbf");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mappings/**/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        pageHelper.setProperties(properties);
        BaseMapperInterceptor baseMapperInterceptor = new BaseMapperInterceptor();
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper, baseMapperInterceptor});
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.cangzhitao.jbf");
        mapperScannerConfigurer.setAnnotationClass(MyBatisMapper.class);
        return mapperScannerConfigurer;
    }


}
