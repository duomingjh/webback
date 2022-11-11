package com.example.videodownload.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.example.videodownload.config.AlipayConfig;
import com.example.videodownload.domain.AlipayNotifyParam;
import com.example.videodownload.service.QRCodeService;
import com.example.videodownload.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class indexController {
   @Autowired
   private AlipayConfig alipayConfig;
@Autowired
    private QRCodeService qrCodeService;
@Autowired
   private MyWebSocket webSocket;

    @PostMapping("/alipayPrecreate")

    public  ResponseEntity index(ModelMap mmap,String sp,float number,String name) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",alipayConfig.app_id,alipayConfig.merchant_private_key,"json","GBK",alipayConfig.alipay_public_key,"RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl("");
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", sp);
        System.out.println(sp);
        bizContent.put("total_amount", number);
        bizContent.put("subject", name);
        request.setBizContent(bizContent.toString());
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
       /* String rsult=response.getBody();
        System.out.println(rsult);
        com.alibaba.fastjson.JSONObject res= JSON.parseObject(rsult);
        Map response1 = (Map) res.get("alipay_trade_precreate_response");
        if (response1.get("code").toString().equals("10000") && response1.get("qr_code") != null) {
            qrCodeService.createQRCode2File(response1.get("qr_code").toString(),"D:/test/"+sp+".jpg");
            response1.put("qrcode","D:/test/"+number);
        }*/
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/trade-notif")

    public ResponseEntity tradeNotify(HttpServletRequest request,String out_trade_no) throws IOException, AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",alipayConfig.app_id,alipayConfig.merchant_private_key,"json","GBK",alipayConfig.alipay_public_key,"RSA2");
        AlipayTradeQueryRequest request1 = new AlipayTradeQueryRequest();//创建API对应的request类
        request1.setBizContent("{" +
                "\"out_trade_no\":\""+out_trade_no+"\"," +
                "\"trade_no\":\"\"}");
        AlipayTradeQueryResponse response = alipayClient.execute(request1);//通过alipayClient调用API，获得对应的response类
        System.out.print(response.getBody());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = JSON.toJSONString(params);
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }

}
