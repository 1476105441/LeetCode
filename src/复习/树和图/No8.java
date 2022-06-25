package 复习.树和图;

import 高级算法.树和图.TreeNode;

public class No8 {
    /**
     *      从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
     * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     */

    //递归的构造，按照前序遍历的顺序来构造节点，然后在当前
    //的范围内查找当前节点中序遍历的位置，以该位置为中心，
    //划分为两部分，左边是当前节点的左子树，右边是当前节点
    //的右子树
    //成功，时间：3-5ms
    /*int pre;
    int[] preorder,inorder;
    public TreeNode buildTree(int[] preorder,int[] inorder){
        pre = 0;
        this.preorder = preorder;
        this.inorder = inorder;
        return build(0,preorder.length-1);
    }

    public TreeNode build(int left,int right){
        if (left > right || pre == preorder.length) {
            return null;
        }
        if (left == right) {
            return new TreeNode(preorder[pre++]);
        }

        TreeNode cur = new TreeNode(preorder[pre++]);
        int loc = left;
        while (loc <= right) {
            if (inorder[loc] == cur.val) {
                break;
            }
            loc++;
        }
        cur.left = build(left,loc-1);
        cur.right = build(loc+1,right);
        return cur;
    }*/

    //使用哈希表优化一下
    /*int pre;
    int[] preorder,inorder;
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder,int[] inorder){
        pre = 0;
        this.preorder = preorder;
        this.inorder = inorder;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return build(0,preorder.length-1);
    }

    public TreeNode build(int left,int right){
        if (left > right || pre == preorder.length) {
            return null;
        }
        if (left == right) {
            return new TreeNode(preorder[pre++]);
        }

        TreeNode cur = new TreeNode(preorder[pre++]);
        int loc = map.get(cur.val);
        cur.left = build(left,loc-1);
        cur.right = build(loc+1,right);
        return cur;
    }*/


    //使用堆栈解决
    //核心思想是：观察前序序列和中序序列中的值，
    //在前序序列中，两个相邻的值之间只有三种关系：
    //后面的是前面的左子树节点，右子树节点，是前面的父节点的右子树节点
    //如何判断这三种关系是关键，首先将前一个节点描
    //述为当前节点，使用当前节点的值和中序序列的值
    //比较，如果相等，则说明当前节点是目前未遍历过
    //的节点中最左边的节点，肯定没有左子树，然后判
    //断是否是当前节点的右子树将栈顶节点的值与当前
    //中序序列的值对比，如果相同，说明当前节点没有
    //右子树，因为中序遍历中它的下一个就是它的父节
    //点了，必然不可能有右子树，此时取出栈顶节点为
    //当前节点，然后重复上述判断右子树过程，直到当
    //前节点有右子树，把右子树接上当前节点，让当前
    //节点移动到它的右子树，再继续下一个节点的连接
    /*public TreeNode buildTree(int[] preorder,int[] inorder){
        Stack<TreeNode> stack = new Stack<>();
        int pre = 0,in = 0;
        TreeNode cur = new TreeNode(preorder[pre++]),root = cur;
        while (pre < preorder.length) {
            //判断当前节点是否有左子树，即它的下一个节点
            //是不是当前中序遍历第一个
            if (inorder[in] != cur.val) {
                cur.left = new TreeNode(preorder[pre++]);
                stack.push(cur);
                cur = cur.left;
            }else{
                in++;
                while (!stack.isEmpty() && stack.peek().val == inorder[in]) {
                    in++;
                    cur = stack.pop();
                }
                cur.right = new TreeNode(preorder[pre++]);
                cur = cur.right;
            }
        }
        return root;
    }*/


    //最优解法
    int pre,in;
    int[] preorder,inorder;
    public TreeNode buildTree(int[] preorder,int[] inorder){
        pre = 0;in = 0;
        this.preorder = preorder;this.inorder = inorder;
        return build(Integer.MAX_VALUE);
    }
    //给左子树传递当前节点的值，因为如果
    //左子树为空，可以更新中序遍历的当前
    //值，给右子树传递当前节点的stop值，
    //这个值很可能是当前节点的父节点的值
    //如果当前节点的右节点为空，那么就要
    //在进入右节点的操作中更新中序遍历的
    //值，因为中序遍历中当前节点的下一个
    //就是父节点
    /*public TreeNode build(int stop){
        if (pre >= preorder.length) {
            return null;
        }
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(node.val);
        node.right = build(stop);
        return node;
    }*/
    public TreeNode build(int stop){
        if (pre >= preorder.length) {
            return null;
        }
        if (inorder[in] == stop) {
            in++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(node.val);
        node.right = build(stop);
        return node;
    }
}
