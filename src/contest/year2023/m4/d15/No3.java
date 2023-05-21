package contest.year2023.m4.d15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 2641. 二叉树的堂兄弟节点 II
 *
 * @author wjs 2023/5/21
 */
public class No3 {
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>();
            int sum = 0;
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    list.add(node.left);
                    queue.add(node.left);
                    sum += node.left.val;
                } else {
                    list.add(null);
                }
                if (node.right != null) {
                    list.add(node.right);
                    queue.add(node.right);
                    sum += node.right.val;
                } else {
                    list.add(null);
                }
                size--;
            }
            for (int i = 0; i < list.size(); i += 2) {
                TreeNode node1 = list.get(i);
                TreeNode node2 = list.get(i + 1);
                int v1 = node1 == null ? 0 : node1.val;
                int v2 = node2 == null ? 0 : node2.val;
                if (node1 != null) {
                    node1.val = sum - v1 - v2;
                }
                if (node2 != null) {
                    node2.val = sum - v1 - v2;
                }
            }
        }
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
