package junior.二叉树;

public class No5 {
    //                 将有序数组转换为二叉搜索树
    //给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
    //
    //高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

    //平衡二叉树
    //想法：因为是有序数组，直接取中间的元素作根节点，后续的左左子树根节点取左半区的中间元素，右子树根节点取右半区的中间元素
    //成功，击败百分百
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return new TreeNode();
        }

        TreeNode root;
        root = recursion(nums,0,nums.length-1);
        return root;
    }

    public TreeNode recursion(int[] nums,int low,int high){
        TreeNode node;
        node = new TreeNode();

        //回溯条件
        if (low == high) {
            node.val = nums[high];
            return node;
        }

        //中间元素的位置
        int center = (low + high) / 2;

        node.val = nums[center];

        if (center != low) {
            node.left = recursion(nums,low,center-1);
        }
        if (center != high) {
            node.right = recursion(nums,center+1,high);
        }

        return node;
    }
}
