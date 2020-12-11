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
 * @Date: 2020/2/21 15:23
 */
@Component("TestBean")
public class TestBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean ,Test{
    static final Logger LOGGER = LoggerFactory.getLogger(TestBean.class);
    @Autowired
    private TestBean2 test;
    public String getA()
    {
        return "A";
    }
    public TestBean()
    {

        LOGGER.error("TestBean constrance");
    }
    @PreDestroy
    public void springPreDestory(){
        test.getA();
        LOGGER.error("@PreDestory");
    }
    @PostConstruct
    public void init()
    {
        LOGGER.error("TestBean init");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOGGER.error("TestBean setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        LOGGER.error("TestBean setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.error("TestBean destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.error("TestBean afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.error("TestBean setApplicationContext");
    }
}
