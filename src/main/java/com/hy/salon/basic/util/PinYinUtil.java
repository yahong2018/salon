package com.hy.salon.basic.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {

    /**
     * 将汉字转换为全拼
     * @param src
     * @return
     */
    public static String getPinYin(String src){
        char[] hz = null;
        hz = src.toCharArray();//该方法的作用是返回一个字符数组，该字符数组中存放了当前字符串中的所有字符
        String[] py = new String[hz.length];//该数组用来存储
        //设置汉子拼音输出的格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String pys = ""; //存放拼音字符串
        int len = hz.length;

        try {
            for (int i = 0; i < len ; i++ ){
                //先判断是否为汉字字符
                if(Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")){
                    //将汉字的几种全拼都存到py数组中
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i],format);
                    //取出改汉字全拼的第一种读音，并存放到字符串pys后
                    pys += py[0];
                }else{
                    //如果不是汉字字符，间接取出字符并连接到 pys 后
                    pys += Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }
        return pys;
    }

    /**
     * 提取每个汉字的首字母
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str){
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            //提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null){
                convert += pinyinArray[0].charAt(0);
            }else{
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 将字符串转换成ASCII码
     */
    public static String getCnASCII(String str){
        StringBuffer buf = new StringBuffer();
        //将字符串转换成字节序列
        byte[] bGBK = str.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            //将每个字符转换成ASCII码
            buf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return buf.toString();
    }

/*
    */
/**
     * 测试
     *//*

    public static void main(String[] args) {
        String str = "中国梦ChainDream2018";
        char str2=str.charAt(0);

        String s = String.valueOf(str2);
        System.out.println("获取第一个字符"+s);
        System.out.println("将汉字转换为全拼"+getPinYin(str));
        System.out.println("获取中文首字母"+getPinYinHeadChar(str));
        System.out.println(getCnASCII(str));
    }
*/


}
