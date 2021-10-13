package com.example.java_practice.demo;


import com.example.java_practice.netty.NettyServer;
import com.sun.tools.attach.VirtualMachine;

import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] a) throws Exception {
       /* VirtualMachine attach = VirtualMachine.attach("2512");
        try {
            attach.loadAgent("/Users/pengshaoxiang/Documents/java_agent-0.0.1-SNAPSHOT.jar");
        } finally {
            attach.detach();
        }*/
        NettyServer.serverStart(8920);
    }
}


