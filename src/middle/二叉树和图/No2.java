package middle.二叉树和图;

import java.util.*;

public class No2 {
    //                  二叉树的锯齿形层次遍历
    //给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

    //-----------------------------------------------------------------
    //                  使用队列和堆栈，标记
    //太麻烦，代码也不简洁
    /*public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        //因为是自己的队列，使用一个计数器来记录当前队列中是否还有元素
        int count = 0;
        //先让根节点入队列，后面再开始出队列过程
        TreeQue treeQue = new TreeQue();
        treeQue.add(root);

        //flag标记是判断当前是正向取还是反向取，true是正向取，false是反向取
        boolean flag = true;

        TreeNode node;

        Stack<TreeNode> stack = new Stack<>();

        while (!treeQue.isEmpty()) {
            count = treeQue.size;
            List<Integer> list = new ArrayList<>();
            //正向取元素
            if (flag) {
                while (count > 0) {
                    node = treeQue.poll();
                    if (node.left != null) {
                        treeQue.add(node.left);
                    }
                    if (node.right != null) {
                        treeQue.add(node.right);
                    }
                    list.add(node.val);
                    count--;
                }
            }else{
                while (count > 0) {
                    node = treeQue.poll();
                    stack.push(node);
                    if (node.left != null) {
                        treeQue.add(node.left);
                    }
                    if (node.right != null) {
                        treeQue.add(node.right);
                    }
                    count--;
                }
                while(!stack.isEmpty()){
                    node = stack.pop();
                    list.add(node.val);
                }
            }
            flag = !flag;
            res.add(list);
        }

        return res;
    }*/
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //                      DFS遍历法
    /*List<List<Integer>> res;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        res = new ArrayList<>();

        dfs(0,root);

        return res;
    }
    public void dfs(int depth,TreeNode node){
        if (node == null) {
            return;
        }

        if(depth >= res.size()){
            res.add(new ArrayList<>());
        }

        //奇数层，根节点是第0层
        if ((depth & 1) == 1) {
            res.get(depth).add(0,node.val);
        }else{
            res.get(depth).add(node.val);
        }

        dfs(depth+1,node.left);
        dfs(depth+1,node.right);
    }*/
    //--------------------------------------------------------------------------

    //------------------------------------------------------------------------------
    //                        堆栈加队列改进版
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        //使用计数器统计当前层中的节点,depth记录层数
        int count = 0,depth = 1;
        TreeNode node;

        stack.push(root);
        while (!stack.isEmpty()) {
            count = stack.size();
            List<Integer> list = new ArrayList<>();
            while (count != 0) {
                node = stack.pop();
                list.add(node.val);
                queue.add(node);
                count--;
            }
            while (!queue.isEmpty()) {
                node = queue.poll();
                //奇数层，正着入栈，即左子树先入栈
                if ((depth & 1) != 0) {
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                }else{
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                }
            }
            res.add(list);
            depth++;
        }

        return res;
    }
}
