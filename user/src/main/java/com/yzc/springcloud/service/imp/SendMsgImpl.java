package com.yzc.springcloud.service.imp;


import cn.hutool.core.util.RandomUtil;
import com.yzc.spirngcloud.utils.exceptionhandler.DiyException;
import com.yzc.springcloud.service.SendMsg;
import com.yzc.springcloud.utils.MsgUtil;
import com.yzc.springcloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SendMsgImpl implements SendMsg {
    @Autowired
    private MsgUtil msgUtil;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 适配器模式
     *
     * @param cal 发送账号
     * @throws Exception
     */

    @Override
    public void send(String cal) {
        boolean isTrue = redisUtil.hasKey(cal);
        if (isTrue) {
            long time = redisUtil.getExpire(cal);
            throw  new DiyException(500,"请" + time + "秒后在发送验证码请求");
        }
        String code = RandomUtil.randomString(6);
        try {
            msgUtil.sendSms(cal, code);
        }catch (Exception e) {
            throw  new DiyException(500,e.getMessage());
        }
        redisUtil.set(cal, code, 60);
    }


}
