package com.fork.forkrpcall.config.annotation;


import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FRpcReference {

    /**
     * 这里是什么意思呢？
     *
     * 如果有多个接口 需要自己指定一个
     * */
    //暂时注释 看看好不好使
//    Class<?> interfaceClass() default void.class;

    String loadbalance() default "RandomLoadBalance";


}
