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


    public void test() {
        //估算价格，处理操作
        int price = 100;
        if (price >80 && price <100) {

        } else {
            if (price <60 && price > 40) {

            }
            if (price < 40) {

            }
        }
    }


    public void test01() {
        int price = 100;
        String appraisement = appraisement(price);
        switch (appraisement) {
            case "CHEAP":
                break;
            case "EXPENSIVE":
                break;
            case "MIDDLE":
                break;
            default:
        }
    }


    private String appraisement(int pice) {
        return null;
    }

}


