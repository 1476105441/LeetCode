package 初级算法.二叉树;

public class No4 {
    //          二叉树的层序遍历
    //给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

    //BFS广度优先遍历倒是不难，主要是要分层，用一个数组记录层数
    //成功，1ms，击败百分之91
    /*
    public List<List<Integer>> levelOrder(TreeNode root) {
        //返回的list结果集
        List<List<Integer>> resList = new ArrayList<>();

        if (root == null) {
            return resList;
        }

        //用于中序遍历的队列
        Queue<TreeNode> treeQue = new LinkedList<>();

        //用于辨认层数的队列
        Queue<Integer> queue = new LinkedList<>();

        treeQue.add(root);
        queue.add(1);

        //用于判断是否在同一层上
        int num1 = 0,num2;

        TreeNode treeNode;
        List<Integer> list = null;

        while (!treeQue.isEmpty()) {
            //出队列
            treeNode = treeQue.poll();
            num2 = queue.poll();

            //不在同一层上
            if (num2 != num1) {
                if (num2 != 1) {
                    resList.add(list);
                }
                list = new ArrayList<>();
                list.add(treeNode.val);
                num1 = num2;
            }else{
                list.add(treeNode.val);
            }

            //让左右子树入队列
            if (treeNode.left != null) {
                treeQue.add(treeNode.left);
                queue.add(num2 + 1);
            }
            if (treeNode.right != null) {
                treeQue.add(treeNode.right);
                queue.add(num2 + 1);
            }
        }
        resList.add(list);
        return resList;
    }*/
    //---------------------------------------------------------------------------


    //---------------------------------------------------------------------------
    //改进自己的广度优先遍历算法
    //代码虽然简洁了，但是内存消耗比我原来的还大？时间也差不多
    /*
    public List<List<Integer>> levelOrder(TreeNode root) {
        //返回的list结果集
        List<List<Integer>> resList = new ArrayList<>();

        if (root == null) {
            return resList;
        }

        //用于中序遍历的队列
        Queue<TreeNode> treeQue = new LinkedList<>();
        treeQue.add(root);

        TreeNode treeNode;
        //记录每一层的节点数
        int level = 0;
        List<Integer> list = null;

        while (!treeQue.isEmpty()) {
            level = treeQue.size();

            //每一次外循环都代表是下一层了
            list = new ArrayList<>();
            //一次将一层的全部遍历出来
            for (int i = 0; i < level; i++) {
                //出队列
                treeNode = treeQue.poll();

                list.add(treeNode.val);

                if (treeNode.left != null) {
                    treeQue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeQue.add(treeNode.right);
                }
            }
            resList.add(list);
        }
        return resList;
    }*/
    //---------------------------------------------------------------------------


    //---------------------------------------------------------------------------
    //大神的DFS解法，看完让人豁然开朗，自己怎么就想不到这个解法？
    /*
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
        //边界条件判断
        if (root == null)
            return;
        //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
        //要先把下一层的list初始化，防止下面add的时候出现空指针异常
        if (level >= list.size()) {
            list.add(new ArrayList<>());
        }
        //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
        list.get(level).add(root.val);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelHelper(list, root.left, level + 1);
        levelHelper(list, root.right, level + 1);
    }*/

}
