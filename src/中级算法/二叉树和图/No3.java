package 中级算法.二叉树和图;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class No3 {
    //                  从前序与中序遍历序列构造二叉树
    //给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。

    //------------------------------------------------------------------------
    //                  分治法：成功，4ms
    //思路：前序遍历中的第一个节点就是根节点，在中序遍历数组中找到其对应的数组下标
    //然后在（中序遍历数组）左边找离当前根节点（前序遍历数组）最近的节点作为左子树，
    //在右边找离当前根节点最近的节点作为右子树
    //注意：如果有相同的值要注意区分，使用一个状态数组来标记节点是否已经加入树中
    /*boolean[] bp;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode();

        bp = new boolean[preorder.length];

        recursion(root,0,preorder.length-1,-1,preorder,inorder);

        return root;
    }

    public int recursion(TreeNode node,int low,int high,int loc,int[] preorder, int[] inorder){
        if (low > high) {
            return -1;
        }

        int i,temp = -3001,center = 0,j,newLoc = 0;

        for(i = loc + 1;i < preorder.length;i++){
            if(!bp[i]){
                newLoc = i;
                temp = preorder[i];
                break;
            }
        }

        if (temp == -3001) {
            return -1;
        }

        for (j = low; j <= high; j++) {
            if (inorder[j] == temp) {
                center = j;
                break;
            }
        }

        node.val = inorder[center];
        node.left = new TreeNode();
        node.right = new TreeNode();
        bp[i] = true;

        i = recursion(node.left,low,center - 1,newLoc,preorder,inorder);
        j = recursion(node.right,center + 1,high,newLoc,preorder,inorder);
        if (i == -1) {
            node.left = null;
        }
        if (j == -1) {
            node.right = null;
        }

        return 0;
    }*/
    //-------------------------------------------------------------------

    //-------------------------------------------------------------------
    //              使用哈希表提高效率：提高1ms
    /*boolean[] bp;
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root ;

        bp = new boolean[preorder.length];

        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }

        root = recursion(0,preorder.length-1,-1,preorder,inorder);

        return root;
    }

    public TreeNode recursion(int low,int high,int loc,int[] preorder, int[] inorder){
        if (low > high) {
            return null;
        }

        int i,temp = -3001,center = 0,j,newLoc = 0;

        for(i = loc + 1;i < preorder.length;i++){
            if(!bp[i]){
                newLoc = i;
                temp = preorder[i];
                break;
            }
        }

        if (temp == -3001) {
            return null;
        }

        center = map.get(temp);

        TreeNode node = new TreeNode();
        node.val = inorder[center];
        bp[i] = true;

        node.left = recursion(low,center - 1,newLoc,preorder,inorder);
        node.right = recursion(center + 1,high,newLoc,preorder,inorder);

        return node;
    }*/
    //---------------------------------------------------------------------

    //---------------------------------------------------------------------
    //              大神的递归算法，好好学学：0ms
    //使用两个成员变量指针来遍历两个数组，很巧妙的用法，in指向中序遍历数组中当前遍历到的位置，
    // pre指向前序遍历数组中当前遍历到的位置
    /*
    public int in;
    public int pre;
    public TreeNode buildTree(int[] preorder, int[] inorder){
        return build(preorder,inorder,Integer.MIN_VALUE);
    }

    public TreeNode build(int[] preorder,int[] inorder,int stop){
        if (pre >= preorder.length) {
            return null;
        }
        if (inorder[in] == stop) {
            in++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder,inorder,node.val);
        node.right = build(preorder,inorder,stop);

        return node;
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //              使用堆栈
    //难点：如何判断当前节点是否有左右子树？
    //使用堆栈解法要点：在前序遍历中使用两个指针，一个是当前节点对象，是已经构造出来的树节点，
    // 它的后一个节点与之对应的关系只有三种：
    //      1、是它的左子树 2、是它的右子树 3、是它某个祖宗节点的右子树
    //如何判断是哪种对应关系呢？利用中序遍历数组来判断，
    public TreeNode buildTree(int[] preorder, int[] inorder){
        if (preorder.length == 0)
            return null;
        Stack<TreeNode> s = new Stack<>();
        //前序的第一个其实就是根节点
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;

        //i是前序遍历数组preorder中的遍历到的元素下标，j是中序遍历数组inorder中遍历到的元素下标
        for (int i = 1, j = 0; i < preorder.length; i++) {
            //第一种情况：当前节点有左子树
            //  初始时，中序遍历指针指向第一个元素，此元素是树中最左边的节点，
            //如果当前节点的值等于中序遍历节点的值，说明当前节点是最左边的
            //节点，没有左子树了
            if (cur.val != inorder[j]) {
                //将前序数组中的元素就是当前节点的左子树
                cur.left = new TreeNode(preorder[i]);
                s.push(cur);
                cur = cur.left;
            } else {
                //第二种情况：当前节点没有左子树
                j++;

                //找到合适的cur，然后确定他的右节点
                //  如果栈顶的值等于当前中序遍历中指针所指的值，说明在中序遍历中当前节点和栈顶节点（也就是当前节点的双亲节点）
                //之间没有其他节点，即当前节点没有右子树（如果有右子树的话，在中序遍历中当前指针指的值不会是栈顶节点的值）
                while (!s.empty() && s.peek().val == inorder[j]) {
                    cur = s.pop();
                    j++;
                }
                //给cur添加右节点
                cur.right = new TreeNode(preorder[i]);
                //添加后让当前节点指向右子树
                cur = cur.right;
            }
        }
        return root;
    }
}
