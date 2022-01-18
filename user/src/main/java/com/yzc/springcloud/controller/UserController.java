package com.yzc.springcloud.controller;



import com.yzc.springcloud.service.SendMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-09-23
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private SendMsg sendMsg;

    @RequestMapping("/test")
    public String test(){
        return "hello";
    }


    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/send")
    public void sendMsg(String cal) {
        sendMsg.send(cal);
    }
}
