package com.cangzhitao.jbf.codegenerator.enums;

/**
 * Created by lihui on 2017/5/11.
 */
public enum JBFColumnType {

    /**
     * java基础数据类型
     */
    BASE(0),

    /**
     * 字典类型
     */
    DICT(1),

    /**
     * 复合类型
     */
    TABLE(2),
    ;

    private int code;

    private JBFColumnType(int code) {
        this.code = code;
    }

    public String toString() {
        return String.valueOf(code);
    }

    public int value() {
        return code;
    }

    public static JBFColumnType getEnum(int code) {
        for(JBFColumnType s:JBFColumnType.class.getEnumConstants()) {
            if(s.code == code) {
                return s;
            }
        }
        return null;
    }

}
