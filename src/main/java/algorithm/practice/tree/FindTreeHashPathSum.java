package algorithm.practice.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 判断树是否有目标值的累加和路径
 * 并找出路径
 *
 * @author 君墨笑
 * @date 2023/12/5
 */
public class FindTreeHashPathSum {


    private static List<List<Integer>> result = new ArrayList<>();

    private static List<List<Integer>> findPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        process(root,target,0,null);
        return result;
    }


    private static void process(TreeNode root, int target, int preSum, List<Integer> preList) {
        List<Integer> list = new ArrayList<>();
        if (preList != null) {
            for (Integer i : preList) {
                list.add(i);
            }
        }
        if (root == null) {
            if (preSum == target) {
                result.add(list);
            }
            return;
        }
        list.add(root.data);
        if (root.right == null && root.left == null) {
            if ((root.data + preSum) == target) {
                result.add(list);
            }
            return;
        }
        preSum = preSum + root.data;
        process(root.left,target,preSum,list);
        process(root.right,target,preSum,list);
    }
}
