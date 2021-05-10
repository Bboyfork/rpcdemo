package com.fork.smsservice.sms;

import com.fork.forkrpcall.config.annotation.FRpcService;
import com.rpcfork.api.sms.SmsService;
import org.apache.dubbo.config.annotation.DubboReference;

@FRpcService
public class SmsServiceImpl implements SmsService {

    @DubboReference

    @Override
    public Object send(String phone, String content) {
        System.out.println("短信发送... 目标：" + phone + " , content: " + content);

        return "短信已发送：" + phone + content;
    }
}
