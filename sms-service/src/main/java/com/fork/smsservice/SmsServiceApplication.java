package com.fork.smsservice;

import com.fork.smsservice.sms.SmsServiceImpl;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
@ComponentScan("com.fork.smsservice")
public class SmsServiceApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SmsServiceApplication.class);

        context.start();

        //启动就发一条测试
        SmsServiceImpl smsService = context.getBean(SmsServiceImpl.class);
        smsService.send("15765519493","启动时的一条短信测试");

        //阻塞 不退出
        System.in.read();
        context.close();
    }
}
