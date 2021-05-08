package com.fork.orderservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
@ComponentScan("com.fork.orderservice")
@PropertySource("classpath:/dubbo.properties")
@EnableDubbo(scanBasePackages = "com.fork.orderservice")
public class OrderServiceApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OrderServiceApplication.class);

        context.start();
        System.in.read();
        context.close();
    }


//    public static void main(String[] args) {
//        SpringApplication.run(OrderServiceApplication.class, args);
//    }

}
