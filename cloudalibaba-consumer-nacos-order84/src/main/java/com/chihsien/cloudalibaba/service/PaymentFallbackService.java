package com.chihsien.cloudalibaba.service;

import com.chihsien.springcloud.entities.CommonResult;
import com.chihsien.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @auther chihsien on 2021/6/2
 * 兜底
 */
@Component
public class PaymentFallbackService implements PaymentService
{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        //服务降级兜底方案
        return new CommonResult<>(4444444,"服务降级返回,没有该流水信息",new Payment(id, "errorSerial......"));
    }
}
