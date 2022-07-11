package 周赛.m7d9;

public class No1 {
    /**
     *      计算布尔二叉树的值
     */

    public boolean evaluateTree(TreeNode root) {
        int res = count(root);
        return res == 1;
    }
    public int count(TreeNode node){
        if (node == null) {
            return -1;
        }
        int l,r;
        l = count(node.left);
        r = count(node.right);

        if (l == -1) {
            return node.val;
        }
        if (node.val == 2) {
            return l | r;
        }
        return l & r;
    }
}
