package 复习.树和图;

import 高级算法.树和图.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    /**
     *      二叉树的层次遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     *
     * 提示：
     *     树中节点数目在范围 [0, 2000] 内
     *     -1000 <= Node.val <= 1000
     */

    /*public List<List<Integer>> levelOrder(TreeNode node){
        List<List<Integer>> res = new ArrayList<>();
        if (node == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int count,i;
        queue.add(node);

        while (!queue.isEmpty()) {
            count = queue.size();
            i = 0;
            List<Integer> list = new ArrayList<>();
            while (i < count) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
                i++;
            }
            res.add(list);
        }

        return res;
    }*/

    //递归的解决
    List<List<Integer>> res;
    public List<List<Integer>> levelOrder(TreeNode root){
        res = new ArrayList<>();
        dfs(root,0);
        return res;
    }

    public void dfs(TreeNode cur,int depth){
        if (cur == null) {
            return;
        }
        List<Integer> list;
        if (depth == res.size()) {
            list = new ArrayList<>();
            res.add(depth,list);
        }else
             list = res.get(depth);
        list.add(cur.val);
        dfs(cur.left,depth+1);
        dfs(cur.right,depth+1);
    }
}
