package 初级算法.链表;

public class No4 {
    //                   合并两个有序链表
    //将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    //提示：
    //
    //    两个链表的节点数目范围是 [0, 50]
    //    -100 <= Node.val <= 100
    //    l1 和 l2 均按 非递减顺序 排列

    //--------------------------------------------------------------------------
    //递归解法，学习借鉴一下，0ms，代码十分的简洁
    /*public ListNode mergeTwoLists(ListNode linked1, ListNode linked2) {
        //只要有一个为空，就返回另一个
        if (linked1 == null || linked2 == null)
            return linked2 == null ? linked1 : linked2;
        //把小的赋值给first
        ListNode first = (linked2.val < linked1.val) ? linked2 : linked1;
        first.next = mergeTwoLists(first.next, first == linked1 ? linked2 : linked1);
        return first;
    }*/
    //--------------------------------------------------------------------------

    //双指针,0ms
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(),now = head,p1 = l1,p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                now.next = p1;
                p1 = p1.next;
            }else{
                now.next = p2;
                p2 = p2.next;
            }
            now = now.next;
        }
        while (p1 != null) {
            now.next = p1;
            now = now.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            now.next = p2;
            now = now.next;
            p2 = p2.next;
        }
        return head.next;
    }
}
