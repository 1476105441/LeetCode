package 复习.树和图;

import 高级算法.树和图.TreeNode;

public class No5 {
    /**
     *      将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     * 提示：
     *     1 <= nums.length <= 104
     *     -104 <= nums[i] <= 104
     *     nums 按 严格递增 顺序排列
     */

    int[] nums;
    public TreeNode sortedArrayToBST(int[] nums){
        int left = 0,right = nums.length-1;
        this.nums = nums;
        TreeNode res = new TreeNode();
        recursion(res,left,right);
        return res;
    }

    public void recursion(TreeNode cur,int left,int right){
        if (left == right) {
            cur.val = nums[left];
            return;
        }

        int center = left + (right-left)/2;
        cur.val = nums[center];
        if (left <= center - 1) {
            cur.left = new TreeNode();
            recursion(cur.left,left,center-1);
        }
        if (right >= center + 1) {
            cur.right = new TreeNode();
            recursion(cur.right,center+1,right);
        }
    }
}
