package com.cangzhitao.jbf.codegenerator.domain;

import com.cangzhitao.jbf.codegenerator.enums.JBFColumnType;
import com.cangzhitao.jbf.datasource.domain.BaseDomain;
import com.cangzhitao.jbf.datasource.persistence.annotation.Column;
import com.cangzhitao.jbf.datasource.persistence.annotation.Table;

/**
 * Created by lihui on 2017/5/11.
 */
@Table(name = "jbf_code_generator_showtype")
public class JBFShowType extends BaseDomain {

    @Column(name = "column_type")
    private int columnType = JBFColumnType.BASE.value();

    @Column(name = "column_type_value")
    private String columnTypeValue;

    private String name;

    private String comment;


    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeValue() {
        return columnTypeValue;
    }

    public void setColumnTypeValue(String columnTypeValue) {
        this.columnTypeValue = columnTypeValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
