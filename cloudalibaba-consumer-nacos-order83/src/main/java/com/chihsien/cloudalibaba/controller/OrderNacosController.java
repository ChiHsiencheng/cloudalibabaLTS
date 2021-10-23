package com.chihsien.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @auther chihsien on 2021/5/31
 * 包含负载均衡能力
 */
@RestController
public class OrderNacosController
{
    @Resource
    private RestTemplate restTemplate;
    /**
     * <strong>Title : <br>
     * @author ChiHsien<br>
     *     读取yml文件中的service-url.nacos-user-service：http://nacos-payment-provider
     *     以前的写法：public static final String SERVER_URL = "http://nacos-payment-provider\n"
     *    将微服务的地址写进配置文件与代码分离
     */
    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    /**
     * 83调用9001、9002 写入他们暴露出来的REST接口
     *      加个consumer表示来自于客户端
     * @param: id
     * @return java.lang.String
     * @throws
     * @author ChiHsien<br>
     * @version
     */

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id)
    {
        //拼接/payment/nacos/
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }

}