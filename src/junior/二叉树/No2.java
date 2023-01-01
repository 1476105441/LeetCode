package junior.二叉树;

import java.util.Stack;

public class No2 {
    //                  验证二叉搜索树
    //  给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    //
    //有效 二叉搜索树定义如下：
    //    节点的左子树只包含 小于 当前节点的数。
    //    节点的右子树只包含 大于 当前节点的数。
    //    所有左子树和右子树自身必须也是二叉搜索树。
    //提示：
    //    树中节点数目范围在[1, 104] 内
    //    -231 <= Node.val <= 231 - 1

    //------------------------------------------------------------------------------
    //太过于麻烦，时间太慢，1ms，被吊打，直接中序遍历比较是否大于前一个节点就可以了
    //              先中序遍历到数组里，在遍历数组查看是否是升序
    /*int[] trees;
    int length = 0;
    public void inorder(TreeNode root){
        if (root.left != null) {
            inorder(root.left);
        }
        trees[length++] = root.val;
        if (root.right != null) {
            inorder(root.right);
        }
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        trees = new int[10000];
        inorder(root);
        for (int i = 1; i < length; i++) {
            if (trees[i] <= trees[i - 1]) {
                return false;
            }
        }
        return true;
    }*/
    //------------------------------------------------------------------------------


    //------------------------------------------------------------------------------
    //                          中序遍历比较
    //成功，0ms
    /*
    //这是前一个节点，为了方便中序遍历比较，设置为成员变量
    TreeNode precursor;
    public boolean isValidBST(TreeNode root) {
        //由于题目说明了二叉树的节点从1开始，所以不必判断根节点是否为空
        //传进来的节点为null，就不用判断和前一个节点的大小
        if (root == null) {
            return true;
        }
        //先左
        if (!isValidBST(root.left)) {
            return false;
        }
        //中间
        if (precursor != null && root.val > precursor.val) {
            precursor = root;
        } else if (precursor == null) {
            precursor = root;
        }else
            return false;
        //右边
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }*/
    //------------------------------------------------------------------------------

    //------------------------------------------------------------------------------
    /*
    //这个递归暂时失败，先换个思路看看
    //失败原因：没有固定一个上界和下界，导致虽然一小块范围内符合要求，但是总体来说还是不符合
    public boolean isValidBST(TreeNode root,TreeNode precursor,boolean leftChild){
        if (root.left != null) {
            if (root.left.val >= root.val || !isValidBST(root.left,root,true)) {
                return false;
            } else if (precursor != null && !leftChild && root.left.val < precursor.val) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val <= root.val || !isValidBST(root.right,root,false)){
                return false;
            } else if (precursor != null && leftChild && root.right.val > precursor.val) {
                return false;
            }
        }
        return true;
    }*/
    //------------------------------------------------------------------------------

    //------------------------------------------------------------------------------
    //成功的递归遍历，0ms
    /*
    public boolean isValidBST(TreeNode root){
        if (root == null) {
            return true;
        }
        return recursion(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }
    //递归函数
    public boolean recursion(TreeNode root,long max,long min){
        if (root == null) {
            return true;
        }
        //超出取值范围，必定不符合题目要求
        if (root.val >= max || root.val <= min) {
            return false;
        }
        //右子树的下界是当前节点的值，即右子树上的值不小于当前节点的值
        //recursion(root.right,max,root.val);
        //左子树的上界是当前节点的值，即左子树上的值不大于当前节点的值
        //recursion(root.left,root.val,min);
        return recursion(root.right,max,root.val)&&recursion(root.left,root.val,min);
    }*/
    //------------------------------------------------------------------------------

    //还有一种解法：非递归中序遍历
    public boolean isValidBST(TreeNode root){
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            //先让左子树全部入栈，因为中序遍历是先遍历左子树

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (pre != null && root.val <= pre.val)
                return false;

            //保存前一个访问的结点
            pre = root;
            root = root.right;
        }
        return true;
    }
}
