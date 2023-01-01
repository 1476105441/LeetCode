package practice;

public class No729 {
    /**
     *      我的日程安排表 I
     */

    //解法一：线段树
    class MyCalendar {

        public MyCalendar() {
            root = new Node();
            N = 1000000000;
        }

        public boolean book(int start, int end) {
            if(!query(root,0,N,start,end-1)){
                update(root,0,N,start,end-1);
                return true;
            }
            return false;
        }

        Node root;
        int N;
        class Node{
            Node left,right;
            boolean val;
            int l,r;
            public Node(){
                l = -1;
                r = -1;
            }
        }
        void update(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                node.val = true;
                node.l = start;
                node.r = end;
                return;
            }
            int c = start+((end-start)>>1);
            pushDown(node,c);
            if(c >= l) update(node.left,start,c,l,r);
            if(c < r) update(node.right,c+1,end,l,r);
            node.val = node.left.val || node.right.val;
        }
        boolean query(Node node,int start,int end,int l,int r){
            if(start >= l && end <= r){
                return node.val;
            }
            int c = start+((end-start)>>1);
            boolean res = false;
            pushDown(node,c);
            if(c >= l) res = query(node.left,start,c,l,r);
            if(c < r) res = res || query(node.right,c+1,end,l,r);
            return res;
        }
        void pushDown(Node node,int c){
            if(node.left == null) node.left = new Node();
            if(node.right == null) node.right = new Node();
            if(node.l == -1) return;
            if(node.l > c){
                node.right.val = node.val;
                node.right.l = node.l;
                node.right.r = node.r;
            }else if(node.r <= c){
                node.left.val = node.val;
                node.left.l = node.l;
                node.left.r = node.r;
            }else{
                node.right.val = node.val;
                node.right.l = c+1;
                node.right.r = node.r;
                node.left.val = node.val;
                node.left.l = node.l;
                node.left.r = c;
            }
        }
    }
}
