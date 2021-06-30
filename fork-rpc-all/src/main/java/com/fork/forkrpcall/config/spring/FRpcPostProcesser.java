package com.fork.forkrpcall.config.spring;

import com.fork.forkrpcall.config.ProtocolConfig;
import com.fork.forkrpcall.config.ReferenceConfig;
import com.fork.forkrpcall.config.RegistryConfig;
import com.fork.forkrpcall.config.ServiceConfig;
import com.fork.forkrpcall.config.annotation.FRpcReference;
import com.fork.forkrpcall.config.annotation.FRpcService;
import com.fork.forkrpcall.config.util.FrpcBootstrap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.ServiceLoader;

public class FRpcPostProcesser implements ApplicationContextAware, InstantiationAwareBeanPostProcessor {
    ApplicationContext applicationContext;

    /**
     * 继承它来获取上下文对象
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //扩展： 重写自InstantiationAwareBeanPostProcessor
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {

        /**
         * 服务暴露 【FRpcService】
         * */
        if(bean.getClass().isAnnotationPresent(FRpcService.class)){

            //找到需要开放网络访问的service实现类。构建serviceConfig配置
            ServiceConfig serviceConfig = new ServiceConfig();
            serviceConfig.addProtocolConfig(applicationContext.getBean(ProtocolConfig.class));
            serviceConfig.addRegistryConfig(applicationContext.getBean(RegistryConfig.class));
            serviceConfig.setReference(bean);
            FRpcService fRpcService = bean.getClass().getAnnotation(FRpcService.class);
            if(void.class == fRpcService.interfaceClass()){
                serviceConfig.setService(bean.getClass().getInterfaces()[0]);
            }else {
                serviceConfig.setService(fRpcService.interfaceClass());
            }

            //还需协议、、、、、等
            //创建一个boot引导 在其中写
            FrpcBootstrap.export(serviceConfig);
        }


        //服务引用  【FRpcReference部分】
        for(Field field:bean.getClass().getDeclaredFields()){
            try {
                if(!field.isAnnotationPresent(FRpcReference.class)){
                    continue;
                }
                //引用相关配置 保存在一个对象里
                ReferenceConfig referenceConfig = new ReferenceConfig();
                referenceConfig.addRegistryConfig(applicationContext.getBean(RegistryConfig.class));
                referenceConfig.addProtocolConfig(applicationContext.getBean(ProtocolConfig.class));
                referenceConfig.setService(field.getType());

                FRpcReference fRpcReference = field.getAnnotation(FRpcReference.class);
                referenceConfig.setLoadbalance(fRpcReference.loadbalance());
                Object referenceBean = FrpcBootstrap.getReferenceBean(referenceConfig);
                field.setAccessible(true);
                field.set(bean,referenceBean);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return true;
    }

}
