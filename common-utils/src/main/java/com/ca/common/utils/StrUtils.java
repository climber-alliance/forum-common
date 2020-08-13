package com.ca.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * forum-common
 *
 * <p> 字符串处理工具类 </p>
 *
 * @author zz
 * @version StrUtils.java 2020.06.27
 **/

public class StrUtils {

    public static Logger LOG = LoggerFactory.getLogger(StrUtils.class);

    public String ArrayToString(String[] strArray, String splitFlag) {
        String tmpString = "";
        if (strArray.length == 0) {
            tmpString = "";
        } else {
            for (int i = 0; i < strArray.length; i++) {
                tmpString = tmpString + strArray[i];
                if (i < strArray.length - 1) {
                    tmpString = tmpString + splitFlag;
                }
            }
        }
        tmpString = tmpString.trim();
        return tmpString;
    }

    public String ArrayToString2(String[] strArray, String splitFlag) {
        String tmpString = "";
        if (strArray.length == 0) {
            tmpString = "''";
        } else {
            for (int i = 0; i < strArray.length; i++) {
                tmpString = tmpString + "'" + strArray[i] + "'";
                if (i < strArray.length - 1) {
                    tmpString = tmpString + splitFlag;
                }
            }
        }
        tmpString = tmpString.trim();
        return tmpString;
    }

    public static String getFileExt(String fileName) {
        String value = new String();
        int start = 0;
        int end = 0;
        if (fileName == null)
            return null;
        start = fileName.lastIndexOf(46) + 1;
        end = fileName.length();
        value = fileName.substring(start, end);
        if (fileName.lastIndexOf(46) > 0)
            return value;
        else
            return "";
    }

    public boolean isNumeric(String strData, boolean dotFlag) {
        if (strData == null) {
            return false;
        }
        char[] numbers = strData.toCharArray();
        for (int i = 0; i < numbers.length; i++) {
            if (dotFlag) {
                if (!Character.isDigit(numbers[i]))
                    return false;
            } else {
                if (!Character.isDigit(numbers[i]) && numbers[i] != '.')
                    return false;
            }
        }
        if (strData.lastIndexOf(46) != strData.indexOf(46))
            return false;
        return true;
    }

    public static String changeChinese(String chnString) {
        String strChinese = null;
        byte[] temp;
        if (chnString == null || chnString == "") {
            return new String("");
        }
        try {
            temp = chnString.getBytes("ISO-8859-1");
            strChinese = new String(temp);
        } catch (java.io.UnsupportedEncodingException e) {
            System.out.println(e);
        }
        return strChinese;
    }

    public static String changeSybase(String chnString) {
        String strChinese = null;
        byte[] temp;
        if (chnString == null || chnString == "") {
            return new String("");
        }
        try {
            temp = chnString.getBytes("gb2312");
            strChinese = new String(temp);
        } catch (java.io.UnsupportedEncodingException e) {
            System.out.println(e);
        }
        return strChinese;
    }

