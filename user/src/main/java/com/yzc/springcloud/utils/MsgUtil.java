package com.yzc.springcloud.utils;


import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgUtil {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.signName}")
    private String signName;

    @Value("${aliyun.templateCode}")
    private String templateCode;



    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public  com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    //手机短信
    public void sendSms(String phone,String code)  throws Exception{
        com.aliyun.dysmsapi20170525.Client client = createClient(accessKeyId, accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam("{\"code\":"+"\"" + code +"\""+ "}");
        // 复制代码运行请自行打印 API 的返回值  //打印个鸡儿 还是得自己来
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
    }



}
