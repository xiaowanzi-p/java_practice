package com.example.java_practice.demo;

import com.example.java_practice.utils.ThreadPoolUtils;
import com.google.common.eventbus.AsyncEventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class EventBusConfig implements DisposableBean {
    @Autowired
    private EventListener listener;

    @Bean("AsyncEventBus")
    public AsyncEventBus createAsyncEventBus() {
        AsyncEventBus esignEventBus = new AsyncEventBus(ThreadPoolUtils.getPoolExecutor());
        esignEventBus.register(listener);
        log.info("电子签EvenBus创建成功:{}",esignEventBus.hashCode());
        return esignEventBus;
    }

    @Override
    public void destroy() throws Exception {
        AsyncEventBus asyncEventBus = createAsyncEventBus();
        log.info("电子签EvenBus开始销毁:{}",asyncEventBus.hashCode());
        asyncEventBus.unregister(listener);
    }
}
