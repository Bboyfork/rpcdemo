package com.fork.forkrpcall.config.spring.annotation;

import com.fork.forkrpcall.config.spring.FRpcConfiguration;
import com.fork.forkrpcall.config.spring.FRpcPostProcesser;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动Fork_RPC
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FRpcPostProcesser.class, FRpcConfiguration.class})
public @interface EnableFRPC {
}
