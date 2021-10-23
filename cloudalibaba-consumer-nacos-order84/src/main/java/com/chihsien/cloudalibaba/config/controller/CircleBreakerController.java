package com.chihsien.cloudalibaba.config.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chihsien.cloudalibaba.service.PaymentService;
import com.chihsien.springcloud.entities.CommonResult;
import com.chihsien.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @auther chihsien on 2021/6/2
 * 84服务熔断
 */
@RestController
@Slf4j
public class CircleBreakerController
{
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback") //没配置页面反馈不友好
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //只配置fallback负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")//只配置blockHandler负责业务异常
    @SentinelResource(value = "fallback",fallback = "handlerFallback"
                                        ,blockHandler = "blockHandler"
            //假如报该异常 不再由fallback兜底 没有降级效果 直接给页面反馈实际情况 多用于debug调试给运维 开发看
                                        ,exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {

        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);
        //预设两种异常 =4为runtime异常 归handlerFallback管 sentinel设置的异常归blockHandler管
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }
    /**
     *
     * @param: id
     * @param: e
     * @return com.chihsien.springcloud.entities.CommonResult
     * 兜底方法 返回页面提示异常类型和信息
     * @author ChiHsien<br>
     * @version
     */

    public CommonResult handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    public CommonResult blockHandler(@PathVariable  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }


    //==================OpenFeign
    @Resource
    //controller调用paymentservice
    private PaymentService paymentService;
    //consumer表示发送自消费者的请求
    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
       /* if(id == 4)
        {
            throw new RuntimeException("没有该id");
        }*/
        return paymentService.paymentSQL(id);
    }
}
