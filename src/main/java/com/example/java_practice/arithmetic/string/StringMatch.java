package com.example.java_practice.arithmetic.string;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringMatch {

    /**
     * @param source 主串
     * @param target 模式串
     * @return
     */
    public static int BF(String source, String target) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(target) || source.length() < target.length()) {
            return -1;
        }

        int sourceLength = source.length();
        int targetLength = target.length();
        //从第一个字符开始遍历截取相同长度的子串进行一一比较
        for (int i=0; i<=(sourceLength-targetLength); i++) {
            //截取模式串长度的字符串
            String substring = source.substring(i, i + targetLength);
            //截取子串和模式串比较
            if (substring.contentEquals(target)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * @param source 主串
     * @param target 模式串
     * @return
     */
    public static int RK(String source, String target) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(target) || source.length() < target.length()) {
            return -1;
        }

        int sourceLength = source.length();
        int targetLength = target.length();
        int intersection = 0; //交集的和
        Map<Character, Integer> charMap = charMap();
        Integer targetHash = calculateHash(target,charMap).get(0); //计算模式串哈希值

        //从第一个字符开始遍历截取相同长度的子串进行一一比较
        for (int i=0; i<=(sourceLength-targetLength); i++) {
            //截取模式串长度的字符串
            String substring = source.substring(i, i + targetLength);
            char[] chars = substring.toCharArray();

            //只有第一个需要计算哈希值，后续的子串通过交集计算出哈希值
            if (i == 0) {
                //计算子串哈希值，并截取交集方便后面子串的哈希值计算
                List<Integer> calculateHash = calculateHash(substring,charMap);
                Integer hash = calculateHash.get(0);
                intersection = calculateHash.get(1);
                //比较哈希值
                if (targetHash.equals(hash)) {
                    //在比较一次字符串相等防止哈希冲突
                    if (substring.contentEquals(target)) {
                        return i;
                    }
                }
            } else {
                //子串的哈希值 = 交集+子串最后一个字符的数字
                Integer hash = intersection + charMap.get(chars[chars.length-1]);
                //交集为哈希值减去子串首字符
                intersection = hash - charMap.get(chars[0]);
                //比较哈希值
                if (targetHash.equals(hash)) {
                    //在比较一次字符串相等防止哈希冲突
                    if (substring.contentEquals(target)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    //计算子串的哈希值和交集值
    private static List<Integer> calculateHash(String string,Map<Character, Integer> charMap) {
        char[] chars = string.toCharArray();
        Integer total = 0;
        for (char c : chars) {
            total = total + charMap.get(c);
        }
        Integer intersection = total - charMap.get(chars[0]);
        ArrayList<Integer> list = Lists.newArrayList(total, intersection);
        return list;
    }

    //a-z 26个字符分别对应0-25数字
    private static Map<Character,Integer> charMap() {
        Map<Character,Integer> map = new HashMap<>();
        map.put('a',0);
        map.put('b',1);
        map.put('c',2);
        map.put('d',3);
        map.put('e',4);
        map.put('f',5);
        map.put('g',6);
        map.put('h',7);
        map.put('i',8);
        map.put('j',9);
        map.put('k',10);
        map.put('l',11);
        map.put('m',12);
        map.put('n',13);
        map.put('o',14);
        map.put('p',15);
        map.put('q',16);
        map.put('r',17);
        map.put('s',18);
        map.put('t',19);
        map.put('u',20);
        map.put('v',21);
        map.put('w',22);
        map.put('x',23);
        map.put('y',24);
        map.put('z',25);
        return map;
    }


    public static int BM(String source, String target) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(target) || source.length() < target.length()) {
            return -1;
        }
        if (fitBadChar(source, target)) {
            return badChar(source,target);
        }
        return -1;
    }

    //是否适合坏字符规则
    private static boolean fitBadChar(String source, String target) {
        char sourceChar = source.charAt(source.length() - 1);
        char targetChar = target.charAt(target.length() - 1);
        if (sourceChar != targetChar) {
            return true;
        }
        return false;
    }


    //坏字符规则查找
    private static int badChar(String source, String target) {
        //初始化坏字符与下标哈希表
        Map<Character, Integer> badCharMap = badCharMap(target);
        int sourceLength = source.length();
        int targerLength = target.length();
        //比较的下标位置
        int beginIndex = 0;
        while (beginIndex <= (sourceLength-targerLength)) {
            //截取子串
            String substring = source.substring(beginIndex, beginIndex + targerLength);
            if (substring.contentEquals(target)) {
                return beginIndex;
            }
            //默认截取子串最后一个字符就是坏字符
            int badIndex = targerLength - 1;
            //确定坏串的比较位置
            char badChar = substring.charAt(substring.length() - 1);
            Integer compareIndex = badCharMap.getOrDefault(badChar, -1);
            //确定下标偏移量
            int offset = badIndex - compareIndex;
            beginIndex = beginIndex + offset;
        }
        return -1;
    }

    //预先将坏字符与其下标位置初始化到哈希表中
    private static Map<Character,Integer> badCharMap(String target) {
        char[] chars = target.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (char ch : chars) {
            int index = 0;
            //多个相同字符取下标最大的
            for (int i=0; i<chars.length; i++) {
                char c = chars[i];
                if (c == ch) {
                    index = i;
                }
            }
            map.put(ch,index);
        }
        return map;
    }
}
