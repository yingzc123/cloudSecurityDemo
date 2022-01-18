package com.yzc.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public interface SendMsg {
    /**
     *
     * @param date 接收账号
     * @throws Exception
     */
    void send(String date);

}
