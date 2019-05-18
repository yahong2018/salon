package com.hy.salon.basic.controller;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hy.salon.basic.util.QRCodeUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/hy/basic/qrcode")
@Api(value = "QrCodeController| 二维码控制器")
public class QrCodeController {







    @RequestMapping("getQrcode")
    @ResponseBody
    public void generateQRCode4Product(HttpServletRequest request, HttpServletResponse response,String url) {
        String longUrl;
        try {
//            longUrl = "http://www.baidu.com";

            // 生成二维码
            BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(url, response);
            // 将二维码输出到页面中
            MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
