package 初级算法.二叉树;

public class No1 {
    //               二叉树的最大深度
    //  给定一个二叉树，找出其最大深度。
    //二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
    //说明: 叶子节点是指没有子节点的节点。

    //寻找二叉树的最大深度：
    //深度优先
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Dfs(root,1);
    }
    /*
    //深度优先遍历
    //失败版本：递归不行，超出时间限制，why？三目运算符的原因，太耗时间
    public int Dfs(TreeNode node){
        //递归出口
        if (node == null) {
            return 0;
        }
        //return 1 + (Dfs(node.left) > Dfs(node.right) ? Dfs(node.left) : Dfs(node.right));
        //改成这个就可以，说明是三目运算符太耗时间
        return 1 + Math.max(Dfs(node.left),Dfs(node.right));
    }*/

    //递归版DFS成功：0ms
    public int Dfs(TreeNode node,int depth){
        int rDepth = 0,lDepth = 0;
        //递归出口
        if (node.left != null) {
            rDepth = Dfs(node.left,depth + 1);
        }

        if (node.right != null) {
            lDepth = Dfs(node.right,depth + 1);
        }
        int max;

        if (rDepth > lDepth) {
            max = rDepth;
        }else
            max = lDepth;

        if (max < depth) {
            max = depth;
        }

        return max;
    }

    //成功，1ms，依然被吊打
    //广度优先遍历：使用队列
    public int Bfs(TreeNode node) {
        int num = 0;

        //使用链表实现队列
        TreeQue treeQue = new TreeQue();

        treeQue.add(node);

        //这是每一层的节点数
        int level;

        while (!treeQue.isEmpty()) {
            level = treeQue.size;
            for (int i = 0; i < level; i++) {
                TreeNode tree = treeQue.poll();

                if (tree.left != null) {
                    treeQue.add(tree.left);
                }
                if (tree.right != null) {
                    treeQue.add(tree.right);
                }
            }
            num++;
        }

        return num;
    }
}
