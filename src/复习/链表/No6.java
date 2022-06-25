package 复习.链表;

import 高级算法.链表.ListNode;

public class No6 {
    /**
     *      环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中
     * 存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链
     * 表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行
     * 传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     *
     * 提示：
     *     链表中节点的数目范围是 [0, 104]
     *     -105 <= Node.val <= 105
     *     pos 为 -1 或者链表中的一个 有效索引 。
     *
     * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
     */

    //快慢指针，如果链表当中有环的话，两个指针最终一定会相遇
    /*public boolean hasCycle(ListNode head){
        ListNode quick = head,slow = head;

        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) {
                return true;
            }
        }

        return false;
    }*/

    //解法二：逐个删除，其实就是每遍历一个就让其的next指针指向自己
    //效率不如递归的高
    /*public boolean hasCycle(ListNode head){
        ListNode cur = head,temp;

        while (cur != null) {
            if (cur.equals(cur.next)) {
                return true;
            }
            temp = cur;
            cur = cur.next;
            temp.next = temp;
        }

        return false;
    }*/

    //解法三：反转链表，如果有环，则反转后的头节点一定等于原来的头节点
    public boolean hasCycle(ListNode head){
        if (head == null || head.next ==null) {
            return false;
        }
        ListNode newHead = null,temp,cur = head;
        while (cur != null) {
            temp = cur;
            cur = cur.next;
            temp.next = newHead;
            newHead = temp;
        }

        return head.equals(newHead);
    }
}
