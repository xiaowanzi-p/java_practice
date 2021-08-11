package com.example.java_practice.classload;


import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * 自定义类加载器
 */
@Slf4j
public class MyClassLoader extends ClassLoader {

    private String path;
    public MyClassLoader(String path) {
        //双亲委派,
        super(ClassLoader.getSystemClassLoader());
        //设置加载类资源的路径
        this.path = path;
    }

    public Class<?> findClass(String className) {
        try {
            //获取字节码资源
            byte[] data = loadClassData(className);
            //加载进内存为Class对象
            String name = "com.example.java_practice.demo." + className;
            Class<?> clazz = super.defineClass(name, data, 0, data.length);
            return clazz;
        } catch (Exception e) {
            log.error("类加载失败",e);
        }
        return null;
    }

    //加载字节码资源到内存
    private byte[] loadClassData(String className) throws Exception {
        String finalName = className + ".class";
        String filePath = path + finalName;
        File source = new File(filePath);
        FileInputStream inputStream = new FileInputStream(source);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int b = 0;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
        byte[] outPut = outputStream.toByteArray();
        inputStream.close();
        outputStream.close();
        return outPut;
    }
}
