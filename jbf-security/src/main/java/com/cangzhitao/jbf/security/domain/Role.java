package com.cangzhitao.jbf.security.domain;

import com.cangzhitao.jbf.datasource.domain.BaseDomain;
import com.cangzhitao.jbf.datasource.persistence.annotation.Table;

/**
 * Created by lihui on 2017/5/6.
 */
@Table(name = "jbf_role")
public class Role extends BaseDomain {

    private String rolename;


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
