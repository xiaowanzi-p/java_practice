package algorithm.practice.violence;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.arithmetic.common.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class StringCollection {


    public static void main(String[] a) {
        String str = "abc";
        List<String> list = process(str.toCharArray(), 0, new ArrayList<>());
        System.out.println(JSONObject.toJSONString(list));
    }


    /**
     * 找出所有字符串的子序列
     */
    private static List<String> process(char[] str, int index, String path, List<String> list) {
        //递归结束条件
        if (index == str.length) {
            list.add(path);
            return list;
        }
        //当前这个字符要
        String yes = path + str[index];
        process(str,index+1,yes,list);
        //当前这个字符不要
        String no = path;
        process(str,index+1,no,list);
        return list;
    }


    private static List<String> process(char[] str, int index, List<String> list) {
        if (index == str.length) {
            list.add(String.copyValueOf(str));
            return list;
        }
        for (int i=index; i<str.length; i++) {
            CommonUtil.swap(str,i,index);
            process(str,index +1,list);
            CommonUtil.swap(str,i,index);
        }
        return list;
    }

}
