package com.im.zemel.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/2/27
 * \* Description:解析出img和文字字符
 * \
 */

public class PatternArticleUntil {
    private static final int CONTENTLENTH = 120;//AbstractContent的长度
    //    注：这是Java正则表达式去除html标签方法。
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签
    private static final String regEx_nbsp = "&nbsp;";//定义所有w标签
    private static final String regImg = "<img src=.*?>";//找出img标签

    public static String findImg(String htmlStr) {
        Pattern p_w = Pattern.compile(regImg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p_w.matcher(htmlStr);
        if (matcher.find()) {

            String url = matcher.group(0);
            url = url.replace("<img src=\"", "");
            url = url.replace("\">", "");
            if (url.charAt(0) != 'h')
                url = "http:" + url;
            return url;

        }
        return null;

    }

    public static String delHTMLTag(String htmlStr) {
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签


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

        Pattern p_nbsp = Pattern.compile(regEx_nbsp, Pattern.CASE_INSENSITIVE);
        Matcher m_nbsp = p_nbsp.matcher(htmlStr);
        htmlStr = m_nbsp.replaceAll(""); // 过滤空格回车标签

        htmlStr = htmlStr.replaceAll(" ", ""); //过滤
        return htmlStr.trim(); // 返回文本字符串
    }

    public static String getAbstractContent(String content) {
        content = delHTMLTag(content);
        if (content.length() > CONTENTLENTH) {
            return content.substring(0, CONTENTLENTH) + "...";

        } else {
            return content;

        }
    }
}

