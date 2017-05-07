package com.cangzhitao.jbf.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cangzhitao.jbf.core.tag.CodeTag;
import com.cangzhitao.jbf.core.tag.EmTag;
import com.cangzhitao.jbf.core.tag.PreTag;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.util.NodeList;



public class HtmlUtil {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
    
    private static final String regEx_img = "<img\\s[^>]+/>"; // 定义HTML标签的正则表达式
    
    public static String delImgTag(String htmlStr) {
    	if(htmlStr==null||"".equals(htmlStr)) {
    		return "";
    	}
    	Pattern p_html = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        return htmlStr.trim();
    }
    
    /**
     * @param htmlStr
     * @return
     *  删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
    	if(htmlStr==null||"".equals(htmlStr)) {
    		return "";
    	}
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        
        htmlStr = htmlStr.replaceAll("　",  "");
        return htmlStr.trim(); // 返回文本字符串
    }
    
    public static String getTextFromHtml(String htmlStr){
    	htmlStr = delHTMLTag(htmlStr);
//    	htmlStr = htmlStr.replaceAll("&nbsp;", "");
//    	htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
    	return htmlStr;
    }
    
    public static void main(String[] args) {
    	String str = "<p><span style=\"color:rgb(0, 0, 0); font-family:微软雅黑,verdana,sans-serif,宋体; font-size:14px\">这一节将作为C-DRAWS的结束，主要讲下复制、粘贴，打开，保存。</span></p><p><span style=\"color:rgb(0, 0, 0); font-family:微软雅黑,verdana,sans-serif,宋体; font-size:14px\">如果没有选择框的话，将复制整个画布，否则将复制选择框里面的内容。 </span></p><p><span style=\"color:rgb(0, 0, 0); font-family:微软雅黑,verdana,sans-serif,宋体; font-size:14px\">剪切的话，这里其实跟复制是一样的，只是多了一步操作，用画布颜色一样的矩形将选择框填充。</span></p>";
		System.out.println(getTextFromHtml(str));
	}
    
    public static void registerTags(Parser parser) {   
        // 注册自定义的新结点解析器,这是必要的...   
    	PrototypicalNodeFactory factory = new PrototypicalNodeFactory();   
    	factory.registerTag(new PreTag());
    	factory.registerTag(new EmTag());
    	factory.registerTag(new CodeTag());
    	parser.setNodeFactory(factory);   
	}  
    
    /**
     * 补齐缺失的html标签，不包含html标签时直接返回
     * @param htmlStr
     * @return
     */
    public static String fixUpHTML(String htmlStr) {
    	if(htmlStr==null||"".equals(htmlStr)) {
    		return "";
    	}
    	try {
    		Parser parser = Parser.createParser(htmlStr, "UTF-8");
    		registerTags(parser);
        	NodeList nodelist = parser.extractAllNodesThatMatch(new NodeFilter() {   
				private static final long serialVersionUID = -1354302983867298283L;
				public boolean accept(Node node) {
					if (node instanceof CompositeTag) {
						return true;
					}
					return false;
				}
            });  
        	String str = "";   
            String tmp = "";   
            if(nodelist.size()==0) {
            	return htmlStr;
            }
            for (int i = 0; i < nodelist.size(); i++) {   
            	Node node = nodelist.elementAt(i);
            	if(node instanceof CompositeTag) {
            		CompositeTag testTag = (CompositeTag) node;   
    				if (testTag.getParent() == null) {
    			        tmp = testTag.toHtml();   
    				    str += tmp + "\n";
    				}
            	}
            }   
            htmlStr = str;
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return htmlStr;
    }
}
