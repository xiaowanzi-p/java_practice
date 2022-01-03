package com.example.java_practice.stability;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景配置类,所有场景的分桶开关、桶容量初始化都是这里配置
 */
@Service
public class SceneConfig implements InitializingBean {

    //key是场景ID,Value是场景Service
    private Map<String,BaseSceneService> map = new HashMap<>();
    //key是场景ID,Value是场景的三个桶容量
    private Map<String,Map<String,List<Integer>>> bucketMap = new HashMap<>();
    @Autowired
    private List<BaseSceneService> baseSceneServices;


    @Override
    public void afterPropertiesSet() throws Exception {
        for (BaseSceneService baseSceneService : baseSceneServices) {
            //初始化Map
            SceneService annotation = baseSceneService.getClass().getAnnotation(SceneService.class);
            String sceneId = annotation.sceneId();
            map.put(sceneId,baseSceneService);
            //初始化全缓存桶容量 (1-10)
            Map<String, List<Integer>> allCache = Map.of("allCache", initBucket(1, 10));
            bucketMap.put(sceneId,allCache);
            //初始化半缓存桶容量(11-40)
            Map<String, List<Integer>> halfCahce = Map.of("halfCahce", initBucket(11, 40));
            bucketMap.put(sceneId,halfCahce);
            //初始化实时计算桶容量(41-100)
            Map<String, List<Integer>> noCache = Map.of("noCache", initBucket(41, 60));
            bucketMap.put(sceneId,noCache);
        }
    }

    public boolean bucketSwitch(String sceneId) {
        return (Boolean)map.get(sceneId).getConfig("bucketSwitch");
    }

    public int allCache(String sceneId) {
        return (Integer) map.get(sceneId).getConfig("allCache");
    }

    public int halfCahce(String sceneId) {
        return (Integer) map.get(sceneId).getConfig("halfCahce");
    }

    public int noCache(String sceneId) {
        return (Integer) map.get(sceneId).getConfig("noCache");
    }

    private List<Integer> initBucket(int begin, int end) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            list.add(i);
        }
        return list;
    }
}
