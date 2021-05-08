package com.fork.smsservice;

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
@PropertySource("classpath:/dubbo.properties")
@EnableDubbo(scanBasePackages = "com.fork.smsservice")
public class SmsServiceApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SmsServiceApplication.class);

        context.start();
        System.in.read();
        context.close();
    }
}
