package 高级算法.树和图;

public class No4 {
    /**
     *          二叉树中的最大路径和
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和
     */

    /**
     * 想法：使用递归的做法，计算每个节点的
     * 经过该节点的最大路径值
     */
    int res;
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        recursion(root);
        return res;
    }

    public int recursion(TreeNode node){
        if (node == null) {
            return 0;
        }
        int left,right,temp;
        left = recursion(node.left);
        right = recursion(node.right);

        temp = node.val + left + right;
        if (temp > res) {
            res = temp;
        }
        left += node.val;
        right += node.val;

        if (left > res) {
            res = left;
        }
        if (right > res) {
            res = right;
        }
        if (node.val > res) {
            res = node.val;
        }

        if (left > right) {
            if(left > node.val){
                return left;
            }else{
                return node.val;
            }
        }else{
            if(right > node.val){
                return right;
            }else{
                return node.val;
            }
        }
    }
}
