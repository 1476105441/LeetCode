package review.树和图;

import senior.树和图.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class No1 {
    /**
     *      二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     */


    /*public int maxDepth(TreeNode root){
        return dfs(root);
    }

    public int dfs(TreeNode node){
        if (node == null) {
            return 0;
        }
        int left,right;

        left = dfs(node.left);
        right = dfs(node.right);

        return Math.max(left,right)+1;
    }*/

    public int maxDepth(TreeNode root){
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int count,res = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            count = queue.size();
            while (count > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }
            res++;
        }

        return res;
    }
}
