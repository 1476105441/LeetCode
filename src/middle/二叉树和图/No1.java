package middle.二叉树和图;

import java.util.ArrayList;
import java.util.List;

public class No1 {
    //                      二叉树的中序遍历
    //给定一个二叉树的根节点 root ，返回它的 中序 遍历。

    //--------------------------------------------------------------------
    //                      递归
    /*List<Integer> res;
    public List<Integer> inorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        recursion(root);
        return res;
    }

    public void recursion(TreeNode root){
        if (root == null) {
            return;
        }

        recursion(root.left);

        res.add(root.val);

        recursion(root.right);
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //                      堆栈
    /*public List<Integer> inorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;

        if (root == null) {
            return list;
        }

        stack.push(root);
        while(node != null || !stack.empty()){
             while(node != null && node.left != null) {
               stack.push(node.left);
               node = node.left;
             }

             node = stack.pop();
             list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            node = node.right;
        }

        return list;
    }*/
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //                  Morris中序遍历
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        //node是用于遍历的指针，pre是寻找的前驱结点
        TreeNode node = root,pre;

        while (node != null) {
            //没有左子树时
            if (node.left == null) {
                res.add(node.val);
                node = node.right;
            }else{
                //有左子树时需要寻找前驱结点
                pre = node.left;
                //前驱结点是当前节点node的左子树中最右边的节点，但要注意可能是已经处理过的前驱结点，即右子树已经指向node节点了
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                //判断前驱结点的右子树是否为空
                if (pre.right != null) {
                    res.add(node.val);
                    node = node.right;
                }else{
                    pre.right = node;
                    node = node.left;
                }
            }
        }

        return res;
    }
}
