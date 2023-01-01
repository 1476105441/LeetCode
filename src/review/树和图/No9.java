package review.树和图;

public class No9 {
    /**
     *      填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
     * 二叉树定义如下：
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     *
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * 提示：
     *     树中节点的数量在 [0, 212 - 1] 范围内
     *     -1000 <= node.val <= 1000
     */

    public Node connect(Node root){
        if (root == null) {
            return null;
        }
        Node preNext,curNext,pre = root,cur = pre.left;
        while (cur != null) {
            preNext = cur;
            curNext = cur.left;
            while (cur != null) {
                cur.next = pre.right;
                cur = cur.next;
                pre = pre.next;
                if (pre == null || cur == null) {
                    break;
                }
                cur.next = pre.left;
                cur = cur.next;
            }
            pre = preNext;
            cur = curNext;
        }
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
