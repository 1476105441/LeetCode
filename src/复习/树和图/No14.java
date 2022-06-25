package 复习.树和图;

import 高级算法.树和图.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No14 {
    /**
     *      二叉树的最近公共祖先
     */

    /*int target1,target2;
    TreeNode res;
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        target1 = p.val;
        target2 = q.val;
        find(root);
        return res;
    }

    public boolean find(TreeNode cur){
        if (cur == null) {
            return false;
        }

        boolean flag1 = false,flag2 = false,flag3 = false;
        int count = 0;
        if (cur.val == target1 || cur.val == target2) {
            flag1 = true;
        }
        flag2 = find(cur.left);
        flag3 = find(cur.right);

        if (flag1) {
            count++;
        }
        if (flag2) {
            count++;
        }
        if (flag3) {
            count++;
        }

        if (count == 2) {
            res = cur;
            return false;
        }
        return flag1 || flag2 || flag3;
    }*/


    //使用哈希表，记录所有节点的父节点，然后从子节点开始往上找
    Map<Integer,TreeNode> map;
    Set<Integer> set;
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        map = new HashMap<>();
        set = new HashSet<>();

        dfs(root);
        while (p != null) {
            set.add(p.val);
            p = map.get(p.val);
        }

        while (q != null) {
            if (set.contains(q.val)) {
                return q;
            }
            q = map.get(q.val);
        }

        return null;
    }

    public void dfs(TreeNode cur){
        if (cur.left != null) {
            map.put(cur.left.val,cur);
            dfs(cur.left);
        }
        if (cur.right != null) {
            map.put(cur.right.val,cur);
            dfs(cur.right);
        }
    }
}
