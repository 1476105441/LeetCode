package review.中级算法复习.树和图;

import java.util.Stack;

public class Mid_tree5 {
    //              二叉搜索树中第k小的元素

    //  思考：二叉搜索树，即为左子树比当前节点的值小，右子树比当前节点的值大
    //的二叉树。要寻找其中第k小的元素，则中序遍历的第k个节点就是，所以也就是
    //中序遍历？  答案是肯定的，就是中序遍历
    public int kthSmallest(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int count = 0,res = -1;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            count++;
            if (count == k) {
                res = node.val;
                break;
            }
            node = node.right;
        }

        return res;
    }
}
