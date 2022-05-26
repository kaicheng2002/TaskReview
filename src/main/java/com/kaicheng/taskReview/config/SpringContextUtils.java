package com.kaicheng.taskReview.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/23 22:10
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    private static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    private static <T> T getBean(String beanName,Class<T> clazz){
        return applicationContext.getBean(beanName,clazz);
    }

    private static boolean containsBean(String beanName){
        return applicationContext.containsBean(beanName);
    }

    public static boolean isSingleton(String beanName){
        return applicationContext.isSingleton(beanName);
    }

    public static Class<? extends Object> getType(String beanName) {
        return applicationContext.getType(beanName);
    }

}
