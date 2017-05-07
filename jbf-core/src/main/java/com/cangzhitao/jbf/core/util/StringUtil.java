package com.cangzhitao.jbf.core.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

public class StringUtil {

	public static String getString(Object o, String defaultStr) {
		if(o!=null) {
			return o.toString();
		}
		return defaultStr;
	}
	
	public static String initialLower(String str) {
        if(str==null||"".equals(str)) {
            return str;
        }
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }

	public static String times(String str, int n) {
		String result = "";
		for(int i=0;i<n;i++) {
			result += str;
		}
		return result;
	}
    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String initialString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        String initialChar = str.substring(0, 1);
        return (initialChar.toUpperCase() + str.substring(1));
    }

    public static String join(Object[] a, String separator) {
        if(a==null||a.length==0) {
            return "";
        }
        if(a.length==1) {
            return a[0]+"";
        }
        String str = a[0]+"";
        for(int i=1;i<a.length;i++) {
            str += separator+a[i];
        }
        return str;
    }

    public static String join(Object[] a, String pre, String end, String separator) {
        if(a==null||a.length==0) {
            return null;
        }
        if(a.length==1) {
            return pre+a[0]+end;
        }
        String str = pre+a[0]+end;
        for(int i=1;i<a.length;i++) {
            str += separator+pre+a[i]+end;
        }
        return str;
    }

    public static String delHtmlTag(String html) {
        /*if (html == null || "".equals(html)) {
            return "";
        }
        String str = html;
        str = str.replaceAll("<[^>]+>", "");
        return str;*/
    	return HtmlUtil.delHTMLTag(html);
    }
    /*
    *//**
     * 数组转换为String
     *
     * @param strs
     * @return
     *//*
    public static String arrayToString(String[] strs) {
        String str = "";
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        str += strs[0];
        for (int i = 1; i < strs.length; i++) {
            str = str + "," + strs[i];
        }
        return str;
    }

    *//**
     * 数组转换为String
     *
     * @param strs
     * @return
     *//*
    public static String arrayToString(long[] strs) {
        String str = "";
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0] + "";
        }
        str += strs[0];
        for (int i = 1; i < strs.length; i++) {
            str = str + "," + strs[i];
        }
        return str;
    }

    *//**
     * 数组转换为String
     *
     * @param strs
     * @return
     *//*
    public static String arrayToString(int[] strs) {
        String str = "";
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0] + "";
        }
        str += strs[0];
        for (int i = 1; i < strs.length; i++) {
            str = str + "," + strs[i];
        }
        return str;
    }

    *//**
     * 数组转换为String
     *
     * @param strs
     * @return
     *//*
    public static String arrayToString(Object strs) {
        if(strs==null) {
            return "";
        }
        if(strs instanceof long[]) {
            return arrayToString((long[])strs);
        } else if(strs instanceof int[]) {
            return arrayToString((int[])strs);
        } else if(strs instanceof String[]) {
            return arrayToString((String[])strs);
        } else {
            return "";
        }

    }*/

    public static String getNotNullString(Object str) {
        if (str == null) {
            return "";
        }
        return str.toString();
    }

    public static String xssFilter(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        return string
                .replaceAll(
                        "(<|>|\'|[(]|[)]|try|catch|xss|&|&|xpression|e-xpression|ression|expRessioN|vbscript|javascript|</script>|%3c|%3e|<>|script|document|eval|;|CR|LF|\"|()|)",
                        "");
    }

    public static String enCodeString(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        String str;
        try {
            str = URLEncoder.encode(string, "UTF-8");
        } catch (Exception e) {
            return string;
        }
        return str;
    }

    public static String deCodeString(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        String str;
        try {
            str = URLDecoder.decode(string, "UTF-8");
        } catch (Exception e) {
            return string;
        }
        return str;
    }


    /*
    public static String escapeHtml(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        String str;
        try {
            str = StringEscapeUtils.escapeHtml(string);
        } catch (Exception e) {
            return string;
        }
        return str;
    }


    public static String unescapeHtml(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        String str;
        try {
            str = StringEscapeUtils.unescapeHtml(string);
        } catch (Exception e) {
            return string;
        }
        return str;
    }
    */
    
    /*
    public static boolean isURLStrValid(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String string = str.toLowerCase();
        for (int i = 0; i < string.length(); i++) {
            if (URLSTR.indexOf(string.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }
    */
    public static boolean isEmail(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        }
        return true;
    }

    /**
     * 替换源字符为目标字符
     * @param str
     * @param escapeCharMap
     * @return
     */
    public static String escapeChar(String str, Map<String, String> escapeCharMap) {
    	if(str==null) {
    		return null;
    	}
        if("".equals(str)) {
            return "";
        }
        String retStr = "";
        for(int i=0;i<str.length();i++) {
            String s = str.charAt(i)+"";
            if(escapeCharMap.get(s)==null) {
                retStr += s;
            } else {
                retStr += escapeCharMap.get(s);
            }
        }
        return retStr;
    }

    /**
     * 将博客代码中的特殊字符进行转义
     * @param source
     * @return
     */
    public static String escapeCode(String source) {
    	if(source==null) {
    		return null;
    	}
    	if("".equals(source)) {
    		return "";
    	}
        Map<String, String> escapeCharMap = new HashMap<String, String>();
        escapeCharMap.put("<", "&lt;");
        escapeCharMap.put(">", "&gt;");
        escapeCharMap.put("&", "&amp;");
        escapeCharMap.put("\"", "&quot;");
        return escapeChar(source, escapeCharMap);
    }

    /**
     * 将html内容中的代码块进行转义
     * @param source
     * @return
     */
    public static String escapeCodeChar(String source) {
        String reg = "<[pP][rR][eE]\\sclass(.*?)=(.*?)['\"][bB][rR][uU][sS][hH](.*?)['\"].*?>([\\s\\S]*?)</[pP][rR][eE]>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(4);
            String target = escapeCode(r);
            source = source.replace(r, target);
        }
        return source;
    }
    
    public static boolean isNotBlank(String source) {
    	if(source==null) {
    		return false;
    	}
    	String temp = source.trim();
    	return !"".equals(temp);
    }
    
    public static boolean isNotBlank(CharSequence source) {
    	if(source==null) {
    		return false;
    	}
    	String temp = source.toString().trim();
    	return !"".equals(temp);
    }
    
    public static String escapeHtml(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        String str;
        try {
            str = StringEscapeUtils.escapeHtml(string);
        } catch (Exception e) {
            return string;
        }
        return str;
    }


    public static String unescapeHtml(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        String str;
        try {
            str = StringEscapeUtils.unescapeHtml(string);
        } catch (Exception e) {
            return string;
        }
        return str;
    }
    
    public static boolean contains(String arrayString, String element) {
    	if(arrayString==null) {
    		return false;
    	}
    	String[] array = arrayString.split(",");
    	for(int i=0;i<array.length;i++) {
    		if(array[i].equals(element)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static final String[] staticResourceSuffixs = new String[]{
    		"jpg",
    		"jpeg",
    		"png",
    		"gif",
    		"bmp",
    		"js",
    		"css",
    		"zip",
    		"rar",
    		"doc",
    		"docx",
    		"txt"
    };
    
    public static boolean isStaticResource(String url) {
    	if(url.startsWith("/static/")||url.startsWith("/public/")||url.startsWith("/resources/")) {
    		return true;
    	}
    	url = url.toLowerCase();
    	for(int i=0;i<staticResourceSuffixs.length;i++) {
    		if(url.endsWith(staticResourceSuffixs[i])) {
    			return true;
    		}
    	}
    	return false;
    }
}
