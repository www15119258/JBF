package com.cangzhitao.jbf.datasource.domain;

import com.cangzhitao.jbf.datasource.persistence.annotation.ID;
import com.cangzhitao.jbf.datasource.util.DomainUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 2017/5/7.
 */
public class BaseDomain {

    @ID
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getFields() {
        return DomainUtil.getFields(this.getClass());
    }

    public List<String> getProps() {
        return DomainUtil.getProps(this.getClass());
    }

    public String getIdField() {
        return DomainUtil.getIdField(this.getClass());
    }

    public String getIdProp() {
        return DomainUtil.getIdProp(this.getClass());
    }






}
