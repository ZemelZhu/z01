package com.zemel.note.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * @Author: zemel
 * @Date: 2020/2/21 17:38
 */
@Component
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    static final Logger LOGGER = LoggerFactory.getLogger(MyBeanPostProcessor.class);
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        LOGGER.error("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,
                                                 String beanName) throws BeansException {
        if(beanName.contains("TestBean"))
            LOGGER.error("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法"+beanClass);
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if(beanName.contains("TestBean"))
            LOGGER.error("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法"+bean.getClass());
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        if(beanName.contains("TestBean"))
            LOGGER.error("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法"+bean.getClass());
        return pvs;
    }
}
