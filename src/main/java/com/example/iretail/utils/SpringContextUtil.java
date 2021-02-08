package com.example.iretail.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Map;
import java.util.Properties;

/**
 * Spring 获取bean 工具类
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

    public static Properties getConfig() throws Exception {
        return ((PropertiesFactoryBean) getBean("&appProperties")).getObject();
    }

    public static Properties getConfig2() throws Exception {
        return ((PropertiesFactoryBean) getBean("appProperties")).getObject();
    }

    public static String[] getBeanMap() {
        return ((XmlWebApplicationContext) SpringContextUtil.applicationContext).getBeanFactory().getBeanDefinitionNames();
    }

    public static Map getBeanWithAnno(Class annotation) {
        return ((XmlWebApplicationContext) SpringContextUtil.applicationContext).getBeanFactory().getBeansWithAnnotation(annotation);
    }
}