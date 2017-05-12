package com.cangzhitao.jbf.codegenerator.domain;

import com.cangzhitao.jbf.datasource.domain.BaseDomain;
import com.cangzhitao.jbf.datasource.persistence.annotation.Column;
import com.cangzhitao.jbf.datasource.persistence.annotation.Table;

/**
 * Created by lihui on 2017/5/11.
 */
@Table(name = "jbf_code_generator_entity")
public class JBFEntity extends BaseDomain {

    @Column(name = "class_name")
    private String className;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "parent_class")
    private String parentClass;

    @Column(name = "request_mapping")
    private String requestMapping;

    @Column(name = "output_project")
    private String outputProject;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public String getOutputProject() {
        return outputProject;
    }

    public void setOutputProject(String outputProject) {
        this.outputProject = outputProject;
    }
}
