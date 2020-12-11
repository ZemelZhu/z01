package com.zemel.note.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: zemel
 * @Date: 2020/2/21 16:45
 */
@Component("TestBean3")
public class TestBean2 implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean ,Test{
    static final Logger LOGGER = LoggerFactory.getLogger(TestBean2.class);
    @Autowired
    private TestBean test;
    public String getA()
    {
        return "Aa";
    }
    public TestBean2()
    {
        LOGGER.error("TestBean2 constrance");
    }
    @PreDestroy
    public void springPreDestory(){
        System.out.println("@PreDestory");
    }
    @PostConstruct
    public void init()
    {
        String a = test.getA();
        LOGGER.error("TestBean2 init"+a);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOGGER.error("setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        LOGGER.error("setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.error("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.error("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.error("setApplicationContext");
    }
}
