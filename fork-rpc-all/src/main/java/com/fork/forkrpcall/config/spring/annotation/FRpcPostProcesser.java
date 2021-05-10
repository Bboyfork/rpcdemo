package com.fork.forkrpcall.config.spring.annotation;

import com.fork.forkrpcall.config.annotation.FRpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FRpcPostProcesser implements ApplicationContextAware, InstantiationAwareBeanPostProcessor {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //扩展： 重写自InstantiationAwareBeanPostProcessor
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {

        /**
         * 判断一下bean中是否有这个注解 有 那就得将它暴露为我们自己的RPC服务
         * */
        if(bean.getClass().isAnnotationPresent(FRpcService.class)){
            System.out.println("启动网络服务 暴露为FRpc Service");
        }
        return true;
    }

}
