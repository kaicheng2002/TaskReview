package com.kaicheng.taskReview.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/24 18:34
 */
public class SchedulingRunnable implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SchedulingRunnable.class);

    private String beanName;

    private String methodName;

    private String params;

    private Object targetBean;

    private Method method;

    public SchedulingRunnable(String beanName, String methodName) {
        this.beanName = beanName;
        this.methodName = methodName;
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        init();
    }


    private void init() {
        try {
            targetBean = SpringContextUtils.getBean(beanName);
            if (StringUtils.hasText(params)) {
                method = targetBean.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = targetBean.getClass().getDeclaredMethod(methodName);
            }
            ReflectionUtils.makeAccessible(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        log.info("定时任务开始 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();

        try {
            if (StringUtils.hasText(params)) {
                method.invoke(targetBean, params);
            } else {
                method.invoke(targetBean);
            }
        } catch (Exception e) {
            log.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s", targetBean, methodName, params), e);
        }

        long endTime = System.currentTimeMillis() - startTime;
        log.info("定时任务结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", targetBean, methodName, params, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        return Objects.equals(beanName, that.beanName) && Objects.equals(methodName, that.methodName);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }
        return Objects.hash(beanName, methodName, params);
    }
}
