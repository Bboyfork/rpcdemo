package com.fork.smsservice.sms;

import com.fork.forkrpcall.config.annotation.FRpcService;
import com.rpcfork.api.sms.SmsService;

@FRpcService
public class SmsServiceImpl implements SmsService {
    @Override
    public Object send(String phone, String content) {
        System.out.println("短信发送... 目标：" + phone + " , content: " + content);

        return "短信已发送：" + phone + content;
    }
}
