package 复习.中级算法复习.树和图;

public class Mid_tree4 {
    //      填充每个节点的下一个右侧节点指针

    public Node connect(Node root){
        if(root == null || root.left == null){
            return root;
        }
        Node preNext = root.left,childNext = preNext.left,pre = root,child =preNext;
        pre.next = null;
        while (preNext != null) {
            child.next = pre.right;
            pre = pre.next;
            child = child.next;

            while (pre != null) {
                child.next = pre.left;
                child = child.next;
                if (child == null) {
                    break;
                }
                child.next = pre.right;
                child = child.next;
                if (child == null) {
                    break;
                }
                pre = pre.next;
            }
            pre = preNext;
            child = childNext;
            if (childNext != null) {
                childNext = childNext.left;
            }
            preNext = preNext.left;
        }
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
