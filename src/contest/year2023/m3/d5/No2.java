package contest.year2023.m3.d5;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class No2 {

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

class Solution {
    //层序遍历+最小堆
    public long kthLargestLevelSum(TreeNode root, int k) {
        Deque<TreeNode> queue = new LinkedList<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long val = 0;
            while (size > 0) {
                TreeNode node = queue.poll();
                val += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            if (pq.size() < k) {
                pq.add(val);
            } else {
                if (pq.peek() < val) {
                    pq.poll();
                    pq.add(val);
                }
            }
        }
        if (pq.size() == k) {
            return pq.peek();
        }
        return -1;
    }
}
