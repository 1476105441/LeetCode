package 复习.链表;

import 高级算法.链表.ListNode;

public class No3 {
    /**
     *      反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 提示：
     *     链表中节点的数目范围是 [0, 5000]
     *     -5000 <= Node.val <= 5000
     *
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     */

    //解法一：使用递归
    /*ListNode cur;
    public ListNode reverseList(ListNode head){
        ListNode before = new ListNode();
        before.next = head;
        cur = before;
        recursion(head);
        cur.next = null;
        return before.next;
    }

    public void recursion(ListNode node){
        if (node == null) {
            return;
        }

        recursion(node.next);
        cur.next = node;
        cur = cur.next;
    }*/

    //解法二：迭代
    public ListNode reverseList(ListNode head){
        ListNode cur = null,pre = null,next = head;

        cur = next;
        while(cur != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
