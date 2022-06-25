package 复习.树和图;

import 高级算法.树和图.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class No7 {
    /**
     *      二叉树的锯齿形层次遍历
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 提示：
     *     树中节点数目在范围 [0, 2000] 内
     *     -100 <= Node.val <= 100
     */

    //想法：两个堆栈，奇数层用奇数栈，偶数层用偶数栈，交替
    //次序将子树存入另一个栈，奇数层存入顺序是左右，偶数层
    //是右左
    /*public List<List<Integer>> zigzagLeveOrder(TreeNode root){
        Stack<TreeNode> s1 = new Stack<>(),s2 = new Stack<>();
        TreeNode node;
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            s1.push(root);
        }
        int count = 1;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if ((count & 1) == 1) {
                while (!s1.isEmpty()) {
                    node = s1.pop();
                    list.add(node.val);
                    if (node.left != null) {
                        s2.push(node.left);
                    }
                    if (node.right != null){
                        s2.push(node.right);
                    }
                }
            }else{
                while (!s2.isEmpty()) {
                    node = s2.pop();
                    list.add(node.val);
                    if (node.right != null) {
                        s1.push(node.right);
                    }
                    if (node.left != null) {
                        s1.push(node.left);
                    }
                }
            }
            res.add(list);
            count++;
        }
        return res;
    }*/

    //DFS是最优解
    //偶数层就正常放，奇数层就反着放
    List<List<Integer>> res;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        res = new ArrayList<>();
        dfs(root,0);
        return res;
    }

    public void dfs(TreeNode node,int depth){
        if (node == null) {
            return;
        }
        List<Integer> list;
        if (res.size() <= depth) {
            res.add(new ArrayList<>());
        }
        list = res.get(depth);
        if ((depth & 1) == 0) {
            list.add(node.val);
        }else
            list.add(0,node.val);

        dfs(node.left,depth+1);

        dfs(node.right,depth+1);
    }
}
