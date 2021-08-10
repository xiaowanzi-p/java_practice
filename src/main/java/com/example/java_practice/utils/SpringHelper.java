package com.example.java_practice.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static  <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name,clazz);
    }

    public static Class<?> getBeanClass(String name) {
        return context.getBean(name).getClass();
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }
}
