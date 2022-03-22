package 中级算法.二叉树和图;

public class No4 {
    //              填充每个节点的下一个右侧节点指针
    //给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
    //填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    //
    //初始状态下，所有 next 指针都被设置为 NULL。
    //
    //进阶：
    //    你只能使用常量级额外空间。
    //    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

    //--------------------------------------------------------------------------
    //                  1ms,还不错
    /*public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        //pre是前驱节点，为了方便连接每一行的节点，first是每连接一行时的第一个节点
        Node pre,node,first = root;
        int flag;

        while (first.left != null ) {
            pre = first;

            node = first.left;
            flag = 0;
            //连接每一层的节点
            while (node != null && pre != null) {
                if (flag == 0) {
                    node.next = pre.right;
                    flag = 1;
                } else if (flag == 1) {
                    pre = pre.next;
                    flag = 2;
                    continue;
                }else{
                    node.next = pre.left;
                    flag = 0;
                }

                node = node.next;
            }

            first = first.left;
        }

        return root;
    }*/
    //----------------------------------------------------------------------------

    //----------------------------------------------------------------------------
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        //pre是前驱节点，为了方便连接每一行的节点，first是每连接一行时的第一个节点
        Node pre,node,first = root;

        while (first.left != null ) {
            pre = first;

            node = first.left;
            node.next = pre.right;
            node = node.next;
            pre = pre.next;
            //连接每一层的节点
            while (node != null && pre != null) {
                //第二种连接情况
                node.next = pre.left;
                node = node.next;
                //第一种连接情况
                node.next = pre.right;
                pre = pre.next;
                node = node.next;
            }

            first = first.left;
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

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
