package com.example.java_practice.demo;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.IdentityHashMap;

public class TestDemo {

    static {
        System.out.println("TestDemo开始加载");
    }

    public static void main(String[] args) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
        ZonedDateTime parse = ZonedDateTime.parse("2022-01-11 06:00:08",pattern);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(parse.toInstant(), ZoneId.of("Asia/Shanghai"));
        System.out.println(1);

    }

    private static void demo() {
        System.out.println("result is 100");
    }
}
