package contest.year2023.m2.d18;

import dataStructure.segmentTree.No731;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    /*Node root;
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        root = new Node();
        int n = nums1.length;
        long cnt = 0;
        root.l = 0;
        root.r = n;
        List<Long> resList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] == 1) {
                update(root,0,n,i,i);
            }
            cnt += nums2[i];
        }
        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int x = queries[i][0],y = queries[i][1],z = queries[i][2];
            if (x == 1) {
                update(root,0,n,y,z);
            } else if (x == 2) {
                cnt += (long) query(root,0,n,0,n) * y;
            } else {
                resList.add(cnt);
            }
        }
        long[] res = new long[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    class Node{
        Node left,right;
        int val,add;
        int l,r;
    }
    public void update(Node node, int start, int end, int l, int r){
        pushDown(node);
        if(start >= l && end <= r){
            node.val = end - start + 1 - node.val;
            node.add = 1;
            return;
        }
        int c = start+((end-start)>>1);
        if(c >= l) update(node.left,start,c,l,r);
        if(c < r) update(node.right,c+1,end,l,r);
        node.val = node.left.val + node.right.val;
    }
    public int query(Node node, int start, int end, int l, int r){
        pushDown(node);
        if(start >= l && end <= r){
            return node.val;
        }
        int c = start+((end-start)>>1),res = 0;
        if(c >= l) res = query(node.left,start,c,l,r);
        if(c < r) res += query(node.right,c+1,end,l,r);
        return res;
    }
    public void pushDown(Node node){
        int c = node.l + ((node.r - node.l) >> 1);
        if (node.left == null) {
            node.left = new Node();
            node.left.l = node.l;
            node.left.r = c;
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.l = c+1;
            node.right.r = node.r;
        }
        if(node.add == 0) return;
        node.left.val = node.left.r - node.left.l + 1 - node.left.val;
        node.right.val = node.right.r - node.right.l + 1 - node.right.val;
        node.left.add = 1;
        node.right.add = 1;
        node.add = 0;
    }*/

    //成功了，上面失败的主要原因是因为add可能多次被修改，而原来的代码里没有体现这种多次修改性
    //但效率很低，900多ms
    /*Node root;
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        root = new Node();
        int n = nums1.length;
        long cnt = 0;
        root.l = 0;
        root.r = n;
        List<Long> resList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] == 1) {
                update(root,0,n,i,i);
            }
            cnt += nums2[i];
        }
        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int x = queries[i][0],y = queries[i][1],z = queries[i][2];
            if (x == 1) {
                update(root,0,n,y,z);
            } else if (x == 2) {
                cnt += (long) query(root,0,n,0,n) * y;
            } else {
                resList.add(cnt);
            }
        }
        long[] res = new long[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    class Node{
        Node left,right;
        int val,add;
        int l,r;
    }
    public void update(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            node.val = end - start + 1 - node.val;
            node.add++;
            return;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        if(c >= l) update(node.left,start,c,l,r);
        if(c < r) update(node.right,c+1,end,l,r);
        node.val = node.left.val + node.right.val;
    }
    public int query(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            return node.val;
        }
        pushDown(node);
        int c = start+((end-start)>>1),res = 0;
        if(c >= l) res = query(node.left,start,c,l,r);
        if(c < r) res += query(node.right,c+1,end,l,r);
        return res;
    }
    public void pushDown(Node node){
        int c = node.l + ((node.r - node.l) >> 1);
        if (node.left == null) {
            node.left = new Node();
            node.left.l = node.l;
            node.left.r = c;
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.l = c+1;
            node.right.r = node.r;
        }
        while (node.add != 0) {
            node.left.val = node.left.r - node.left.l + 1 - node.left.val;
            node.right.val = node.right.r - node.right.l + 1 - node.right.val;
            node.left.add++;
            node.right.add++;
            node.add--;
        }
    }*/

    //改为布尔值，总算成功了，64ms
    /*Node root;
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        root = new Node();
        int n = nums1.length;
        long cnt = 0;
        root.l = 0;
        root.r = n;
        List<Long> resList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] == 1) {
                update(root,0,n,i,i);
            }
            cnt += nums2[i];
        }
        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int x = queries[i][0],y = queries[i][1],z = queries[i][2];
            if (x == 1) {
                update(root,0,n,y,z);
            } else if (x == 2) {
                cnt += (long) query(root,0,n,0,n) * y;
            } else {
                resList.add(cnt);
            }
        }
        long[] res = new long[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    class Node{
        Node left,right;
        int val,l,r;
        boolean add;
    }
    public void update(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            node.val = end - start + 1 - node.val;
            node.add = !node.add;
            return;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        if(c >= l) update(node.left,start,c,l,r);
        if(c < r) update(node.right,c+1,end,l,r);
        node.val = node.left.val + node.right.val;
    }
    public int query(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            return node.val;
        }
        pushDown(node);
        int c = start+((end-start)>>1),res = 0;
        if(c >= l) res = query(node.left,start,c,l,r);
        if(c < r) res += query(node.right,c+1,end,l,r);
        return res;
    }
    public void pushDown(Node node){
        int c = node.l + ((node.r - node.l) >> 1);
        if (node.left == null) {
            node.left = new Node();
            node.left.l = node.l;
            node.left.r = c;
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.l = c+1;
            node.right.r = node.r;
        }
        if(!node.add) return;
        node.left.val = node.left.r - node.left.l + 1 - node.left.val;
        node.right.val = node.right.r - node.right.l + 1 - node.right.val;
        node.left.add = !node.left.add;
        node.right.add = !node.right.add;
        node.add = false;
    }*/

    Node root;
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        root = new Node();
        int n = nums1.length;
        long cnt = 0;
        root.l = 0;
        root.r = n;
        List<Long> resList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] == 1) {
                update(root,0,n,i,i);
            }
            cnt += nums2[i];
        }
        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int x = queries[i][0],y = queries[i][1],z = queries[i][2];
            if (x == 1) {
                update(root,0,n,y,z);
            } else if (x == 2) {
                cnt += (long) query(root,0,n,0,n) * y;
            } else {
                resList.add(cnt);
            }
        }
        long[] res = new long[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    class Node{
        Node left,right;
        int val,l,r,add;
    }
    public void update(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            node.val = end - start + 1 - node.val;
            node.add++;
            return;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        if(c >= l) update(node.left,start,c,l,r);
        if(c < r) update(node.right,c+1,end,l,r);
        node.val = node.left.val + node.right.val;
    }
    public int query(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            return node.val;
        }
        pushDown(node);
        int c = start+((end-start)>>1),res = 0;
        if(c >= l) res = query(node.left,start,c,l,r);
        if(c < r) res += query(node.right,c+1,end,l,r);
        return res;
    }
    public void pushDown(Node node){
        int c = node.l + ((node.r - node.l) >> 1);
        if (node.left == null) {
            node.left = new Node();
            node.left.l = node.l;
            node.left.r = c;
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.l = c+1;
            node.right.r = node.r;
        }
        if((node.add & 1) == 0) return;
        node.left.val = node.left.r - node.left.l + 1 - node.left.val;
        node.right.val = node.right.r - node.right.l + 1 - node.right.val;
        node.left.add++;
        node.right.add++;
        node.add = 0;
    }
}
