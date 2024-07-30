package daily.year2024.m7;

/**
 * @author wjs 2024/7/30
 */
public class d30 {
    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        calendar.book(10,20);
        calendar.book(15,25);
        calendar.book(20,30);
    }
}

class MyCalendar {
    Node root;
    public MyCalendar() {
        root = new Node();
    }

    public boolean book(int start, int end) {
        boolean res = query(start,end-1,0,1000000001,root);
        if(res) {
            return false;
        }
        setVal(start,end-1,0,1000000001,root);
        return true;
    }

    private boolean query(int s,int e,int l,int r,Node node) {
        if(s <= l && r <= e) {
            return node.val;
        }
        pushDown(node);
        int c = l + ((r-l) >> 1);
        boolean res = false;
        if(c >= s) {
            res = query(s,e,l,c,node.left);
        }
        if(c < e) {
            res = res || query(s,e,c+1,r,node.right);
        }
        return res;
    }
    private void setVal(int s, int e, int l, int r, Node node) {
        if(s <= l && r <= e) {
            node.val = true;
            node.update = true;
            return;
        }
        pushDown(node);
        int c = l + ((r-l) >> 1);
        if(c >= s) {
            setVal(s,e,l,c,node.left);
        }
        if(c < e) {
            setVal(s,e,c+1,r,node.right);
        }
        node.val = node.left.val || node.right.val;
    }

    private void pushDown(Node node) {
        if(node.left == null) {
            node.left = new Node();
        }
        if(node.right == null) {
            node.right = new Node();
        }
        if(!node.update) {
            return;
        }
        node.left.val = node.val;
        node.left.update = true;
        node.right.val = node.val;
        node.right.update = true;
        node.update = false;
    }
}

class Node {
    Node left,right;
    boolean val, update;
    public Node() {
        val = false;
        update = false;
    }
}
