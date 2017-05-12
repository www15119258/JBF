package com.cangzhitao.jbf.security.domain;

import com.cangzhitao.jbf.datasource.domain.BaseDomain;
import com.cangzhitao.jbf.datasource.persistence.annotation.Table;

/**
 * Created by lihui on 2017/5/6.
 */
@Table(name = "jbf_user")
public class User extends BaseDomain {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
