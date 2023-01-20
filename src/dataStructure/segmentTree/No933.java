package dataStructure.segmentTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class No933 {

}

//解法一：线段树，106ms，效率较低
/*
class RecentCounter {
    Node root;
    int r = 1000000000;
    public RecentCounter() {
        root = new Node();
    }

    public int ping(int t) {
        update(root,0,r,t,t);
        return query(root,0,r,t-3000,t);
    }

    class Node{
        Node left,right;
        int val,add;
    }

    private void update(Node node,int left,int right,int start,int end){
        if (start <= left && right <= end) {
            node.val += 1;
            node.add = 1;
            return;
        }
        pushDown(node);
        int c = left + ((right-left) >> 1);
        if (c >= start) {
            update(node.left,left,c,start,end);
        }
        if (c < end) {
            update(node.right,c+1,right,start,end);
        }
        node.val = node.left.val + node.right.val;
    }

    private int query(Node node,int left,int right,int start,int end){
        if (start <= left && right <= end) {
            return node.val;
        }
        pushDown(node);
        int c = left + ((right-left) >> 1),res = 0;
        if (c >= start) {
            res = query(node.left,left,c,start,end);
        }
        if (c < end) {
            res += query(node.right,c+1,right,start,end);
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
        node.left.val += node.add;
        node.left.add = node.add;
        node.right.val += node.add;
        node.right.add = node.add;
        node.add = 0;
    }
}*/

//解法二：暴力，183ms，效率更低
/*
class RecentCounter {
    int[] nums;
    int index;
    public RecentCounter() {
        index = 0;
        nums = new int[10000];
    }

    public int ping(int t) {
        nums[index++] = t;
        int res = 0,limit = t - 3000;
        for (int i = Math.max(index - 3001, 0); i < index; i++) {
            if (nums[i] >= limit) {
                res++;
            }
        }
        return res;
    }
}*/

//解法三：使用队列，19ms
/*
class RecentCounter {
    Queue<Integer> queue;
    public RecentCounter() {
        //queue = new LinkedList<>();
        queue = new ArrayDeque<>(); //时间没有提升，但是空间利用率提升了
    }

    public int ping(int t) {
        int limit = t - 3000;
        while (!queue.isEmpty() && queue.peek() < limit) {
            queue.poll();
        }
        queue.add(t);
        return queue.size();
    }
}*/

//暴力解法改进，16ms，击败百分之百
class RecentCounter {
    int[] nums;
    int index,start;
    public RecentCounter() {
        index = 0;
        start = 0;
        nums = new int[10000];
    }

    public int ping(int t) {
        nums[index++] = t;
        int limit = t - 3000,i;
        for ( i = Math.max(index - 3001, start); i < index; i++) {
            if (nums[i] >= limit) {
                break;
            }
        }
        start = i;
        return index - i;
    }
}