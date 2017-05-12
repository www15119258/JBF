package com.cangzhitao.jbf.codegenerator.domain;

import com.cangzhitao.jbf.codegenerator.enums.JBFColumnType;
import com.cangzhitao.jbf.datasource.domain.BaseDomain;
import com.cangzhitao.jbf.datasource.persistence.annotation.Column;
import com.cangzhitao.jbf.datasource.persistence.annotation.Table;

/**
 * Created by lihui on 2017/5/11.
 */
@Table(name = "jbf_code_generator_column")
public class JBFColumn extends BaseDomain {

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "showtype_id")
    private Long showTypeId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "column_name")
    private String columnName;

    private String comment;

    @Column(name = "column_type")
    private int columnType = JBFColumnType.BASE.value();

    @Column(name = "column_type_value")
    private String columnTypeValue;

    @Column(name = "date_time_format")
    private String dateTimeFormat;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "is_optional")
    private Boolean isOptional = true;

    @Column(name = "is_jsongnore")
    private Boolean isJsonIgnore = false;

    @Column(name = "is_multiple")
    private Boolean isMultiple = false;

    @Column(name = "is_lob")
    private Boolean isLob = false;


    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getShowTypeId() {
        return showTypeId;
    }

    public void setShowTypeId(Long showTypeId) {
        this.showTypeId = showTypeId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

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

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getOptional() {
        return isOptional;
    }

    public void setOptional(Boolean optional) {
        isOptional = optional;
    }

    public Boolean getJsonIgnore() {
        return isJsonIgnore;
    }

    public void setJsonIgnore(Boolean jsonIgnore) {
        isJsonIgnore = jsonIgnore;
    }

    public Boolean getMultiple() {
        return isMultiple;
    }

    public void setMultiple(Boolean multiple) {
        isMultiple = multiple;
    }

    public Boolean getLob() {
        return isLob;
    }

    public void setLob(Boolean lob) {
        isLob = lob;
    }
}
