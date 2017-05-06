package com.cangzhitao.jbf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lihui on 2017/5/6.
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.cangzhitao.jbf"})
public class JBFExampleApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JBFExampleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JBFExampleApplication.class, args);
    }

}
