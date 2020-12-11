package com.zemel.note.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/2/21 17:25
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    static final Logger LOGGER = LoggerFactory.getLogger(MyBeanPostProcessor.class);
    // 容器加载的时候会加载一些其他的bean，会调用初始化前和初始化后方法
    // 这次只关注book(bean)的生命周期
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Test){
            LOGGER.error("MyBeanPostProcessor.postProcessBeforeInitialization"+bean.getClass());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Test){
            LOGGER.error("MyBeanPostProcessor.postProcessAfterInitialization"+bean.getClass());
        }
        return bean;
    }
}
