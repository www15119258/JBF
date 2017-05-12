package com.cangzhitao.jbf.datasource.util;

import com.cangzhitao.jbf.core.util.ReflectUtil;
import com.cangzhitao.jbf.datasource.persistence.annotation.Column;
import com.cangzhitao.jbf.datasource.persistence.annotation.ID;
import com.cangzhitao.jbf.datasource.persistence.annotation.Table;
import com.cangzhitao.jbf.datasource.persistence.annotation.Transient;
import com.sun.java.browser.plugin2.DOM;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lihui on 2017/5/7.
 */
public class DomainUtil {

    private volatile static ConcurrentMap<String, List<String>> domainPropsMap = new ConcurrentHashMap<String, List<String>>();

    private volatile static ConcurrentMap<String, List<String>> domainFieldsMap = new ConcurrentHashMap<String, List<String>>();

    private volatile static ConcurrentMap<String, String> domainIdPropMap = new ConcurrentHashMap<String, String>();

    private volatile static ConcurrentMap<String, String> domainIdFieldMap = new ConcurrentHashMap<String, String>();

    private volatile static ConcurrentMap<String, String> domainTableNameMap = new ConcurrentHashMap<String, String>();

    public volatile static ConcurrentMap<String, Map<String, Object>> domainMateDataMap = new ConcurrentHashMap<String, Map<String, Object>>();

    public static Map<String, Object> getMateData(Class c) {
        Map<String, Object> mateData = domainMateDataMap.get(c.getName());
        if(mateData==null) {
            synchronized (DomainUtil.class) {
                mateData = domainMateDataMap.get(c.getName());
                if(mateData!=null) {
                    return mateData;
                }
                mateData = new HashMap<String, Object>();
                String tableName = null;
                Table table = c.getAnnotation(Table.class)!=null?(Table) c.getAnnotation(Table.class):null;
                if(table==null) {
                    tableName = c.getSimpleName();
                } else {
                    tableName = table.name();
                    if(tableName==null||"".equals(tableName)) {
                        tableName = c.getSimpleName();
                    }
                }
                mateData.put("__table_name__", tableName);

                List<Map<String, String>> filedList = new ArrayList<Map<String, String>>();
                Map<String, Field> fieldsMap = ReflectUtil.getAllFields(c, true);
                Collection<Field> fields = fieldsMap.values();
                Iterator<Field> it = fields.iterator();
                ID idAn = null;
                while(it.hasNext()) {
                    Field field = it.next();
                    if(idAn==null) {
                        idAn = field.getAnnotation(ID.class);
                        if(idAn!=null) {
                            Map<String, String> fieldMap = new HashMap<String, String>();
                            String idField = idAn.name();
                            if(idField==null||"".equals(idField)) {
                                idField = field.getName();
                            }
                            fieldMap.put("field", idField);
                            fieldMap.put("prop", field.getName());
                            mateData.put("__id__", fieldMap);
                            continue;
                        }
                    }
                    Transient t = field.getAnnotation(Transient.class);
                    if(t!=null) {
                        continue;
                    }
                    Column column = field.getAnnotation(Column.class);
                    Map<String, String> fieldMap = new HashMap<String, String>();
                    String fieldName;
                    if(column==null) {
                        fieldName = field.getName();
                    } else {
                        fieldName = column.name();
                        if(fieldName==null||"".equals(fieldName)) {
                            fieldName = field.getName();
                        }
                    }
                    fieldMap.put("field", fieldName);
                    fieldMap.put("prop", field.getName());
                    filedList.add(fieldMap);
                }
                if(idAn==null) {
                    throw new RuntimeException("没有定义ID");
                }
                mateData.put("__field_list__", filedList);
                domainMateDataMap.put(c.getName(), mateData);
            }
        }
        return mateData;
    }

    public static String getTableName(Class c) {
        String tableName = domainTableNameMap.get(c.getName());
        if(tableName==null) {
            synchronized (DomainUtil.class) {
                tableName = domainTableNameMap.get(c.getName());
                if(tableName!=null) {
                    return tableName;
                }
                Table table = c.getClass().getAnnotation(Table.class);
                if(table==null) {
                    tableName = c.getSimpleName();
                } else {
                    tableName = table.name();
                    if(tableName==null||"".equals(tableName)) {
                        tableName = c.getSimpleName();
                    }
                }
                domainTableNameMap.put(c.getName(), tableName);
            }
        }
        return tableName;
    }

