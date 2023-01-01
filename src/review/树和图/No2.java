package review.树和图;

import senior.树和图.TreeNode;

import java.util.Stack;

public class No2 {
    /**
     *      验证二叉树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     *     节点的左子树只包含 小于 当前节点的数。
     *     节点的右子树只包含 大于 当前节点的数。
     *     所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 提示：
     *     树中节点数目范围在[1, 104] 内
     *     -231 <= Node.val <= 231 - 1
     */

    /*public boolean isValidBST(TreeNode root){
        return valid(root,Integer.MAX_VALUE+1L, Integer.MIN_VALUE-1L);
    }

    public boolean valid(TreeNode node,long max,long min){
        if (node == null) {
            return true;
        }

        if (node.val >= max || node.val <= min) {
            return false;
        }

        return valid(node.left,node.val,min) && valid(node.right,max,node.val);
    }*/

    //中序遍历呀，中序遍历按顺序遍历的话
    /*TreeNode pre;
    public boolean isValidBST(TreeNode root){
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (pre != null && root.val <= pre.val) {
            return false;
        } else {
            pre = root;
        }

        return isValidBST(root.right);
    }*/

    //非递归的中序遍历
    public boolean isValidBST(TreeNode root){
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root,pre = null;
        stack.push(cur);
        cur = cur.left;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if (pre != null && cur.val <= pre.val) {
                return false;
            }else
                pre = cur;

            cur = cur.right;
        }

        return true;
    }
}
