package com.chihsien.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther chihsien on 2021/6/1
 */
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        return "------testB";
    }


    @GetMapping("/testD 异常比例")
    public String testD()
    {
        return "------testD";
    }


    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE测试异常数");
        int age = 10/0;
        return "------testE";
    }


    /**
     *
     * @param: p1
     * @param: p2
     * @return java.lang.String
     * 热点限流 访问该地址传入俩参数 p1 p2
     *        只对p1进行限流监控
     * @author ChiHsien<br>
     * @version
     */
    @GetMapping("/testHotKey")
    /**value保证唯一 一般和REST一致
     * blockHandler对于没有遵守在sentinel控制台所配置的规则的链接地址
     * 由blockHandler指定的方法来做兜底 类似于fallback
    */
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2
    ){
       // int num = 10/0;
        return  "-----------testHotKey";
    }


    /**
     *
     * @return java.lang.String
     * testHotKey的兜底方法
     * @author ChiHsien<br>
     * @version
     */

    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "--------deal_testHotKey   QAQ";
    }
}
