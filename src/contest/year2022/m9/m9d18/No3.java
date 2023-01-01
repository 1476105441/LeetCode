package contest.year2022.m9.m9d18;


import java.util.LinkedList;
import java.util.Queue;

public class No3 {
    /**
     *      反转二叉树的奇数层
     */

    public TreeNode reverseOddLevels(TreeNode root) {
        int c = 2;
        int[] nums = new int[1 << 14];
        TreeNode[] nodes = new TreeNode[1 << 14];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int s = queue.size(),l = 0;
            boolean flag = (c & 1) == 1;
            while (s > 0) {
                TreeNode node = queue.poll();
                if (flag) {
                    nums[l] = node.val;
                    nodes[l] = node;
                    l++;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                s--;
            }
            if (flag) {
                for (int i = 0; i < l; i++) {
                    nodes[l-1-i].val = nums[i];
                }
            }
            c++;
        }

        return root;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
