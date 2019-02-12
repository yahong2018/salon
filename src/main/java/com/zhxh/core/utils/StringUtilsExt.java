package com.zhxh.core.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.Iterator;

public class StringUtilsExt {
    private static MessageDigest md5 = null;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getMd5(String str) {
        if(StringUtils.isEmpty(str)) {
        	str = "";
        }
    	
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for (byte x : bs) {
            if ((x & 0xff) >> 4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }

    public static String joinWrap(Object[] array, String separator, String pre, String post) {
        return array == null ? null : joinWrap(array, separator, 0, array.length, pre, post);
    }

    public static String joinWrap(Object[] array, String separator, int startIndex, int endIndex, String pre, String post) {
        if (array == null) {
            return null;
        } else {
            if (separator == null) {
                separator = "";
            }

            int bufSize = endIndex - startIndex;
            if (bufSize <= 0) {
                return "";
            } else {
                bufSize *= (array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length();
                StringBuffer buf = new StringBuffer(bufSize);

                for (int i = startIndex; i < endIndex; ++i) {
                    if (i > startIndex) {
                        buf.append(separator);
                    }

                    if (array[i] != null) {
                        buf.append(pre);
                        buf.append(array[i]);
                        buf.append(post);
                    }
                }
                return buf.toString();
            }
        }
    }

    public static String joinWrap(Collection collection, String separator, String pre, String post) {
        return collection == null ? null : joinWrap(collection.iterator(), separator, pre, post);
    }

    public static String joinWrap(Iterator iterator, String separator, String pre, String post) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return pre + ObjectUtils.toString(first) + post;
            } else {
                StringBuffer buf = new StringBuffer(256);
                if (first != null) {
                    buf.append(first);
                }

                while (iterator.hasNext()) {
                    if (separator != null) {
                        buf.append(separator);
                    }

                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(pre);
                        buf.append(obj);
                        buf.append(post);
                    }
                }

                return buf.toString();
            }
        }
    }
}