    public static String getIdProp(Class c) {
        String id = domainIdPropMap.get(c.getName());
        if(id==null) {
            synchronized (DomainUtil.class) {
                id = domainIdPropMap.get(c.getName());
                if(id!=null) {
                    return id;
                }
                Map<String, Field> fieldsMap = ReflectUtil.getAllFields(c, true);
                Collection<Field> fields = fieldsMap.values();
                Iterator<Field> it = fields.iterator();
                ID idAn = null;
                while(it.hasNext()) {
                    Field field = it.next();
                    idAn = field.getAnnotation(ID.class);
                    if(idAn!=null) {
                        id = field.getName();
                        domainIdPropMap.put(c.getName(), id);
                        break;
                    }
                }
                if(idAn==null) {
                    throw new RuntimeException("没有定义ID");
                }
            }
        }
        return id;
    }

    public static String getIdField(Class c) {
        String id = domainIdFieldMap.get(c.getName());
        if(id==null) {
            synchronized (DomainUtil.class) {
                id = domainIdFieldMap.get(c.getName());
                if(id!=null) {
                    return id;
                }
                Map<String, Field> fieldsMap = ReflectUtil.getAllFields(c, true);
                Collection<Field> fields = fieldsMap.values();
                Iterator<Field> it = fields.iterator();
                ID idAn = null;
                while(it.hasNext()) {
                    Field field = it.next();
                    idAn = field.getAnnotation(ID.class);
                    if(idAn!=null) {
                        Column column = field.getAnnotation(Column.class);
                        if(column==null) {
                            id = field.getName();
                        } else {
                            id = column.name();
                            if(id==null||"".equals(id)) {
                                id = field.getName();
                            }
                        }
                        domainIdFieldMap.put(c.getName(), id);
                        break;
                    }
                }
                if(idAn==null) {
                    throw new RuntimeException("没有定义ID");
                }
            }
        }
        return id;
    }

    public static List<String> getProps(Class c) {
        List<String> propsList = domainPropsMap.get(c.getName());
        if(propsList==null) {
            synchronized(DomainUtil.class) {
                propsList = domainPropsMap.get(c.getName());
                if(propsList!=null) {
                    return propsList;
                }
                propsList = new ArrayList<String>();

                Map<String, Field> fieldsMap = ReflectUtil.getAllFields(c, true);
                Collection<Field> fields = fieldsMap.values();
                Iterator<Field> it = fields.iterator();
                while(it.hasNext()) {
                    Field field = it.next();
                    ID id = field.getAnnotation(ID.class);
                    if(id!=null) {
                        continue;
                    }
                    Transient t = field.getAnnotation(Transient.class);
                    if(t!=null) {
                        continue;
                    }
                    propsList.add(field.getName());
                }
                domainPropsMap.put(c.getName(), propsList);
            }
        }
        return propsList;
    }

    public static List<String> getFields(Class c) {
        List<String> fieldsList = domainFieldsMap.get(c.getName());
        if(fieldsList==null) {
            synchronized(DomainUtil.class) {
                fieldsList = domainFieldsMap.get(c.getName());
                if(fieldsList!=null) {
                    return fieldsList;
                }
                fieldsList = new ArrayList<String>();

                Map<String, Field> fieldsMap = ReflectUtil.getAllFields(c, true);
                Collection<Field> fields = fieldsMap.values();
                Iterator<Field> it = fields.iterator();
                while(it.hasNext()) {
                    Field field = it.next();
                    ID id = field.getAnnotation(ID.class);
                    if(id!=null) {
                        continue;
                    }
                    Transient t = field.getAnnotation(Transient.class);
                    if(t!=null) {
                        continue;
                    }
                    Column column = field.getAnnotation(Column.class);
                    if(column==null) {
                        fieldsList.add(field.getName());
                    } else {
                        String name = column.name();
                        if(name==null||"".equals(name)) {
                            fieldsList.add(field.getName());
                        } else {
                            fieldsList.add(name);
                        }
                    }
                }
                domainFieldsMap.put(c.getName(), fieldsList);
            }
        }
        return fieldsList;
    }

}
