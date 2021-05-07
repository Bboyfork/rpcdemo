package com.rpcfork.api.sms;

/**
 * 短信
 * @author Bboy_fork
 * @date 2021年5月6日10:36:05
 */
public interface SmsService {

    /**
     * 发送短信方法
     * @param phone 号码
     * @param content 内容
     * @return 发送结果
     */
    Object send(String phone, String content);
}
