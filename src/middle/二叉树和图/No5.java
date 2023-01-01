package middle.二叉树和图;

public class No5 {
    //              二叉搜索树中第K小的元素
    //  给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
    //  进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？

    //二叉搜索树是左小右大的数，所以最小的节点一定是最左边的节点
    int count;
    int res;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        recursion(root,k);
        return res;
    }

    public void recursion(TreeNode node, int k){
        if (node == null) {
            return;
        }

        recursion(node.left,k);

        count++;

        if (count == k) {
            res = node.val;
            count++;
            return;
        }

        recursion(node.right,k);
    }
}
