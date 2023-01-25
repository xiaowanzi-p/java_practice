package com.example.java_practice.xa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pengshaoxiang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableRetry {

    /**
     * 任务名称
     */
    String name();

    /**
     * 第一次是否异步执行默认true
     */
    boolean async() default true;

    /**
     * 最大重试次数默认3
     */
    int maxRetry() default 3;

    /**
     * 重试间隔默认60秒
     */
    long retryTime() default 60;

    /**
     * 企微报警配置类名称
     */
    String notifyConfig();
}
