package com.hy.salon.basic.controller;

import com.hy.salon.basic.util.CodeUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;
@Controller
@Api(value = "RandomCode|验证码控制器")
@RequestMapping("/randomCode")
public class RandomCode {

    @RequestMapping
    public String getCode(HttpServletResponse resp, HttpServletRequest req) {
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
//      req.getContext().getSession().put("code", codeMap.get("code").toString());
        req.getSession().setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/getCodeId")
    public String getCodeId(HttpServletResponse resp, HttpServletRequest req) {
        String code= (String) req.getSession().getAttribute("code");

        System.out.println("code++++++++++++++"+code);
        return null;
    }
}
