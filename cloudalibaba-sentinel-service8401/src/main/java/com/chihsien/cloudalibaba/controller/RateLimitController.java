package com.chihsien.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chihsien.cloudalibaba.myhandler.CustomerBlockHandler;
import com.chihsien.springcloud.entities.CommonResult;
import com.chihsien.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther chihsien on 2021/6/2
 */
@RestController
public class RateLimitController {
    /**
     *
     * @return com.chihsien.springcloud.entities.CommonResult
     * 按资源名称限流
     * @author ChiHsien<br>
     * @version
     */

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception)
    {
        //由FlowException类来负责异常包圆~
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }


    /**
     *
     * @return com.chihsien.springcloud.entities.CommonResult
     * 无兜底方法 使用系统默认的 按url
     * @author ChiHsien<br>
     * @version
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }

   /**
    *
    * @return com.chihsien.springcloud.entities.CommonResult
    *   自定义通用的限流处理逻辑，
    *      blockHandlerClass = CustomerBlockHandler.class
    *      blockHandler = handleException2
    *      上述配置：找CustomerBlockHandler类里的handleException2方法进行兜底处理
    *      自定义通用的限流处理逻辑
    *      blockHandlerClass:可以找CustomerBlockHandler里具体的方法来兜底处理
    * @author ChiHsien<br>
    * @version 1.0
    */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleException2")
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客户自定义限流处理逻辑",new Payment(2021L,"serial003"));
    }


}