    public static String Replace(String source, String oldString,
                                 String newString) {
        StringBuffer output = new StringBuffer();

        int lengthOfSource = source.length();
        int lengthOfOld = oldString.length();

        int posStart = 0;
        int pos;

        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));

            output.append(newString);
            posStart = pos + lengthOfOld;
        }
        if (posStart < lengthOfSource) {
            output.append(source.substring(posStart));
        }
        return output.toString();
    }

    public static String Left(String sourceString, int nLength) {
        if (sourceString == null || sourceString == ""
                || sourceString.length() <= nLength) {
            return sourceString;
        }
        return sourceString.substring(0, nLength);
    }

    public static String Reverse(String strReverse) {
        if (strReverse == null) {
            return strReverse;
        } else {
            StringBuffer tmpString = new StringBuffer(strReverse);

            tmpString = tmpString.reverse();

            return tmpString.toString();
        }
    }

    public static String Right(String sourceString, int nLength) {
        if (sourceString == null || sourceString == ""
                || sourceString.length() <= nLength) {
            return sourceString;
        }
        return sourceString.substring(sourceString.length() - nLength,
                sourceString.length());
    }

    public static String Mid(String sourceString, int nStart, int nLength) {
        try {
            if (sourceString == null || sourceString == "") {
                return sourceString;
            }
            int Length = sourceString.length();
            if (nStart > Length || nStart < 0) {
                return null;
            }
            if ((nStart + nLength) > Length)
                return sourceString.substring(nStart, Length);
            return sourceString.substring(nStart, nStart + nLength);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 判断字符串是否为:null,空串,'null'
     *
     * @param s
     * @return boolean
     */
    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s.trim()) || "null".equals(s.trim()) || "\"null\"".equals(s.trim())) {
            return true;
        }

        return false;
    }

    /**
     * 校验集合为空
     *
     * @param <T>
     *
     * @param list
     * @return true 空 ,false 对象或者集合非空
     */
    public static <T> boolean isEmpty(List<T> list) {
        return null == list || list.isEmpty();
    }

    public static String toSql(String str) {
        String sql = new String(str);
        return Replace(sql, "'", "''");
    }

    public static String toHtmlInput(String str) {
        if (str == null)
            return null;

        String html = new String(str);

        html = Replace(html, "&", "&amp;");
        html = Replace(html, "<", "&lt;");
        html = Replace(html, ">", "&gt;");

        return html;
    }

    public static String toHtml(String str) {
        if (str == null) {
            return null;
        }

        String html = new String(str);

        html = toHtmlInput(html);
        html = Replace(html, "\r\n", "\n");
        html = Replace(html, "\n", "<br>");
        html = Replace(html, "\t", "    ");
        html = Replace(html, " ", " &nbsp;");

        return html;
    }

    public static String notEmpty(Object value) {
        if (value == null) {
            value = "";
        }
        return String.valueOf(value);
    }

    public static boolean isNotEmpty(Object value) {
        if (value == null || value.equals("")) {
            return false;
        }
        return true;
    }

    public static String get(Map map, String keyName) {
        return notEmpty(map.get(keyName));
    }

    /**
     *
     * @param sql
     * @param params
     */
    public String getSql(String sql, Object[] params) {
        StrUtils str = new StrUtils();
        int idx = -1;
        int i = 0;
        String tmp = sql;
        while ((idx = tmp.indexOf("?")) > -1) {
            tmp = tmp.replaceFirst("\\?", "'" + str.toSql((String) params[i++])
                    + "'");
        }
        return tmp;
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static String ListToString(List list, String key, String split) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            str += get(map, key) + split;
        }
        if (!"".equals(str))
            str = str.substring(0, str.lastIndexOf(split));
        return str;

    }

    public static String listToStringWithDistinct(List list, String key,
                                                  String split) {
        String str = "";
        List<String> lst = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            String value = get(map, key);
            if (!lst.contains(value) && !value.equals("")) {
                lst.add(value);
                str += value + split;
            }
        }
        if (!"".equals(str))
            str = str.substring(0, str.lastIndexOf(split));
        else
            str = "''";
        return str;
    }

    public static String listToStringWithDistinct2(List list, String key,
                                                   String split) {
        String str = "";
        List<String> lst = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            String value = get(map, key);
            if (!lst.contains(value) && !value.equals("")) {
                lst.add(value);
                str += value + split;
            }
        }
        if (!"".equals(str))
            str = str.substring(0, str.lastIndexOf(split));
        else
            str = "";
        return str;
    }

