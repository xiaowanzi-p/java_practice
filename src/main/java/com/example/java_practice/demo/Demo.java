package com.example.java_practice.demo;

import com.example.java_practice.classload.MyClassLoader;
import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.datastructure.graph.Graph;
import com.example.java_practice.netty.NettyServer;
import com.example.java_practice.nio.NormalNio;
import com.example.java_practice.rpc.provider.DefaultRpcProvider;
import com.example.java_practice.rpc.stub.StubFactory;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;

public class Demo {

    public static void main(String[] a) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/pengshaoxiang/Documents/");
        Class<?> clazz = myClassLoader.findClass("DemoUser");
        DemoUser demoUser = (DemoUser) clazz.newInstance();
        String name = demoUser.getName();
        System.out.println(name);
    }
}


