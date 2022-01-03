package com.example.java_practice.stability;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础场景模板方法,用户需要继承这个自定义
 */

public abstract class BaseSceneService<T> {

    //场景查询总入口方法,需要加上注解以便AOP加强
    @SceneEntrance
    public T queryScene(BaseRequest request) {
        boolean dynamicCache = request.isDynamicCache();
        boolean staticCache = request.isStaticCache();
        //方法上下文传递,用来传递信息
        Map<String, Object> context = new HashMap<>();

        Object staticData = null;
        //开启了静态数据缓存,从缓存获取数据
        if (staticCache) {
            staticData = queryStaticCache(request, context);
        }
        //没有开启静态缓存或者缓存没有获得数据,实时获取静态数据
        if (staticData == null) {
            staticData = queryStaticData(request, context);
            //开启了静态数据缓存则需要设置缓存,默认5分钟失效
            if (staticCache) {
                setStaticCache(request,context,staticData,300L);
            }
        }

        Object dynamicData = null;
        //开启了动态数据缓存,则从缓存获取数据
        if (dynamicCache) {
            dynamicData = queryDynamicCache(request, context);
        }
        //没有开启动态缓存或者缓存没有获得数据,实时获取动态数据
        if (dynamicData == null) {
            dynamicData = queryDynamicData(request,context);
            //开启了动态数据缓存则需要设置缓存,默认5分钟失效
            if (dynamicCache) {
                setDynamicCache(request,context,dynamicData,300L);
            }
        }

        //视图渲染
        T view = assembleView(request, context, staticData, dynamicData);
        return view;
    }


    //获取静态数据缓存
    private Object queryStaticCache(BaseRequest request, Map<String, Object> context) {
        String cacheKey = staticCacheKey(request, context);
        Object data = queryFromCache(cacheKey);
        return data;
    }

    private void setStaticCache(BaseRequest request, Map<String, Object> context, Object data, Long sceonds) {
        String cacheKey = staticCacheKey(request, context);
        setCache(cacheKey,data);
    }

    //静态缓存的Key方法,需要自定义实现
    protected abstract String staticCacheKey(BaseRequest request, Map<String, Object> context);

    //此处是调用缓存的方法,可以是Redis,可以是本地缓存
    private Object queryFromCache(String cacheKey) {
        return null;
    }

    //此处设置缓存的方法,可以是Redis,可以是本地缓存
    private void setCache(String cacheKey, Object data) {

    }

    //实时获取静态数据,需要用户自己实现
    protected abstract Object queryStaticData(BaseRequest request, Map<String, Object> context);


    private Object queryDynamicCache(BaseRequest request, Map<String, Object> context) {
        String key = dynamicCacheKey(request, context);
        Object data = queryFromCache(key);
        return data;
    }

    protected abstract String dynamicCacheKey(BaseRequest request, Map<String, Object> context);

    //实时获取动态数据,需要用户自己实现
    protected abstract Object queryDynamicData(BaseRequest request, Map<String, Object> context);

    private void setDynamicCache(BaseRequest request, Map<String, Object> context, Object data, Long sceonds) {
        String cacheKey = dynamicCacheKey(request, context);
        setCache(cacheKey,data);
    }

    //组装视图的方法,需要用户自己实现
    protected abstract T assembleView(BaseRequest request, Map<String, Object> context, Object staticData, Object dynamicData);

    //获取场景配置的方法
    protected abstract Object getConfig(String config);
}
