package junior.二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class No3 {
    //               对称二叉树
    //给定一个二叉树，检查它是否是镜像对称的。
    //你可以运用递归和迭代两种方法解决这个问题吗？

    //失败：没有考虑仔细，如果节点的值相同就不行了
    //-----------------------------------------------------------
    //想法：将二叉树利用中序遍历转换成数组，再判断数组是否是回文串
    /*public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        int[] nums = new int[1000];
        int num = 0;
        //根节点入栈
        stack.push(root);
        //中序遍历将值放入数组中
        while (root.left != null || !stack.isEmpty()) {
            //先让根节点的左子树全部入栈
            while (root.left != null) {
                stack.push(root.left);
                root = root.left;
            }
            //取栈顶元素
            root = stack.pop();
            nums[num++] = root.val;
            //右子树入栈
            if (root.right != null) {
                stack.push(root.right);
                root = root.right;
            }
        }
        int low = 0,high = num - 1;
        while (low < high) {
            if (nums[low] != nums[high]){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }*/
    //------------------------------------------------------------------------


    //自己写的递归，成功，0ms，击败百分百
    //-----------------------------------------------------------------------------------
    /*public static boolean isSymmetric(TreeNode root){
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left,root.right);
    }
    //新想法：能不能像双指针一样，两个指针遍历二叉树，一个指针走左边，另一个走右边
    public static boolean isSymmetric(TreeNode left,TreeNode right){
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            //不同时为空
            return false;
        }
        return isSymmetric(left.left,right.right) && left.val == right.val && isSymmetric(left.right,right.left);
    }*/
    //-----------------------------------------------------------------------------------



    //我的非递归：使用两个堆栈一个中序遍历root左子树，一个后序遍历root右子树，两个链表存放左子树遍历
    //13ms，非常慢，又要存进堆栈里又要取出来放进链表里，太慢
    //-----------------------------------------------------------------------------------
    /*
    public static boolean isSymmetric(TreeNode root){
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }
        TreeNode left = root.left, right = root.right;
        Stack<TreeNode> stack1 = new Stack<>(), stack2 = new Stack<>();
        IntList list1,head1,list2,head2;
        head1 = new IntList();
        head1.val = left.val;
        list1 = head1;
        head2 = new IntList();
        head2.val = right.val;
        list2 = head2;
        //根节点入栈
        stack1.push(left);
        stack2.push(right);
        //中序遍历将值放入数组中
        while (left.left != null || !stack1.isEmpty()) {
            //先让左节点的左子树全部入栈
            while (left.left != null) {
                stack1.push(left.left);
                left = left.left;
            }
            //取栈顶元素
            left = stack1.pop();
            list1.next = new IntList();
            list1 = list1.next;
            list1.val = left.val;
            //右子树入栈
            if (left.right != null) {
                stack1.push(left.right);
                left = left.right;
            }
        }
        while (right.right != null || !stack2.isEmpty()) {
            //先让右节点的左子树全部入栈
            while (right.right != null) {
                stack2.push(right.right);
                right = right.right;
            }
            //取栈顶元素
            right = stack2.pop();
            list2.next = new IntList();
            list2 = list2.next;
            list2.val = right.val;
            //左子树入栈
            if (right.left != null) {
                stack2.push(right.left);
                right = right.left;
            }
        }
        while (head1 != null && head2 != null) {
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        if (head1 != null || head2 != null) {
            return false;
        }
        return true;
    }*/
    //-------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------
    //成功，1ms
    //使用队列的非递归来解决，但是得注意，用一个队列解决的话，要一次出队两个进行比较
    public static boolean isSymmetric(TreeNode root){
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left.val != right.val) {
                return false;
            }

            //使用异或来判断两个不同时为空
            if (left.left == null ^ right.right == null) {
                return false;
            }

            if (left.left != null) {
                queue.add(left.left);
                queue.add(right.right);
            }

            if (left.right == null ^ right.left == null) {
                return false;
            }

            if (left.right != null) {
                queue.add(left.right);
                queue.add(right.left);
            }
        }
        return true;
    }
}
