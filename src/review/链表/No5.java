package review.链表;

import senior.链表.ListNode;

public class No5 {
    /**
     *      回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
     * 如果是，返回 true ；否则，返回 false 。
     *
     * 提示：
     *     链表中节点数目在范围[1, 105] 内
     *     0 <= Node.val <= 9
     *
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */

    //1、使用堆栈，27ms，很慢
    /*public boolean isPalindrome(ListNode head){
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        int len = 0,i = 0,half;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
            len++;
        }

        cur = head;
        half = len / 2;
        while (i < half) {
            if (cur.val != stack.pop().val) {
                return false;
            }
            cur = cur.next;
            i++;
        }
        return true;
    }*/

    //2、反转链表
    /*public boolean isPalindrome(ListNode head){
        ListNode newHead = null,cur = head,l1;
        int len = 0,i = 0,half;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        half = len / 2;
        cur = head;
        while (i < half) {
            i++;
            l1 = cur;
            cur = cur.next;
            l1.next = newHead;
            newHead = l1;
        }
        if ((len & 1) != 0) {
            cur = cur.next;
        }
        l1 = newHead;

        while (l1 != null && cur != null) {
            if (l1.val != cur.val) {
                return false;
            }
            l1 = l1.next;
            cur = cur.next;
        }
        if (l1 != null || cur != null) {
            return false;
        }
        return true;
    }*/

    //3、优化一下反转链表，通过快慢指针寻找中间节点，边寻找边反转数组
    //通过观察，如果链表是个数是奇数，则快指针最终不为空，快指针下一个
    //才为空，根据这个特点做判断
    public boolean isPalindrome(ListNode head){
        ListNode newHead = null,temp,quick = head,slow = head;

        while (quick != null && quick.next != null) {
            temp = slow;
            slow = slow.next;
            quick = quick.next.next;
            temp.next = newHead;
            newHead = temp;
        }

        if (quick != null) {
            slow = slow.next;
        }

        while (newHead != null && slow != null) {
            if (newHead.val != slow.val) {
                return false;
            }
            newHead = newHead.next;
            slow = slow.next;
        }

        return true;
    }
}
