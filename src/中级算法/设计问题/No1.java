package 中级算法.设计问题;

import 中级算法.二叉树和图.TreeNode;

import java.util.Stack;

public class No1 {
    //              二叉树的序列化与反序列化
    //序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
    //请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

    //-----------------------------------------------------------------------------------
    //想法：将前序遍历的结果和中序遍历的结果组成一个字符串，反序列化时根据前序和中序的结果来构造二叉树（失败）
    //失败原因：当二叉树中有相同元素时，导致前序和中序遍历的序列相同时，会导致结果出错
    // Encodes a tree to a single string.
    /*public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder preorder = new StringBuilder();
        TreeNode node;
        Stack<TreeNode> stack = new Stack<>();

        node = root;
        while (node != null || !stack.empty()) {
            while (node != null) {
                preorder.append(node.val).append(',');
                if (node.left == null) {
                    preorder.append('t').append(',');
                }
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (node.right == null) {
                preorder.append('t').append(',');
            }
            node = node.right;
        }

        return preorder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        pre = 0;
        String[] strings = data.split(",");

        return build(strings);
    }

    int pre;
    public TreeNode build(String[] strings){
        if (strings[pre].equals("t")) {
            pre++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(strings[pre++]));
        node.left = build(strings);
        node.right = build(strings);

        return node;
    }*/
    //---------------------------------------------------------------------

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder preorder = new StringBuilder();
        TreeNode node;
        Stack<TreeNode> stack = new Stack<>();

        node = root;
        while (node != null || !stack.empty()) {
            while (node != null) {
                preorder.append(node.val).append(',');
                if (node.left == null) {
                    preorder.append('t').append(',');
                }
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (node.right == null) {
                preorder.append('t').append(',');
            }
            node = node.right;
        }

        return preorder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        pre = 0;
        String[] strings = data.split(",");

        return build(strings);
    }

    int pre;
    public TreeNode build(String[] strings){
        if (strings[pre].equals("t")) {
            pre++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(strings[pre++]));
        node.left = build(strings);
        node.right = build(strings);

        return node;
    }
}
