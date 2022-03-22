package 中级算法.链表;

import java.util.HashSet;
import java.util.Set;

public class No3 {
    //                  相交链表
    //给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
    //图示两个链表在节点 c1 开始相交：

    //------------------------------------------------------------------------------
    //                   哈希表
    //太慢
    /*public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;

        Set<ListNode> set = new HashSet<>();

        while (a != null) {
            set.add(a);
            a = a.next;
        }

        while (b != null) {
            if (set.contains(b)) {
                return b;
            }
            b = b.next;
        }
        return null;
    }*/
    //------------------------------------------------------------------------

    //------------------------------------------------------------------------
    //                  想用回溯解决，失败
    /*public ListNode recursion(ListNode a,ListNode b){
        ListNode res;
        //a和b都到了最后一个节点
        if (a.next == null && b.next == null) {
            if (a == b) {
                return a;
            }else{
                return new ListNode(0);
            }
        } else if (a.next == null) {
            res = recursion(a,b.next);
        } else if (b.next == null) {
            res = recursion(a.next,b);
        }else{
            res = recursion(a.next,b.next);
        }

        if (a == b) {
            return a;
        }

        return res;
    }*/
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    //                  两次遍历法：最优解
    //第一次遍历时计算两个链表长度
    /*public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode a = headA,b = headB , res = null;

        //length是两个链表的长度之差
        int lengthA = 0, lengthB = 0, length;
        boolean flag = false;

        while (a != null) {
            lengthA++;
            a = a.next;
        }
        while (b != null) {
            lengthB++;
            b = b.next;
        }

        if (lengthA > lengthB) {
            length = lengthA - lengthB;
            flag = true;
        }else{
            length = lengthB - lengthA;
        }
        a = headA;
        b = headB;

        while (length > 0) {
            if (flag) {
                a = a.next;
            }else{
                b = b.next;
            }
            length--;
        }

        while (a != null && b != null) {
            if (a == b) {
                res = a;
                break;
            }
            a = a.next;
            b = b.next;
        }

        return res;
    }*/
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    //                      双指针的妙用
    //两个指针a和b分别从A链表和B链表开始遍历，a遍历到链表尾后，让其从B链表的表头开始往后遍
    // 历，同理，b从A链表的表头开始往后遍历，如果两个链表之间有相交点，那么a和b一定会相遇，
    // 原因如下：如果A，B两链表的差值为length，假设A的长度长，当b遍历到链表尾时，a一定没
    // 到链表尾（lengthA>lengthB）,a和b之间的差距就是length，而b遍历完B后开始遍历A，a
    // 遍历完开始遍历B，这个差值就会被补上，在a开始遍历B时，a和b指针就会形成距离相交点（如果
    // 存在）相同距离的场面。
    /*public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode a = headA, b = headB, res = null;

        //flagA作用：标记a是否已经遍历完了B链表
        boolean flagA = false,flagB = false;

        while (a != null && b != null) {
            if (a == b) {
                res = a;
                break;
            }

            if (a.next == null) {
                if (flagA) {
                    break;
                }
                flagA = true;
                a = headB;
            } else if (b.next == null) {
                if (flagB) {
                    break;
                }
                flagB = true;
                b = headA;
            }
            a = a.next;
            b = b.next;
        }

        return res;
    }*/
    //--------------------------------------------------------------

    //--------------------------------------------------------------------
    //                      双指针妙用的精简版
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        //tempA和tempB我们可以认为是A,B两个指针
        ListNode tempA = headA;
        ListNode tempB = headB;

        //由于各自遍历完两个链表后，tempA和tempB会同时为null，所以不需要额外的判断退出循环
        while (tempA != tempB) {
            //如果指针tempA不为空，tempA就往后移一步。
            //如果指针tempA为空，就让指针tempA指向headB（注意这里是headB不是tempB）
            tempA = tempA == null ? headB : tempA.next;
            //指针tempB同上
            tempB = tempB == null ? headA : tempB.next;
        }
        //tempA要么是空，要么是两链表的交点
        return tempA;
    }
}
