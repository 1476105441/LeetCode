package review.树和图;

import senior.树和图.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class No3 {
    /**
     *      对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * 提示：
     *     树中节点数目在范围 [1, 1000] 内
     *     -100 <= Node.val <= 100
     *
     * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
     */

    //递归解决，如果一颗树的左右子树是对称的，那么这
    //棵树就是对称二叉树
    //----------------------------------------
    //上面那种方法是错误的，还是得要用双指针，分别遍历
    /*public boolean isSymmetric(TreeNode root){
        return examine(root.left, root.right);
    }*/
    //失败作品
    /*public Integer examine(TreeNode node){
        if (node == null) {
            return null;
        }

        Integer left,right;
        left = examine(node.left);
        right = examine(node.right);

        if(left != null && (!left.equals(right) || left == -101)){
            return -101;
        } else if (left == null) {
            if (right == null) {
                return node.val;
            }else
                return -101;
        }else
            return node.val;
    }*/
    /*public boolean examine(TreeNode left,TreeNode right){
        if (left == null) {
            return right == null;
        } else if (right == null) {
            return false;
        }

        boolean flag1,flag2;
        flag1 = examine(left.left,right.right);
        flag2 = examine(left.right,right.left);
        if (!flag1 || !flag2) {
            return false;
        }

        return left.val == right.val;
    }*/

    //迭代写法，两个堆栈
    /*public boolean isSymmetric(TreeNode root){
        Stack<TreeNode> left = new Stack<>(),right = new Stack<>();
        TreeNode node1 = root.left,node2 = root.right;

        while ((!left.isEmpty() && !right.isEmpty()) || node1 != null || node2 != null) {
            if (node1 == null && node2 == null) {
                node1 = left.pop();
                node2 = right.pop();
                node1 = node1.right;
                node2 = node2.left;
                continue;
            } else if (node1 == null || node2 == null) {
                return false;
            }

            if (node1.val != node2.val) {
                return false;
            }
            left.push(node1);
            right.push(node2);
            node1 = node1.left;
            node2 = node2.right;
        }

        return left.isEmpty() && right.isEmpty();
    }*/

    //使用异或来做两个不同时为空的判断
    /*public boolean isSymmetric(TreeNode root){
        Stack<TreeNode> left = new Stack<>(),right = new Stack<>();
        TreeNode node1 = root.left,node2 = root.right;

        while ((!left.isEmpty() && !right.isEmpty()) || node1 != null || node2 != null) {
            if (node1 == null ^ node2 == null) {
                return false;
            }
            if (node1 == null) {
                node1 = left.pop();
                node2 = right.pop();
                node1 = node1.right;
                node2 = node2.left;
            }else{
                if (node1.val != node2.val) {
                    return false;
                }
                left.push(node1);
                right.push(node2);
                node1 = node1.left;
                node2 = node2.right;
            }
        }

        return left.isEmpty() && right.isEmpty();
    }*/

    //使用队列
    public boolean isSymmetric(TreeNode root){
        Queue<TreeNode> left = new LinkedList<>(),right = new LinkedList<>();
        left.add(root.left);
        right.add(root.right);

        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode node1 = left.poll(),node2 = right.poll();

            if (node1 == null ^ node2 == null) {
                return false;
            }
            if (node1 != null) {
                if (node1.val != node2.val) {
                    return false;
                }
                left.add(node1.left);
                left.add(node1.right);
                right.add(node2.right);
                right.add(node2.left);
            }
        }

        return left.isEmpty() && right.isEmpty();
    }
}