/*
    public static List distinctList(List list, final String key,
                                    final String orderKey) {
        List<Map> lst = (List) ObjectHelper.byteClone(list);
        if (orderKey != null)
            Collections.sort(lst, new Comparator<Map>() {
                public int compare(Map m1, Map m2) {
                    if (m1.get(orderKey) instanceof Number) {
                        BigDecimal n1 = (BigDecimal) m1.get(orderKey);
                        BigDecimal n2 = (BigDecimal) m1.get(orderKey);
                        return n1.compareTo(n2);
                    } else {
                        String s1 = get(m1, orderKey);
                        String s2 = get(m2, orderKey);
                        return s1.compareTo(s2);
                    }
                }
            });
        String temp = null;
        for (Iterator<Map> iter = lst.iterator(); iter.hasNext();) {
            Map mapCur = iter.next();
            String curValue = get(mapCur, key);
            if (curValue.equals(temp))
                iter.remove();
            else
                temp = curValue;
        }
        return lst;
    }
*/

    public static String toUTF8(String str) {
        String s = str;
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) > '\177') {
                    result.append("&#x");
                    String hex = Integer.toHexString(s.charAt(i));
                    StringBuffer hex4 = new StringBuffer(hex);
                    hex4.reverse();
                    int len = 4 - hex4.length();
                    for (int j = 0; j < len; j++)
                        hex4.append('0');

                    for (int j = 0; j < 4; j++)
                        result.append(hex4.charAt(3 - j));

                    result.append(';');
                } else {
                    result.append(s.charAt(i));
                }

            return result.toString();
        } catch (Exception e) {
            return s;
        }
    }

    public static String encoderByMd5(String str)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] unencodedPassword = str.getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return str;
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String convertFileSize(long filesize) {
        String strUnit = "B";
        String strAfterComma = "";
        int intDivisor = 1;
        if (filesize >= 1024 * 1024) {
            strUnit = "MB";
            intDivisor = 1024 * 1024;
        } else if (filesize >= 1024) {
            strUnit = "KB";
            intDivisor = 1024;
        }
        if (intDivisor == 1)
            return filesize + strUnit;

        strAfterComma = "" + 100 * (filesize % intDivisor) / intDivisor;
        if (strAfterComma == "")
            strAfterComma = ".0";
        return filesize / intDivisor + "." + strAfterComma + strUnit;
    }

    /**
     * 将字符转数组
     *
     * @param str
     * @return
     */
    public static String[] toArr(String str) throws Exception {
        String inStr = str;
        String a[] = null;
        try {
            if (null != inStr) {
                StringTokenizer st = new StringTokenizer(inStr, ",");
                if (st.countTokens() > 0) {
                    a = new String[st.countTokens()];
                    int i = 0;
                    while (st.hasMoreTokens()) {
                        a[i++] = st.nextToken();
                    }
                }
            }
        } catch (Exception e) {

        }
        return a;
    }

    /**
     * 在数组中查找字符串
     *
     * @param params
     * @param name
     * @param ignoreCase
     * @return
     */
    public static int indexOf(String[] params, String name, boolean ignoreCase) {
        if (params == null)
            return -1;
        for (int i = 0, j = params.length; i < j; i++) {
            if (ignoreCase && params[i].equalsIgnoreCase(name)) {
                return i;
            } else if (params[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 检测字符串长度 中文两个字节，英文一个字节
     *
     * @param value
     * @return
     */
    public static int stringLength(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * Object 转 String 方法
     *
     * @param val
     * @param defaultValue
     *            默认字符串
     * @return
     */
    public static String objToString(Object val, String defaultValue) {
        if (null == val)
            return defaultValue;
        try {
            return String.valueOf(val.toString());
        } catch (Exception e) {
            // LOG.error(val + " 无法转换为Integer! ", e);
        }
        return null;
    }

    /**
     * Object 转 Integer 方法
     *
     * @param val
     * @return
     */
    public static Integer objToInteger(Object val) {

        try {
            return Integer.parseInt(val.toString());
        } catch (Exception e) {
            // LOG.error(val + " 无法转换为Integer! ", e);
        }
        return null;
    }

    /**
     * 生成指定长度的随机串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * Object 转 Int 方法
     *
     * @param val
     * @param defaultValue
     * @return
     */
    public static int objToInt(Object val, int defaultValue) {
        if (val == null)
            return defaultValue;
        try {
            if (isEmpty(val.toString()))
                return defaultValue;
            String valStr = val.toString();
            if (val.toString().startsWith("-")) {
                valStr = valStr.substring(1, valStr.length());
            }
            if (StringUtils.isNumeric(valStr)) {
                return Integer.parseInt(val.toString());
            }
        } catch (Exception e) {
            // LOG.error(val + " 无法转换为int! ", e);
        }
        return defaultValue;
    }

    /**
     * obj To String
     *
     * @param val
     * @return
     */
    public static String objToStr(Object val) {
        return null == val ? "" : String.valueOf(val);
    }

    /**
     * object 转 Double ，异常则返回 0
     *
     * @param obj
     * @return
     */
    public static Double objToDouble(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验手机号码
     *
     * @param tel
     * @return
     */
    public static boolean checkTel(String tel) {
        if (isEmpty(tel))
            return false;
        String mobilephone =  "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern = Pattern.compile(mobilephone);
        Matcher matcher = pattern.matcher(tel);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkTel("13100000001"));
    }

}
