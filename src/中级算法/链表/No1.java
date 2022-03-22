package 中级算法.链表;

public class No1 {
    //                      两数相加
    //给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    //请你将两个数相加，并以相同形式返回一个表示和的链表。
    //你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    //--------------------------------------------------------------------------------
    //                      成功，击败百分百
    //先思考：如何相加，使用双指针遍历两个链表可以吗？设置一个进位值
    //就是代码有点长
    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1,p2 = l2,l3 = new ListNode(),resHead = l3,p3 = l3;

        //记录进位的变量
        int c = 0,temp;

        while (p1 != null && p2 != null) {
            temp = p1.val + p2.val + c;
            if (temp >= 10) {
                c = 1;
                temp = temp % 10;
            }else{
                c = 0;
            }

            p3 = l3;
            l3.val = temp;
            l3.next = new ListNode();
            l3 = l3.next;

            p1 = p1.next;
            p2 = p2.next;
        }

        while (p1 != null) {
            temp = p1.val  + c;
            if (temp >= 10) {
                c = 1;
                temp = temp % 10;
            }else{
                c = 0;
            }

            p3 = l3;
            l3.val = temp;
            l3.next = new ListNode();
            l3 = l3.next;

            p1 = p1.next;
        }

        while (p2 != null) {
            temp = p2.val + c;
            if (temp >= 10) {
                c = 1;
                temp = temp % 10;
            }else{
                c = 0;
            }

            p3 = l3;
            l3.val = temp;
            l3.next = new ListNode();
            l3 = l3.next;

            p2 = p2.next;
        }

        if (c == 1) {
            l3.val = c;
            l3.next = null;
        }else{
            p3.next = null;
        }

        return resHead;
    }*/
    //-----------------------------------------------------------------------------

    //-----------------------------------------------------------------------------
    //使用递归？
    ListNode l3 = new ListNode();
    ListNode p3 = l3,pre;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){

        recursion(l1,l2,0);

        return l3;
    }

    //c是进位
    public void recursion(ListNode p1,ListNode p2,int c){
        int temp,newC;

        if (p1 != null && p2 != null) {
            temp = p1.val + p2.val + c;
        } else if (p1 != null) {
            temp = p1.val + c;
        } else if (p2 != null) {
            temp = p2.val + c;
        }else {
            if (c == 0) {
                pre.next = null;
            }else {
                p3.val = c;
            }
            return;
        }

        if (temp >= 10) {
            temp = temp % 10;
            newC = 1;
        }else{
            newC = 0;
        }

        pre = p3;
        p3.val = temp;
        p3.next = new ListNode();
        p3 = p3.next;

        if (p1 != null && p2 != null) {
            recursion(p1.next,p2.next,newC);
        } else if (p1 != null) {
            recursion(p1.next,null,newC);
        } else {
            recursion(null,p2.next,newC);
        }
    }
}
