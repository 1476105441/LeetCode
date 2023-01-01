package review.树和图;

import senior.树和图.TreeNode;

public class No10 {
    /**
     *      二叉搜索树中第K小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
     * 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     *
     * 提示：
     *     树中的节点数为 n 。
     *     1 <= k <= n <= 104
     *     0 <= Node.val <= 104
     *
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     */

    //思路：中序遍历
    int res,k,count;
    public int kthSmallest(TreeNode root,int k){
        this.k = k;
        res = 0;
        count = 1;
        findK(root);
        return res;
    }
    public void findK(TreeNode node){
        if (node == null) {
            return;
        }
        findK(node.left);
        if (count == k) {
            res = node.val;
            count++;
            return;
        } else if (count > k) {
            return;
        }
        count++;
        findK(node.right);
    }
}
