package dataStructure.segmentTree;

public class No307 {
}

//解法一：线段树，133ms，效率很低
/*
class NumArray {
    int n;
    Node root;
    public NumArray(int[] nums) {
        root = new Node();
        n = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            updateNode(root,0,n,i,i,nums[i]);
        }
    }

    public void update(int index, int val) {
        updateNode(root,0,n,index,index,val);
    }

    public int sumRange(int left, int right) {
        return queryNode(root,0,n,left,right);
    }

    class Node{
        Node left,right;
        int val,add;
    }

    private void updateNode(Node node,int left,int right,int start,int end,int val){
        if (start <= left && right <= end) {
            node.val = val;
            node.add = val;
            return;
        }
        pushDown(node);
        int c = left + ((right - left) >> 1);
        if (c >= start) {
            updateNode(node.left,left,c,start,end,val);
        }
        if (c < end) {
            updateNode(node.right,c+1,right,start,end,val);
        }
        node.val = node.left.val + node.right.val;
    }
    private int queryNode(Node node,int left,int right,int start,int end){
        if (start <= left && right <= end) {
            return node.val;
        }
        pushDown(node);
        int c = left + ((right - left) >> 1),res = 0;
        if (c >= start) {
            res = queryNode(node.left,left,c,start,end);
        }
        if (c < end) {
            res += queryNode(node.right,c+1,right,start,end);
        }
        return res;
    }
    private void pushDown(Node node){
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.add == 0) {
            return;
        }
        node.left.val = node.add;
        node.left.add = node.add;
        node.right.val = node.add;
        node.right.add = node.add;
        node.add = 0;
    }
}*/

//解法二：暴力前缀和，940ms，效率更垃圾
/*
class NumArray {
    int[] nums,prefix;
    public NumArray(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }
    }

    public void update(int index, int val) {
        int dif = val - nums[index];
        nums[index] = val;
        for (int i = index; i < nums.length; i++) {
            prefix[i] += dif;
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right] - prefix[left] + nums[left];
    }
}*/

//解法三：树状数组，72ms
class NumArray {
    int[] nums, tree;
    int n;

    public NumArray(int[] nums) {
        n = nums.length + 1;
        this.nums = nums;
        tree = new int[n];
        for (int i = 0; i < nums.length; i++) {
            updateTree(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int dif = val - nums[index];
        nums[index] = val;
        if (dif != 0) {
            updateTree(index + 1, dif);
        }
    }

    public int sumRange(int left, int right) {
        int l = query(left + 1);
        int r = query(right + 1);
        return r - l + nums[left];
    }

    private void updateTree(int index, int dif) {
        while (index < n) {
            tree[index] += dif;
            index += (index & (-index));
        }
    }

    private int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= (index & (-index));
        }
        return sum;
    }
}