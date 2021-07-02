package com.example.java_practice.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

    public static ThreadPoolExecutor poolExecutor;
    private static final int corePoolSize = 16;
    private static final int maximumPoolSize = 64;
    private static final long keepAliveTime = 60L;
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(128);

    static {
        poolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,workQueue);
    }

    public static void execute(Runnable runnable) {
        poolExecutor.execute(runnable);
    }
}
