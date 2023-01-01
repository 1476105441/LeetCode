package review.树和图;

import senior.树和图.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class No6 {
    /**
     *      二叉树的中序遍历
     * 给定一个二叉树的根节点root，返回它的中序遍历
     *
     * 提示：
     *     树中节点数目在范围 [0, 100] 内
     *     -100 <= Node.val <= 100
     *
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    //解法一：递归
    /*List<Integer> res;
    public List<Integer> inorderTraversal(TreeNode root){
        res = new ArrayList<>();
        dfs(root);
        return res;
    }
    public void dfs(TreeNode node){
        if (node == null) {
            return;
        }

        dfs(node.left);
        res.add(node.val);
        dfs(node.right);
    }*/

    //迭代
    /*public List<Integer> inorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }*/

    //Morris算法
    public List<Integer> inorderTraversal(TreeNode root){
        TreeNode cur = root,pre = null,search;
        List<Integer> res = new ArrayList<>();
        while (cur != null) {
            if (cur.left != null) {
                search = cur.left;
                while (search != null && search != cur) {
                    pre = search;
                    search = search.right;
                }
                if (search == cur) {
                    res.add(cur.val);
                    cur = cur.right;
                }else{
                    pre.right = cur;
                    cur = cur.left;
                }
            }else{
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}
