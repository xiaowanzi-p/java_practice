package com.example.java_practice.stability;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础入参,用户需要继承这个
 */
@Getter
@Setter
public class BaseRequest<T> {

    //是否开启静态数据缓存,默认false
    private boolean staticCache = false;
    //是否开启动态数据缓存,默认false
    private boolean dynamicCache = false;
    //用来哈希计算的分流key,一般是用户ID或者游客ID
    private String hashKey;
    //请求入参数据
    private T request;
}
