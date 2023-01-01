package senior.树和图;

import java.util.HashMap;
import java.util.Map;

public class No3 {
    /*
     *          二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最
     * 近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可
     * 能大（一个节点也可以是它自己的祖先）。”
     *
     * 提示：
     *     树中节点数目在范围 [2, 105] 内。
     *     -109 <= Node.val <= 109
     *     所有 Node.val 互不相同 。
     *     p != q
     *     p 和 q 均存在于给定的二叉树中。
     */

    /*
     * 两次遍历法，先遍历一遍寻找任意一个节
     * 点，找到后返回，并将其父节点都标记上，
     * 然后再遍历第二遍寻找第二个节点
     *
     * 8ms，效率太低
     */
    /*TreeNode res;
    Map<TreeNode,Integer> map;
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        map = new HashMap<>();

        find(p.val,root);
        find(q.val,root);

        return res;
    }

    public boolean find(int val,TreeNode node){
        if (node == null) {
            return false;
        }
        if (val == node.val) {
            if (map.get(node) != null) {
                res = node;
                return false;
            }
            map.put(node,1);
            return true;
        }
        if(find(val,node.left)||find(val,node.right)){
            if (map.get(node) != null) {
                res = node;
                return false;
            }
            map.put(node,1);
            return true;
        }

        return false;
    }*/


    /**
     * 有没有更高效的算法？
     */
    TreeNode res;
    Map<TreeNode, Integer> map;
    boolean flag1, flag2;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        map = new HashMap<>();
        find(p.val, q.val, root);

        return res;
    }

    //同时找两个
    public boolean find(int val1, int val2, TreeNode node) {
        if (node == null) {
            return false;
        }

        boolean flag = false, left, right;

        left = find(val1, val2, node.left);
        right = find(val1, val2, node.right);
        //如果左右子树返回true则说明当前节点是其中一个结果的父节点
        if (((node.val == val1 || node.val == val2) && (left || right)) || left && right) {
            res = node;
            return false;
        } else if (left || right||node.val == val1 || node.val == val2) {
            flag =true;
        }

        return flag;
    }
}
