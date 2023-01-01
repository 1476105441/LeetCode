package review.设计问题;

import java.util.Stack;

public class No3 {
    /**
     *      二叉树的序列化与反序列化
     */

    //思路，使用前序遍历，用字符t表示空子树
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            TreeNode node = root;
            StringBuilder sb = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();

            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    sb.append(node.val).append(',');
                    stack.push(node);

                    if (node.left == null) {
                        sb.append('t').append(',');
                    }
                    node = node.left;
                }

                node = stack.pop();

                if (node.right == null) {
                    sb.append('t').append(',');
                }
                node = node.right;
            }

            return sb.toString();
        }

        int loc;
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if("".equals(data))
                return null;
            loc = 0;
            String[] s = data.split(",");
            return build(s);
        }

        public TreeNode build(String[] s){
            if(loc >= s.length || "t".equals(s[loc])){
                loc++;
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(s[loc]));
            loc++;
            node.left = build(s);
            node.right = build(s);

            return node;
        }
    }
}
