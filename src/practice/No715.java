package practice;

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
