package com.java.agent;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    /*public static void premain(String agentArgument, Instrumentation instrumentation) throws Exception{
        System.out.println("进入了代理拦截器");
        System.out.println("代理的配置参数: " + agentArgument);
        MyClassFileTransformer transformer = new MyClassFileTransformer();
        instrumentation.addTransformer(transformer);
    }*/


    public static void agentmain(String agentArgument, Instrumentation instrumentation) throws Exception {
        System.out.println("this is a agent process start");
        MyClassFileTransformer transformer = new MyClassFileTransformer();
        instrumentation.addTransformer(transformer,true);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        System.out.println("loaded class size " + allLoadedClasses.length);
        for (Class clazz : allLoadedClasses) {
            System.out.println("ClassName is " + clazz.getName());
            if (clazz.getName().contains("TestDemo")) {
                System.out.println("retransformClasses is " + clazz.getName());
                instrumentation.retransformClasses(clazz);
                break;
            }
        }
        System.out.println("this is a agent process end");
    }
}
