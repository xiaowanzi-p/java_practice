package com.example.java_practice.stability;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SceneService(sceneId = "product_detail")
@Getter
@Setter
public class ProductDetailSceneService extends BaseSceneService<Object> {

    //是否分流开关,默认false,支持动态配置
    @Value("${}")
    private boolean bucketSwitch = false;
    //全缓存流量,默认10%
    @Value("${}")
    private int allCache = 10;
    //半缓存流量,默认30%
    @Value("${}")
    private int halfCahce = 30;
    //实时计算流量,默认60%
    @Value("${}")
    private int noCache = 60;

    @Override
    protected String staticCacheKey(BaseRequest request, Map<String, Object> context) {
        return null;
    }

    @Override
    protected Object queryStaticData(BaseRequest request, Map<String, Object> context) {
        return null;
    }

    @Override
    protected String dynamicCacheKey(BaseRequest request, Map<String, Object> context) {
        return null;
    }

    @Override
    protected Object queryDynamicData(BaseRequest request, Map<String, Object> context) {
        return null;
    }

    @Override
    protected Object assembleView(BaseRequest request, Map<String, Object> context, Object staticData, Object dynamicData) {
        return null;
    }

    @Override
    protected Object getConfig(String config) {
        Object value = null;
        switch (config) {
            case "bucketSwitch":
                value = bucketSwitch;
                break;
            case "allCache":
                value = allCache;
                break;
            case "halfCahce":
                value = halfCahce;
                break;
            case "noCache":
                value = noCache;
                break;
            default:
        }
        return value;
    }
}
