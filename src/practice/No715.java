package practice;

import java.util.ArrayList;
import java.util.List;

public class No715 {
    /**
     *      Range 模块
     */

    //线段树，111ms
    /*class RangeModule {
        Node root = new Node();
        int N = 1000000000;
        public RangeModule() {

        }

        public void addRange(int left, int right) {
            update(root,0,N,left,right-1,1);
        }

        public boolean queryRange(int left, int right) {
            int res = query(root,0,N,left,right-1);
            return res == 1;
        }

        public void removeRange(int left, int right) {
            update(root,0,N,left,right-1,2);
        }

        class Node{
            Node left,right;
            int val,add;
            public Node(){
                val = 2;
            }
        }
        //1表示true，2表示false
        public void update(Node node,int start,int end,int l,int r,int val){
            if(start >= l && end <= r){
                node.val = val;
                node.add = val;
                return;
            }
            pushDown(node);
            int c = start + ((end-start) >> 1);
            if(c >= l) update(node.left,start,c,l,r,val);
            if(c < r) update(node.right,c+1,end,l,r,val);
            if(node.left.val == 1 && node.right.val == 1) node.val = 1;
            else node.val = 2;
        }
        public int query(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                return node.val;
            }
            pushDown(node);
            int c = start + ((end-start) >> 1),res = 2,count = 0;
            if(c >= l){
                res = query(node.left,start,c,l,r);
                count++;
            }
            if(c < r){
                if(count == 1){
                    if(res != 2) res = query(node.right,c+1,end,l,r);
                }else res = query(node.right,c+1,end,l,r);
            }
            return res;
        }
        public void pushDown(Node node){
            if(node.left == null) node.left = new Node();
            if(node.right == null) node.right = new Node();
            if(node.add == 0) return;
            node.left.val = node.add;
            node.right.val = node.add;
            node.left.add = node.add;
            node.right.add = node.add;
            node.add = 0;
        }
    }*/
}

//解法一：线段树
/*
class RangeModule {
    int l = 0,r = 1000000000;
    TreeNode root = new TreeNode();
    public RangeModule() {

    }

    public void addRange(int left, int right) {
        update(root,l,r,left,right-1,1);
    }

    public boolean queryRange(int left, int right) {
        int res = query(root, l, r, left, right-1);
        return res == 1;
    }

    public void removeRange(int left, int right) {
        update(root,l,r,left,right-1,0);
    }

    class TreeNode{
        //add用于更新后续节点，-1表示不需要更新，0表示false，1表示true
        int val,add = -1;
        TreeNode left,right;
    }
    void update(TreeNode node,int left,int right,int start,int end,int val){
        if (start <= left && right <= end) {
            node.val = val;
            node.add = val;
            return;
        }
        pushDown(node);
        int c = left + ((right-left) >> 1);
        if(c >= start){
            update(node.left,left,c,start,end,val);
        }
        if (c < end) {
            update(node.right,c+1,right,start,end,val);
        }
        if (node.left.val == 1 && node.right.val == 1) {
            node.val = 1;
        } else {
            node.val = 0;
        }
    }
    int query(TreeNode node,int left,int right,int start,int end){
        if (start <= left && right <= end) {
            return node.val;
        }
        pushDown(node);
        int c = left + ((right-left) >> 1),res = 1,flag = 0; //flag标识是否进入子循环中
        if (c >= start) {
            res = query(node.left,left,c,start,end);
            flag = 1;
        }
        if (c < end) {
            if (flag == 1 && res == 0) {
                return res;
            }
            res &= query(node.right,c+1,right,start,end);
        }
        return res;
    }
    void pushDown(TreeNode node){
        if (node.left == null) {
            node.left = new TreeNode();
        }
        if (node.right == null) {
            node.right = new TreeNode();
        }
        if (node.add == -1) {
            return;
        }
        node.left.val = node.add;
        node.left.add = node.add;
        node.right.add = node.add;
        node.right.val = node.add;
        node.add = -1;
    }
}*/

class RangeModule {
    //集合中只存放存在的区间，也就是说开区间不存入
    List<int[]> range;

    public RangeModule() {
        range = new ArrayList<>();
    }

    public void addRange(int left, int right) {
        int n = range.size(), loc = -1;
        right--;
        if (n == 0) {
            range.add(new int[]{left,right});
            return;
        }
        loc = findLoc(left);
        //找到最近的一个l <= left之后
        if (loc != -1) {
            int[] tmp = range.get(loc);
            //判断两个集合是否有交集，一定要注意！！！如果前一个集合是(9,9)，而当前集合是(10,10)，那也是需要合并的
            if (left-1 <= tmp[1]) {
                if (right <= tmp[1]) {
                    return;
                }
                left = tmp[0];
                range.remove(loc);
                //注意，移除了一个元素，下次遍历开始时要为当前位置的元素，所以loc要减一
                loc--;
            }
        }
        for (int i = loc+1; i < range.size(); i++) {
            int[] tmp = range.get(i);
            //注意两个相邻的区间也要合并
            if (right < tmp[0]-1) {
                break;
            } else {
                range.remove(i);
                //被当前区间包含了，不必继续遍历下去，合并区间
                if (right <= tmp[1]) {
                    right = tmp[1];
                    range.add(i, new int[]{left, right});
                    return;
                }
                i--;
            }
        }
        range.add(new int[]{left,right});
    }

    public boolean queryRange(int left, int right) {
        int loc = findLoc(left);
        if (loc == -1) {
            return false;
        }
        int[] tmp = range.get(loc);
        //return left >= tmp[0] && right-1 <= tmp[1];
        return right-1 <= tmp[1];
    }

    public void removeRange(int left, int right) {
        int n = range.size(), loc;
        right--;
        loc = findLoc(left);
        //找到最近的一个l <= left之后
        if (loc != -1) {
            int[] tmp = range.get(loc);
            //判断两个集合是否有交集
            if (left <= tmp[1]) {
                range.remove(loc);
                if (left - 1 >= tmp[0]) {
                    //loc++是为了保证如果右边区间也变小了，会加在此区间的后面
                    range.add(loc++,new int[]{tmp[0],left-1});
                }
                if (right <= tmp[1]) {
                    if (tmp[1] - 1 >= right) {
                        range.add(loc,new int[]{right+1,tmp[1]});
                    }
                    return;
                }
                //特别注意此处
                loc--;
            }
        }
        for (int i = loc+1; i < range.size(); i++) {
            int[] tmp = range.get(i);
            if (right < tmp[0]) {
                return;
            } else {
                range.remove(i);
                if (right < tmp[1]) {
                    range.add(i, new int[]{right+1, tmp[1]});
                    return;
                }
                i--;
            }
        }
    }

    private int findLoc(int left){
        int n = range.size(), loc = -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int c = l + ((r - l) >> 1);
            int[] tmp = range.get(c);
            if (tmp[0] > left) {
                r = c - 1;
            } else {
                loc = c;
                l = c + 1;
            }
        }
        return loc;
    }
}