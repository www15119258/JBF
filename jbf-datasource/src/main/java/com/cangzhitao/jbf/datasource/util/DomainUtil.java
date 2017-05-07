package com.cangzhitao.jbf.datasource.util;

import com.cangzhitao.jbf.core.util.ReflectUtil;
import com.cangzhitao.jbf.datasource.persistence.annotation.Column;
import com.cangzhitao.jbf.datasource.persistence.annotation.ID;
import com.cangzhitao.jbf.datasource.persistence.annotation.Transient;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by lihui on 2017/5/7.
 */
public class DomainUtil {

    private volatile static Map<String, List<String>> domainPropsMap = new HashMap<String, List<String>>();

    private volatile static Map<String, List<String>> domainFieldsMap = new HashMap<String, List<String>>();

    private volatile static Map<String, String> domainIdPropMap = new HashMap<String, String>();

    private volatile static Map<String, String> domainIdFieldMap = new HashMap<String, String>();

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
