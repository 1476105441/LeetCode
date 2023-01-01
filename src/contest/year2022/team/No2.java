package contest.year2022.team;

public class No2 {
    public TreeNode expandBinaryTree(TreeNode root) {
        dfs(root);
        return root;
    }

    public void dfs(TreeNode node){
        if(node == null) return;

        dfs(node.left);
        dfs(node.right);

        if(node.left != null && node.left.val != -1){
            TreeNode newNode = new TreeNode(-1);
            newNode.left = node.left;
            node.left = newNode;
        }
        if(node.right != null && node.right.val != -1){
            TreeNode newNode = new TreeNode(-1);
            newNode.right = node.right;
            node.right = newNode;
        }
    }
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
