package com.example.java_practice.demo;


import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] a) throws Exception {
        TestUser testUser = new TestUser();
        Class<? extends TestUser> clazz = testUser.getClass();
        Method demoMethod = clazz.getMethod("demoMethod");
        demoMethod.invoke(testUser,null);
    }
}


