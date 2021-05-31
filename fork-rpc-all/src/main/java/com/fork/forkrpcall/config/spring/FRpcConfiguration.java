package com.fork.forkrpcall.config.spring;

import com.fork.forkrpcall.config.ProtocolConfig;
import com.fork.forkrpcall.config.RegistryConfig;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Field;

/**
 * 配置
 *  让spring创建这个bean来使用      /   如何把自己创建的对象放到spring - BeanDefinition中
 * */
public class FRpcConfiguration implements ImportBeanDefinitionRegistrar {

    StandardEnvironment environment;

    public FRpcConfiguration(Environment environment) {
        this.environment = (StandardEnvironment)environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        //使用此对象来创建beanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder;

        beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ProtocolConfig.class);
        for (Field field:ProtocolConfig.class.getDeclaredFields()) {

            String value = environment.getProperty("frpc.protocol." + field.getName());
            beanDefinitionBuilder.addPropertyValue(field.getName(),value);
        }
        //并注册
        registry.registerBeanDefinition("ProtocolConfig",beanDefinitionBuilder.getBeanDefinition());

        //还有 RegintryConfig

        beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RegistryConfig.class);
        for (Field field:ProtocolConfig.class.getDeclaredFields()) {

            String value = environment.getProperty("frpc.registry." + field.getName());
            beanDefinitionBuilder.addPropertyValue(field.getName(),value);
        }
        //并注册
        registry.registerBeanDefinition("RegintryConfig",beanDefinitionBuilder.getBeanDefinition());

    }
}
