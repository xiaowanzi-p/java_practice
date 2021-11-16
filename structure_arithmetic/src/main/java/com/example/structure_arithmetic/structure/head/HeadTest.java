package com.example.structure_arithmetic.structure.head;

import com.alibaba.fastjson.JSONObject;

public class HeadTest {

    public static void main(String[] a) {
        BigHead<Integer> bigHead = new BigHead<>(10);
        bigHead.add(2);
        bigHead.add(1);
        bigHead.add(4);
        bigHead.add(8);
        bigHead.add(7);
        bigHead.add(3);
        System.out.println(JSONObject.toJSONString(bigHead.getArray()));
        Integer remove = bigHead.remove();
        System.out.println(remove);
        System.out.println(JSONObject.toJSONString(bigHead.getArray()));
    }
}
