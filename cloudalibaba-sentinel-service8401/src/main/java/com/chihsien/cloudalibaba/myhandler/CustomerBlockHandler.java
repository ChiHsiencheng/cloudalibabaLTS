package com.chihsien.cloudalibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chihsien.springcloud.entities.CommonResult;

/**
 * @auther chihsien on 2021/6/2
 * 自定义限流处理逻辑
 */
public class CustomerBlockHandler {

    /**
     * @return com.chihsien.springcloud.entities.CommonResult
     * 与自定义通用的限流处理逻辑 customerBlockHandler 返回同源的类型
     * @param: exception
     * @author ChiHsien<br>
     * @version
     */
    public static CommonResult handleException(BlockException exception) {

        return new CommonResult(4444,
                "用户自定义的限流处理信息，global handlerException CustomerBlockHandler--------1");
    }

    public static CommonResult handleException2(BlockException exception) {

        return new CommonResult(4444,
                "用户自定义的限流处理信息，global handlerException CustomerBlockHandler---------2");
    }

}
