package 复习.链表;

import 高级算法.链表.ListNode;

public class No9 {
    /**
     *      相交链表
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode p1 = headA,p2 = headB,res = null;

        while(true){
            if (p1 == p2) {
                res = p1;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == null && p2 == null) {
                break;
            } else if (p1 == null) {
                p1 = headB;
            } else if (p2 == null) {
                p2 = headA;
            }
        }

        return res;
    }
}
