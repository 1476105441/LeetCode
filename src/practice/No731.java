package practice;

public class No731 {
    /**
     *    我的日程安排表 II
     */

    //失败
    /*class MyCalendarTwo {
        Node root;
        int N = 1000000000;
        public MyCalendarTwo() {
            root = new Node();
        }

        public boolean book(int start, int end) {
            if(query(root,0,N,start,end-1) < 2){
                update(root,0,N,start,end-1);
                return true;
            }
            return false;
        }

        class Node{
            Node left,right;
            int val,add;
        }
        public void update(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                node.val++;
                node.add++;
                return;
            }
            pushDown(node);
            int c = start+((end-start)>>1);
            if(c >= l) update(node.left,start,c,l,r);
            if(c < r) update(node.right,c+1,end,l,r);
            node.val = Math.max(node.left.val,node.right.val);
        }
        public int query(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                return node.val;
            }
            pushDown(node);
            int c = start+((end-start)>>1),res = 0;
            if(c >= l) res = query(node.left,start,c,l,r);
            if(c < r) res = Math.max(query(node.right,c+1,end,l,r),res);
            return res;
        }
        public void pushDown(Node node){
            if(node.left == null) node.left = new Node();
            if(node.right == null) node.right = new Node();
            if(node.add == 0) return;
            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add = node.add;
            node.right.add = node.add;
            node.add = 0;
        }
    }*/

    //成功了，其实没有那么复杂
    class MyCalendarTwo {
        Node root;
        int N = 1000000000;
        public MyCalendarTwo() {
            root = new Node();
        }

        public boolean book(int start, int end) {
            if(query(root,0,N,start,end-1) < 2){
                update(root,0,N,start,end-1);
                return true;
            }
            return false;
        }

        class Node{
            Node left,right;
            int val,add;
        }
        public void update(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                node.val++;
                node.add++;
                return;
            }
            pushDown(node);
            int c = start+((end-start)>>1);
            if(c >= l) update(node.left,start,c,l,r);
            if(c < r) update(node.right,c+1,end,l,r);
            node.val = Math.max(node.left.val,node.right.val);
        }
        public int query(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                return node.val;
            }
            pushDown(node);
            int c = start+((end-start)>>1),res = 0;
            if(c >= l) res = query(node.left,start,c,l,r);
            if(c < r) res = Math.max(query(node.right,c+1,end,l,r),res);
            return res;
        }
        public void pushDown(Node node){
            if(node.left == null) node.left = new Node();
            if(node.right == null) node.right = new Node();
            if(node.add == 0) return;
            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add += node.add;
            node.right.add += node.add;
            node.add = 0;
        }
    }
}
