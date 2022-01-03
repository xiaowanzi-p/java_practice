package com.example.java_practice.stability;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class SceneAspect {

    @Autowired
    private SceneConfig sceneConfig;

    //切点是@SceneEntrance注解的方法
    @Before("@annotation(com.example.java_practice.stability.SceneEntrance)")
    public void beforeMethod(ProceedingJoinPoint joinPoint) {
        //获取Target对象实例的场景ID
        String sceneId = joinPoint.getTarget().getClass().getAnnotation(SceneService.class).sceneId();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //因为场景入口方法统一入参为BaseRequest,所以直接获取
        BaseRequest request = (BaseRequest) args[0];
        //先获取分流开关
        if (!sceneConfig.bucketSwitch(sceneId)) {
            //没有开启分流则不做任何处理
            return;
        }
        //再平衡桶
        balanceBucket(sceneId);
        //hash用户或者游客ID,确认桶
        int hash = hash(request.getHashKey());
        String bucket = confirmBucket(sceneId, hash);
        //根据桶做不桶的设置
        if (bucket.contains("allCache")) {
            //全缓存桶
            request.setDynamicCache(true);
            request.setStaticCache(true);
        }
        if (bucket.contains("halfCache")) {
            //半缓存桶
            request.setDynamicCache(false);
            request.setStaticCache(true);
        }
        if (bucket.contains("noCache")) {
            //实时计算桶
            request.setDynamicCache(false);
            request.setStaticCache(false);
        }

    }

    private int hash(String key) {
        return 0;
    }

    //重新平衡桶容量
    private void balanceBucket(String sceneId) {
        //判断全缓存桶的数据个数是否等于配置的值比如10,不等于则需要重新Rebalance
        int allCache = sceneConfig.allCache(sceneId);
        //判断半缓存桶的数据个数是否等于配置的值比如30,不等于则需要重新Rebalance
        int halfCahce = sceneConfig.halfCahce(sceneId);
        //判断实时计算桶的数据个数是否等于配置的值比如60,不等于则需要重新Rebalance
        int noCache = sceneConfig.noCache(sceneId);
    }

    //确认在哪个桶
    private String confirmBucket(String sceneId, int hash) {
        //判断hash值在哪一个桶的List中，就说明在哪一个桶
        return null;
    }
}
