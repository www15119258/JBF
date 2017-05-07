package com.cangzhitao.jbf.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReflectUtil {
	
	public static Map<String, Field> getAllFields(Class<? extends Object> c, boolean includeParent) {
		List<Class<? extends Object>> classList = new ArrayList<Class<? extends Object>>();
		Class<? extends Object> temp = c;
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		if(includeParent) {
			while(temp!=Object.class) {
				classList.add(temp);
				temp = temp.getSuperclass();
			}
		} else {
			classList.add(temp);
		}
		for(int i=classList.size()-1;i>=0;i--) {
			temp = classList.get(i);
			Field[] fields = temp.getDeclaredFields();
			for(int j=0;j<fields.length;j++) {
				Field field = fields[j];
				if(fieldMap.get(field.getName())==null) {
                    fieldMap.put(field.getName(), field);
                }
			}
		}
		return fieldMap;
	}

	public static Map<String, Field> getAllFields(Class<? extends Object> c) {
		return getAllFields(c, false);
	}
	
	public static Object setField(Object target, String fieldName, Object value) {
        Class<? extends Object> clazz = target.getClass();
        try {
            Field field = getAllFields(target.getClass()).get(fieldName);
            if(field==null) {
            	return target;
            }
            boolean isAccess = field.isAccessible();
            if(isAccess) {
                field.set(target, value);
            } else {
                Method method = null;
                try {
                    method = clazz.getDeclaredMethod("set" + StringUtil.initialString(fieldName), value.getClass());
                } catch(Exception e) {
                }
                if(method!=null) {
                    method.invoke(target, value);
                    return target;
                }


                field.setAccessible(true);
                field.set(target, value);
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

    public static Object getFiled(Object target, String fieldName) {
        Class<? extends Object> clazz = target.getClass();
        try {
            Field field = getAllFields(target.getClass()).get(fieldName);
            if(field==null) {
            	return null;
            }
            boolean isAccess = field.isAccessible();
            if(isAccess) {
                return field.get(target);
            } else {
                Method method = null;
                try {
                    method = clazz.getDeclaredMethod("get" + StringUtil.initialString(fieldName));
                } catch(Exception e) {
                }
                if(method!=null) {
                    Object o = null;
                    o = method.invoke(target);
                    return o;
                }
                field.setAccessible(true);
                Object o = field.get(target);
                field.setAccessible(false);
                return o;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static void main(String[] args) {


	}
	
}
