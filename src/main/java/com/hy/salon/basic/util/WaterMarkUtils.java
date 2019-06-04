package com.hy.salon.basic.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
public class WaterMarkUtils {
    /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent,Color markContentColor,Font font) {

        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
            System.out.println("srcImgWidth====="+srcImgWidth);
            System.out.println("getWatermarkLength(waterMarkContent, g)====="+getWatermarkLength(waterMarkContent, g));
            int x = 0;
            int y = 50;
            int watermarkLength = getWatermarkLength(waterMarkContent, g);//获取水印内容长度
            g.drawString(waterMarkContent, x, y);                           //画出第一道水印
            g.drawString("第二道水印", x+watermarkLength+20, y+50);  //画出第二道水印
            g.drawString("第三道水印", x+2*watermarkLength+20, y+50+50);  //画出第三道水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
/*    public static void main(String[] args) {
        Font font = new Font("测试一小妹", Font.PLAIN, 20);                     //水印字体
        String srcImgPath="E:/image/d33734eaa083a4185c69c00ec8055a8.jpg"; //源图片地址
        String tarImgPath="E:/image/999.jpg"; //待存储的地址
        String waterMarkContent="第一道水印";  //水印内容
        Color color = new Color(255,255,0,128);                               //水印图片色彩以及透明度
        new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, waterMarkContent, color, font);

    }*/

}
