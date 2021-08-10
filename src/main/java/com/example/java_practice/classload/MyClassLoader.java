package com.example.java_practice.classload;


/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {

    private String path;
    public MyClassLoader(String path) {
        //双亲委派,
        super(ClassLoader.getSystemClassLoader());
        //设置加载类资源的路径
        this.path = path;
    }

    public Class<?> findClass(String className) {
        //获取字节码资源

        //加载进内存为Class对象
        return null;
    }

    //加载字节码资源到内存
    private byte[] loadClassData(String className) {
        return null;
    }
}
