package com.chihsien.cloudalibaba.service;

import com.chihsien.springcloud.entities.CommonResult;
import com.chihsien.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @auther chihsien on 2021/6/2
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)//调用中关闭9003服务提供者
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    //调用 9003 9004方法
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}