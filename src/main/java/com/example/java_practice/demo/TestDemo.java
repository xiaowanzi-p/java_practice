package com.example.java_practice.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.BoundType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class TestDemo {

    static {
        System.out.println("TestDemo开始加载");
    }

    public static void main(String[] args) {
        /*Range<Integer> r1 = Range.upTo(1, BoundType.CLOSED);
        Range<Integer> r2 = Range.range(1, BoundType.OPEN, 2, BoundType.CLOSED);
        Range<Integer> r3 = Range.range(2, BoundType.OPEN, 4, BoundType.CLOSED);
        Range<Integer> r4 = Range.downTo(4, BoundType.CLOSED);
        HashMap<String, Integer> map = new HashMap<>();
        map.put(r1.toString(),6);
        map.put(r2.toString(),4);
        map.put(r3.toString(),3);
        map.put(r4.toString(),2);
        System.out.println(JSONObject.toJSONString(map));*/
        //{"(-∞,1]":6,"(1,2]":4,"(2,4]":3,"[4,+∞)":2}

        String slidingPrice = "{\"(-∞,1]\":7.5,\"(1,2]\":6,\"(2,4]\":5.5,\"[4,+∞)\":4}";
        Map<String,Object> map = JSONObject.parseObject(slidingPrice, Map.class);
        Map<Range<Integer>,BigDecimal> range = new HashMap<>();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof BigDecimal) {
                range.put(toRange(entry.getKey()),(BigDecimal) value);
            }
            if (value instanceof Integer) {
                BigDecimal bigDecimal = BigDecimal.valueOf(Long.valueOf(value.toString()));
                range.put(toRange(entry.getKey()),bigDecimal);
            }


        }
    }

    private static void demo() {
        System.out.println("result is 100");
    }


    private static Range<Integer> toRange(String string) {
        String[] split = string.split(",");
        String lowStr = split[0];
        String upStr = split[1];

        BoundType lowBoundType;
        boolean belowAll = false;
        Integer lowEndpoint = null;
        BoundType upBoundType;
        boolean aboveAll = false;
        Integer upEndpoint = null;
        if (lowStr.contains("(")) {
            lowBoundType = BoundType.OPEN;
        } else {
            lowBoundType = BoundType.CLOSED;
        }
        if (upStr.contains(")")) {
            upBoundType = BoundType.OPEN;
        } else {
            upBoundType = BoundType.CLOSED;
        }
        if (lowStr.contains("-∞")) {
            belowAll = true;
        }
        if (upStr.contains("+∞")) {
            aboveAll = true;
        }
        if (!belowAll) {
            lowEndpoint = Integer.valueOf(lowStr.substring(1,lowStr.length()));
        }
        if (!aboveAll) {
            upEndpoint = Integer.valueOf(upStr.substring(0,upStr.length()-1));
        }
        if (belowAll) {
            return Range.upTo(upEndpoint,upBoundType);
        }
        if (aboveAll) {
            return Range.downTo(lowEndpoint,lowBoundType);
        }
        return Range.range(lowEndpoint,lowBoundType,upEndpoint,upBoundType);
    }
}
