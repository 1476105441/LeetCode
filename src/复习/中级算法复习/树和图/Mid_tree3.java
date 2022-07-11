package 复习.中级算法复习.树和图;

import java.util.Stack;

public class Mid_tree3 {
    //      从前序与中序遍历序列构造二叉树

    //解法一：使用双指针遍历前序序列和中序序列
    /*int pre,in;
    public TreeNode buildTree(int[] preorder, int[] inorder){
        pre = 0;
        in = 0;
        return build(preorder,inorder,Integer.MAX_VALUE);
    }

    public TreeNode build(int[] preorder,int[] inorder,int stop){
        if(pre >= preorder.length){
            return null;
        }
        if(inorder[in] == stop){
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);

        node.left = build(preorder,inorder,node.val);
        node.right = build(preorder,inorder,stop);

        return node;
    }*/

    //解法二：分治法(5ms)
    /*int pre;
    public TreeNode buildTree(int[] preorder, int[] inorder){
        pre = 0;
        return build(preorder,inorder,0,inorder.length);
    }

    public TreeNode build(int[] preorder,int[] inorder,int begin,int end){
        if (begin > end) {
            return null;
        }
        int loc = -1;
        for (int i = begin; i <= end; i++) {
            if (preorder[pre] == inorder[i]) {
                loc = i;
                break;
            }
        }
        if (loc == -1) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);

        node.left = build(preorder,inorder,begin,loc-1);
        node.right = build(preorder,inorder,loc+1,end);

        return node;
    }*/

    //解法三：使用堆栈
    //  在前序序列中，相邻的两个元素m和n，只有两种情况，n是m的左子树，或者n是m的右子树或m
    //父节点的右子树
    //  解题之前需要这么几个变量：cur代表当前节点，i是遍历前序序列的指针，i所指的元素就是
    //当前节点的后一个相邻的节点，j是中序遍历的指针，j所指的元素代表除已构造的节点之外最左
    //边的节点，也就是说，j所指的节点没有左子树或者已经构造了左子树
    //  两种情况：1、当前节点有左子树，给当前节点加上左子树，让当前节点入栈，作为左子树的祖先
    //           节点之一，设置当前节点为其左子树，继续考察是否有左子树
    //          2、当前节点没有左子树，那么其邻接的下一个元素就是当前节点的右子树或者其祖先
    //           节点的右子树，通过中序遍历来确定
    //  判断条件：
    //  要点1：判断当前节点是否有左子树。通过中序遍历的序列来判断，中序遍历指针所指的元素是
    //最左边的元素，一定没有左子树，所以只需要判断当前节点与中序遍历指针所指的元素值是否相等，
    //就可以确定当前节点是否有左子树，不相等则说明有左子树，相等则说明没有左子树
    //  要点2：当前节点没有左子树，如何将邻接点接到合适的位置？当前节点没有左子树，说明当前
    //节点的值与中序序列指针所指的值相等，先将指针指向下一个，然后再判断指针的值是否与堆栈中
    //栈顶节点的值相等，因为堆栈的栈顶节点就是当前节点的双亲节点，而中序遍历的顺序是左中右，
    //
    public TreeNode buildTree(int[] preorder,int[] inorder){
        int i = 1,j = 0;
        TreeNode cur = new TreeNode(preorder[0]),head = cur;
        Stack<TreeNode> stack = new Stack<>();

        for (; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                stack.push(cur);
                cur = cur.left;
            }else{
                j++;

                while (!stack.isEmpty() && stack.peek().val == inorder[j]) {
                    cur = stack.pop();
                    j++;
                }

                cur.right = new TreeNode(preorder[i]);
                cur = cur.right;
            }
        }

        return head;
    }
}
