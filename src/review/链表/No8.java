package review.链表;

import senior.链表.ListNode;

public class No8 {
    /**
     *      奇偶链表
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     *
     * 提示:
     *     n ==  链表中的节点数
     *     0 <= n <= 104
     *     -106 <= Node.val <= 106
     */

    /*public ListNode oddEvenList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur1 = head,other = new ListNode(),cur2 = other,cur = head.next;
        int flag = 0;
        while (cur != null) {
            if (flag == 0) {
                cur2.next = cur;
                cur2 = cur2.next;
                flag = 1;
            }else{
                cur1.next = cur;
                cur1 = cur1.next;
                flag = 0;
            }
            cur = cur.next;
        }
        cur2.next = null;
        cur1.next = other.next;

        return head;
    }*/

    //更简洁的一种写法
    public ListNode oddEvenList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode on = head,en = head.next,p;

        while (en != null && en.next != null) {
            p = en.next;
            en.next = p.next;
            p.next = on.next;
            on.next = p;
            en = en.next;
            on = on.next;
        }

        return head;
    }
}
