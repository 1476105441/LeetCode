package review.链表;

import senior.链表.ListNode;

public class No2 {
    /**
     *      删除链表的倒数第N个节点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * 提示：
     *     链表中结点的数目为 sz
     *     1 <= sz <= 30
     *     0 <= Node.val <= 100
     *     1 <= n <= sz
     */

    //递归，从最后一个节点开始计数，到n时删除
    /*int count,num;
    boolean flag;
    public ListNode removeNthFromEnd(ListNode head,int n){
        num = n;
        count = 0;
        flag = false;
        recursion(head);
        if (!flag) {
            return head.next;
        }
        return head;
    }

    public void recursion(ListNode cur){
        if (cur == null) {
            return;
        }

        recursion(cur.next);

        if (count == num) {
            cur.next = cur.next.next;
            flag = true;
            count++;
        }else{
            count++;
        }
    }*/

    //双指针解法，太妙了，我竟然忘了，让一个指针先走n步，然
    //后两个指针同时开始走，当先走的指针走到尽头时，后走的指
    //针就到了要删除的位置

    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode temp = new ListNode(),quick,slow;
        temp.next = head;
        slow = temp;
        quick = temp;
        int count = 0;
        while (count < n + 1) {
            quick = quick.next;
            count++;
        }

        while (quick != null) {
            slow = slow.next;
            quick = quick.next;
        }

        slow.next = slow.next.next;
        return temp.next;
    }
}
