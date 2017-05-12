package com.cangzhitao.jbf.datasource.domain;

import com.cangzhitao.jbf.datasource.persistence.annotation.Column;
import com.cangzhitao.jbf.datasource.persistence.annotation.ID;
import com.cangzhitao.jbf.datasource.util.DomainUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihui on 2017/5/7.
 */
public class BaseDomain {

    @ID
    private Long id;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_date")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        if(createDate==null) {
            createDate = new Date();
        }
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        if(updateDate==null) {
            updateDate = new Date();
        }
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
