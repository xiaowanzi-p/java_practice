package com.example.structure_arithmetic.arithmetic.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTest {

    public static void main(String[] a) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(1);
        list.add(3);
        list.add(8);
        QuickSort.sort(list);
        System.out.println(JSONObject.toJSONString(list));
    }
}
