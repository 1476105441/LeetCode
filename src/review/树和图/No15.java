package review.树和图;

import senior.树和图.TreeNode;

public class No15 {
    /**
     * 二叉树中的最大路径和
     */
    int max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        findMax(root);
        return max;
    }

    public int findMax(TreeNode cur) {
        if (cur == null) {
            return 0;
        }

        int m = cur.val, lv, rv;
        lv = findMax(cur.left);
        rv = findMax(cur.right);

        if (cur.val + lv > m) {
            m = cur.val + lv;
        }
        if (cur.val + rv > m) {
            m = cur.val + rv;
        }

        if (m > max) {
            max = m;
        }
        if (cur.val + lv + rv > max) {
            max = cur.val + lv + rv;
        }
        return m;
    }
}
