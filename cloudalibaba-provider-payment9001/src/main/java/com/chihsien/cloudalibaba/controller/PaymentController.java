package com.chihsien.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
 /*************                                                        _ooOoo_
         2 //                                                         o8888888o
         3 //                                                         88" . "88
         4 //                                                         (| -_- |)
         5 //                                                          O\ = /O
         6 //                                                      ____/`---'\____
         7 //                                                    .   ' \\| |// `.
         8 //                                                     / \\||| : |||// \
         9 //                                                   / _||||| -:- |||||- \
         10 //                                                     | | \\\ - /// | |
         11 //                                                   | \_| ''\---/'' | |
         12 //                                                    \ .-\__ `-` ___/-. /
         13 //                                                 ___`. .' /--.--\ `. . __
         14 //                                              ."" '< `.___\_<|>_/___.' >'"".
         15 //                                             | | : `- \`.;`\ _ /`;.`/ - ` : | |
         16 //                                               \ \ `-. \_ __\ /__ _/ .-` / /
         17 //                                       ======`-.____`-.___\_____/___.-`____.-'======
         18 //                                                          `=---='
         19 //
         20 //                                       .............................................
         21 //                                              佛祖保佑             永无BUG
         22 //                                      佛曰:
         23 //                                              写字楼里写字间，写字间里程序员；
         24 //                                              程序人员写程序，又拿程序换酒钱。
         25 //                                              酒醒只在网上坐，酒醉还来网下眠；
         26 //                                              酒醉酒醒日复日，网上网下年复年。
         27 //                                              但愿老死电脑间，不愿鞠躬老板前；
         28 //                                              奔驰宝马贵者趣，公交自行程序员。
         29 //                                              别人笑我忒疯癫，我笑自己命太贱；
         30 //                                              不见满街漂亮妹，哪个归得程序员？
/**
 * @auther chihsien on 2021/5/31
 */
@RestController
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }
}
