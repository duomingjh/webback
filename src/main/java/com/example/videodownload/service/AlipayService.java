package com.example.videodownload.service;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.videodownload.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Mall
 * @date ：Created in 2021-02-01
 * @description ：
 */
@Service
public class AlipayService {
    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 电脑版支付
     *
     * @param subject 标题
     * @param orderId 订单ID
     * @param total   金额
     * @return
     * @throws Exception
     */
    public String toPayPage(String subject, String orderId, String total) throws Exception {
        AlipayTradePagePayResponse response = Factory.Payment.Page().pay(subject, orderId, total, alipayConfig.return_url);
        return response.getBody();
    }




}
