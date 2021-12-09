package com.example.videodownload.controller;

import com.example.videodownload.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/qrCode")
    public void getQRCode(String codeContent, HttpServletResponse response) {
        try {
            qrCodeService.createQRCode2File(codeContent, "e:/test/1.jpg");
//            log.info("成功生成二维码！");
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("发生错误， 错误信息是：{}！", e.getMessage());
        }
    }
}
