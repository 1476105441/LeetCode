package daily.year2024.m7;

/**
 * 线段树解法
 *
 * @author wjs 2024/8/1
 */
public class d31 {
    class Solution {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            Node root = new Node();
            getVal(nums,0,n-1,root);
            return root.mSum;
        }

        private void getVal(int[] nums,int l,int r,Node node) {
            if(l == r) {
                node.lSum = nums[l];
                node.rSum = nums[l];
                node.mSum = nums[l];
                node.iSum = nums[l];
                return;
            }
            if(node.left == null) {
                node.left = new Node();
            }
            if(node.right == null) {
                node.right = new Node();
            }
            int c = l + ((r-l) >> 1);
            getVal(nums,l,c,node.left);
            getVal(nums,c+1,r,node.right);
            node.iSum = node.left.iSum + node.right.iSum;
            node.lSum = node.left.lSum;
            if(node.lSum < node.left.iSum + node.right.lSum) {
                node.lSum = node.left.iSum + node.right.lSum;
            }
            node.rSum = node.right.rSum;
            if(node.rSum < node.right.iSum + node.left.rSum) {
                node.rSum = node.right.iSum + node.left.rSum;
            }
            node.mSum = node.left.mSum;
            if(node.mSum < node.right.mSum) {
                node.mSum = node.right.mSum;
            }
            if(node.mSum < node.left.rSum + node.right.lSum) {
                node.mSum = node.left.rSum + node.right.lSum;
            }
        }
    }
    class Node {
        int lSum,rSum,mSum,iSum;
        Node left,right;
    }
}
