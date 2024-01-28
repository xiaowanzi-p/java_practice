package algorithm.practice.violence;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 汉罗塔
 */
public class hanluota {

    public static void main(String[] a) {
        process(Lists.newArrayList(1,2,3),"左边","右边","中间");
    }


    private static void process(List<Integer> list, String from, String to, String other) {
        //递归结束条件
        if (list.size() == 1) {
            System.out.println("将编号"+list.get(0)+"从 "+ from + " 移动到 " + to);
            return;
        }
        //将1~n-1从from移动到other
        process(list.subList(0,list.size()-1),from,other,to);
        //将第n个从from移动到to
        System.out.println("将编号"+list.get(list.size()-1)+"从 "+ from + " 移动到 " + to);
        //将1~n-1从other移动到to
        process(list.subList(0,list.size()-1),other,to,from);
    }
}
