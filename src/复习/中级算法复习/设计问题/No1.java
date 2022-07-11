package 复习.中级算法复习.设计问题;

import 复习.中级算法复习.树和图.TreeNode;

import java.util.Stack;

public class No1 {
    //              二叉树的序列化与反序列化

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        StringBuilder sb = new StringBuilder();

        while (node != null || !stack.empty()) {
            while (node != null) {
                sb.append(node.val).append(",");
                stack.push(node);
                if (node.left == null) {
                    sb.append("t").append(",");
                }
                node = node.left;
            }
            node = stack.pop();

            node = node.right;

            if (node == null) {
                sb.append("t").append(",");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        System.out.println(data);
        pre = 0;
        String[] split = data.split(",");
        TreeNode head;

        head = build(split);

        return head;
    }

    int pre;
    public TreeNode build(String[] strings){
        TreeNode node;

        if (pre > strings.length - 1 || "t".equals(strings[pre])) {
            pre++;
            return null;
        }

        node = new TreeNode(Integer.parseInt(strings[pre++]));
        node.left = build(strings);
        node.right = build(strings);

        return node;
    }

    /*public TreeNode buildTree(int[] preorder, int[] inorder){
        if (preorder.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(preorder[0]),cur = head;
        Stack<TreeNode> stack = new Stack<>();
        //i用来遍历前序序列，j用来遍历中序序列
        int i,j = 0;

        for (i = 1; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                stack.push(cur);
                cur = cur.left;
            }else{
                j++;
                while (!stack.empty() && stack.peek().val == inorder[j]) {
                    cur = stack.pop();
                    j++;
                }
                cur.right = new TreeNode(preorder[i]);
                cur = cur.right;
            }
        }

        return head;
    }*/
}
