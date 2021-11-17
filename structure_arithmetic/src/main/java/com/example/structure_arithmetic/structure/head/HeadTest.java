package com.example.structure_arithmetic.structure.head;

import com.alibaba.fastjson.JSONObject;

public class HeadTest {

    public static void main(String[] a) {
        SmallHead<Integer> smallHead = new SmallHead<>(10);
        smallHead.add(2);
        smallHead.add(1);
        smallHead.add(4);
        smallHead.add(8);
        smallHead.add(7);
        smallHead.add(3);
        System.out.println(JSONObject.toJSONString(smallHead.getArray()));
        Integer remove = smallHead.remove();
        System.out.println(remove);
        System.out.println(JSONObject.toJSONString(smallHead.getArray()));
    }
}
