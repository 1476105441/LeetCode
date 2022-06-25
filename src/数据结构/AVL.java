package 数据结构;

import java.util.List;

public class AVL {
    Node root;

    AVL(Node root){
        this.root = root;
    }
    AVL(List<Integer> list){
        if (list != null) {
            root = build(list,0,list.size()-1,null);
        }
    }

    //由一个有序集合构造平衡二叉树
    Node build(List<Integer> list,int left,int right,Node parent){
        if (left > right) {
            return null;
        }
        int center = left+(right-left)/2; //防止计算结果溢出，采用减法
        Node node = new Node(list.get(center),parent);

        node.left = build(list,left,center-1,node);
        node.right = build(list,center+1,right,node);

        recompute(node);
        return node;
    }

    //删除操作
    boolean delete(int val){
        if (root == null) {
            return false;
        }
        //1、找到要删除节点
        Node node = findLocation(val);
        if (node == null || node.val != val) {
            return false;
        }

        //既有左子树又有右子树的情况要特殊处理
        if (node.left != null && node.right != null) {
            Node replace;
            if (getHeight(node.left) > getHeight(node.right)) {
                replace = findRight(node.left);
            }else{
                replace = findLeft(node.right);
            }
            node.val = replace.val;
            //删除replace节点，因为node的值已经被取代，现在需要删除replace
            node = replace;
        }

        Node parent = node.parent;
        del(node);
        reBalance(parent);
        return true;
    }

    void del(Node node){
        if (node.left != null && node.right != null) {
            throw new RuntimeException("删除出错");
        }

        Node parent = node.parent,next;
        if (node.left != null) {
            next = node.left;
        }else {
            next = node.right;
        }

        if (next != null) {
            next.parent = parent;
        }
        if (node == root) {
            root = next;
        }else{
            if (node == parent.left) {
                parent.left = next;
            }else{
                parent.right = next;
            }
        }
        node.parent = null;
    }

    //插入操作
    void insert(int val){
        if (root == null) {
            root = new Node(val);
        }else{
            //找到当前节点应该插入的位置
            Node node = findLocation(val);
            //判断是否插入到左子树上
            boolean addLeft = node.val >= val;
            //值相等时做统一处理，插入到node的左边
            if (node.val == val && node.left != null) {
                node = findRight(node.left);
                addLeft = false;
            }

            Node leaf = new Node(val,node);
            if (addLeft) {
                node.left = leaf;
            }else{
                node.right = leaf;
            }
            //重新调整平衡状态和信息
            reBalance(leaf);
        }
    }

    //从叶子节点开始往上调整信息和结构
    void reBalance(Node node){
        while (node != null) {
            int oldHeight = node.height,oldSize = node.size;
            //不平衡需要重构
            if (!isBalance(node)) {
                node = reBuild(findTallChild(findTallChild(node)));
                //node的左右子树结构发生了变化，重新统计信息
                recompute(node.left);
                recompute(node.right);
            }
            recompute(node);
            if (oldHeight == node.height && oldSize == node.size) {
                break;
            }else{
                node = node.parent;
            }
        }
    }

    Node findTallChild(Node node){
        if (getHeight(node.left) > getHeight(node.right)) {
            return node.left;
        }else{
            return node.right;
        }
    }

    Node reBuild(Node node){
        Node parent = node.parent;
        Node grandParent = parent.parent;

        //如果子节点、父节点、父节点的父节点在同一侧，只需要旋转一次，否
        //则旋转两次，将子节点旋转到中间位置
        if ((node == parent.left) && (parent == grandParent.right)) {
            rotate(parent);
            return parent;
        }else{
            //双旋
            rotate(node);
            rotate(node);
            return node;
        }
    }

    //旋转操作，调整二叉树的结构以达到平衡
    void rotate(Node node){
        Node parent = node.parent;
        Node grandParent = parent.parent;
        //为空说明parent为根节点
        if (grandParent == null) {
            root = node;
            node.parent = null;
        }else{
            //将node节点接到parent应该在的位置，取代parent的位置
            reLink(grandParent,node,parent == grandParent.left);
        }

        if (node == parent.left) {
            //node是parent的左子树，比parent小，所以右子
            //树也小于parent，因此接到parent的左子树上
            reLink(parent,node.right,true);
            reLink(node,parent,false);
        }else{
            //node是parent的右子树，它和它的左子树都比parent大，
            //因此它的左子树接到parent的右边
            reLink(parent,node.left,false);
            reLink(node,parent,true);
        }
    }

    void reLink(Node parent,Node child,boolean addLeft){
        if (addLeft) {
            parent.left = child;
        }else{
            parent.right = child;
        }

        if (child != null) {
            child.parent = parent;
        }
    }

    //高度差不超过1的才平衡
    boolean isBalance(Node node){
        return getHeight(node.left) - getHeight(node.right) <= 1;
    }

    //找到当前节点的最右边节点
    Node findRight(Node node){
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    Node findLeft(Node node){
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //找到应该插入位置的节点
    Node findLocation(int val){
        Node node = root,pre = null;
        while (node != null) {
            pre = node;
            if (node.val > val) {
                node = node.left;
            } else if (node.val < val) {
                node = node.right;
            }else
                return node;
        }
        return pre;
    }

    //计算每个节点的信息
    void recompute(Node node){
        //当前节点的高度等于左右子树最大高度加一
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //计算当前节点所包含的节点个数（包括自身）
        node.size = 1 + getSize(node.left) + getSize(node.right);
    }

    public static int getHeight(Node node){
        return node != null ? node.height : 0;
    }
    public static int getSize(Node node){
        return node != null ? node.size : 0;
    }
}
class Node{
    public Node parent;
    public Node left;
    public Node right;
    public int height;  //高度，便于计算左右子树的高度差
    public int size;    //所包含的节点个数
    public int val;

    Node(int val){
        this(val,null,null,null);
    }

    Node(int val,Node parent){
        this(val,parent,null,null);
    }

    Node(int val,Node parent,Node left,Node right){
        this.val = val;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.height = 0;      //初始高度为0，后面再统计
        this.size = 1;        //初始节点个数为1
    }
}
