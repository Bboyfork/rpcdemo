package com.fork.smsservice;

import com.fork.forkrpcall.config.spring.annotation.EnableFRPC;
import com.rpcfork.api.sms.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan("com.fork.smsservice")
@EnableFRPC
public class SmsServiceApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SmsServiceApplication.class);

        context.start();

        //启动就发一条测试
        SmsService smsService = context.getBean(SmsService.class);
        smsService.send("15765519493","启动时的一条短信测试");

        //阻塞 不退出
        System.in.read();
        context.close();
    }
}
