package com.example.java_practice.xa;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * 重试补偿核心AOP逻辑
 *
 * @author pengshaoxiang
 */
public class RetryAop {

    /**
     * 环绕通知
     */
    public Object aorund(ProceedingJoinPoint point, EnableRetry annotion) {
        //获取注解配置
        String name = annotion.name();
        boolean async = annotion.async();
        int maxRetry = annotion.maxRetry();
        long retryTime = annotion.retryTime();
        String notifyConfig = annotion.notifyConfig();
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        Object[] args = point.getArgs();
        String methodParam = "";
        if (args != null) {
            RetryParam param = (RetryParam) args[0];
            methodParam = JSONObject.toJSONString(param);
        }
        //在执行事务方法之前先记录本地消息表
        RetryModel model = new RetryModel();
        model.setName(name);
        model.setAsync(async);
        model.setMaxRetry(maxRetry);
        model.setRetryTime(retryTime);
        model.setNotifyConfig(notifyConfig);
        model.setStatus("待执行");
        model.setClassName(className);
        model.setMethodName(method);
        model.setMethodParam(methodParam);
        saveRetryModel(model);
        //执行事务方法,判断是否需要异步
        Object result = null;
        if (async) {
            CompletableFuture.runAsync(() -> executeTransaction(model,point));
        } else {
            result = executeTransaction(model,point);
        }
        //如果是异步执行，则返回值固定为空
        return result;
    }


    private void saveRetryModel(RetryModel model) {}

    private Object executeTransaction(RetryModel model, ProceedingJoinPoint point) {
        try {
            //执行事务方法
            Object result = point.proceed();
            //更新本地消息表为成功
            model.setStatus("成功");
            saveRetryModel(model);
            return result;
        } catch (Throwable e) {
            //失败了立刻重试一次
            try {
                Object result = point.proceed();
                //更新本地消息表为成功
                model.setStatus("成功");
                saveRetryModel(model);
                return result;
            } catch (Throwable x) {
                //记录重试信息更新本地消息表
                long retryTime = model.getRetryTime();
                long nextTime = System.currentTimeMillis() / 1000 + retryTime;
                model.setNextExecuteTime(nextTime);
                model.setStatus("失败");
                model.setRemark(x.getMessage());
                saveRetryModel(model);
            }
        }
        return null;
    }
}
