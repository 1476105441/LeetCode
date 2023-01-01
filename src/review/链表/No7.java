package review.链表;

import senior.链表.ListNode;

public class No7 {
    /**
     *      两数之和
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 提示：
     *     每个链表中的节点数在范围 [1, 100] 内
     *     0 <= Node.val <= 9
     *     题目数据保证列表表示的数字不含前导零
     */

    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode cur1 = l1,cur2 = l2,pre1 = l1;
        int temp,c = 0;

        while (cur1 != null && cur2 != null) {
            pre1 = cur1;
            temp = cur1.val + cur2.val + c;
            if (temp >= 10) {
                temp %= 10;
                c = 1;
            }else
                c = 0;
            cur1.val = temp;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        if (cur1 == null) {
            pre1.next = cur2;
            cur1 = pre1.next;
        }

        while (cur1 != null) {
            pre1 = cur1;
            temp = cur1.val + c;
            if (temp >= 10) {
                temp %= 10;
                c = 1;
            }else
                c = 0;
            cur1.val = temp;
            cur1 = cur1.next;
        }

        if (c == 1) {
            pre1.next = new ListNode();
            pre1.next.val = 1;
        }

        return l1;
    }

    //冗余的写法
    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode cur1 = l1,cur2 = l2,pre1 = l1;
        int temp,c = 0;

        while (cur1 != null && cur2 != null) {
            pre1 = cur1;
            temp = cur1.val + cur2.val + c;
            if (temp >= 10) {
                temp %= 10;
                c = 1;
            }else
                c = 0;
            cur1.val = temp;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        if (cur1 == null) {
            pre1.next = cur2;
            cur1 = cur2;
            while (cur1 != null) {
                pre1 = cur1;
                temp = cur1.val + c;
                if (temp >= 10) {
                    temp %= 10;
                    c = 1;
                }else
                    c = 0;
                cur1.val = temp;
                cur1 = cur1.next;
            }
        }else{
            while (cur1 != null) {
                pre1 = cur1;
                temp = cur1.val + c;
                if (temp >= 10) {
                    temp %= 10;
                    c = 1;
                }else
                    c = 0;
                cur1.val = temp;
                cur1 = cur1.next;
            }
        }

        if (c == 1) {
            pre1.next = new ListNode();
            pre1.next.val = 1;
        }

        return l1;
    }*/
}
