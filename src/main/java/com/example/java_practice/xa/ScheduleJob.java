package com.example.java_practice.xa;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.utils.SpringHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ScheduleJob {

    public void task() {
        //查询已失败的所有记录
        List<RetryModel> failTask = queryFailTask();
        //已达到最大重试次数还没成功的记录开始发送消息告警
        notify(failTask);
        //找出到了执行时间的task
        List<RetryModel> confirmExecuteTask = confirmExecuteTask(failTask);
        //开始用线程池异步执行任务,需要自定义线程池
        confirmExecuteTask.forEach(task -> {
            CompletableFuture.runAsync(() -> executeTask(task));
        });
    }

    /**
     * 查询已失败的记录
     */
    private List<RetryModel> queryFailTask() {
        return null;
    }

    /**
     * 确认可以执行的任务:重试次数未到最大次数、并且到了可以执行的时间
     */
    private List<RetryModel> confirmExecuteTask(List<RetryModel> list) {
        ArrayList<RetryModel> result = new ArrayList<>();
        for (RetryModel model : list) {
            int hasRetry = model.getHasRetry();
            Integer maxRetry = model.getMaxRetry();
            //已经重试了最大次数则略过
            if (hasRetry == maxRetry) {
                continue;
            }
            //没到执行时间的略过
            long nextExecuteTime = model.getNextExecuteTime();
            long now = System.currentTimeMillis() / 1000;
            if (nextExecuteTime > now) {
                continue;
            }
            //到了执行时间的则可以执行
            result.add(model);
        }
        return result;
    }

    /**
     * 重试到了最大次数还失败的进行消息告警
     */
    private void notify(List<RetryModel> list) {
        for (RetryModel model : list) {
            int hasRetry = model.getHasRetry();
            Integer maxRetry = model.getMaxRetry();
            if (maxRetry == hasRetry) {
                //消息告警
                String notifyConfig = model.getNotifyConfig();
                Object config = SpringHelper.getBean(notifyConfig);
                //调用企微消息告警
            }
        }
    }


    /**
     * 执行任务开始补偿
     */
    private void executeTask(RetryModel model) {
        try {
            //此处需要加分布式锁以及幂等操作，防止重复执行。分布式锁key可以用ID，幂等逻辑可以是最大重试次数以及状态
            //获取执行的类、方法、入参等信息
            String className = model.getClassName();
            String methodName = model.getMethodName();
            String methodParam = model.getMethodParam();
            RetryParam param = JSONObject.parseObject(methodParam, RetryParam.class);
            //从Spring容器获取实例、反射进行调用方法
            Object target = SpringHelper.getBean(className);
            Class<?> aClass = SpringHelper.getBean(className).getClass();
            Method method = aClass.getMethod(methodName);
            method.invoke(target,param);
            //成功则更新本地消息记录表
            int hasRetry = model.getHasRetry() + 1;
            model.setHasRetry(hasRetry);
            model.setStatus("成功");
            saveRetryModel(model);
        } catch (Throwable e) {
            //失败了记录下次重试信息并更新本地消息表
            long retryTime = model.getRetryTime();
            long nextTime = System.currentTimeMillis() / 1000 + retryTime;
            int hasRetry = model.getHasRetry() + 1;
            model.setNextExecuteTime(nextTime);
            model.setHasRetry(hasRetry);
            model.setRemark(e.getMessage());
            model.setStatus("失败");
            saveRetryModel(model);
        }
    }

    private void saveRetryModel(RetryModel model) {}
}
