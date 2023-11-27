package algorithm.practice.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 多叉树节点
 *
 * @author 君墨笑
 * @date 2023/11/27
 */
@Getter
@Setter
public class MultiTreeNode {

    private int data;

    private List<MultiTreeNode> nodes;
}
