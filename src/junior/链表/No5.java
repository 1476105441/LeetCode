package junior.链表;

public class No5 {
    //                回文链表
    //  给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
    //提示：
    //    链表中节点数目在范围[1, 10的5次方] 内
    //    0 <= Node.val <= 9
    //进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

    //-------------------------------------------------------------------------
    //17ms，垃圾，被吊打
    /*
    boolean flag = true;
    public boolean isPalindrome(ListNode head) {
        recursion(head,head);
      return flag;
    }
    //递归，函数参数传递是值传递，就算是一个指针也是传的地址数据
    public ListNode recursion(ListNode front,ListNode rear){
        if (rear == null) {
            return front;
        }
        front = recursion(front,rear.next);
        if (front == null) {
            return null;
        }
        if (front.val != rear.val) {
            flag = false;
            return null;
        }
        //说明已经对比过一遍了
        if (front.next == rear || rear.next == front) {
            return null;
        }
        return front.next;
    }*/
    //------------------------------------------------------------------------------

    //------------------------------------------------------------------------------
    //尝试用数组解决，不行，链表节点数目太大，看成了105个
    /*public boolean isPalindrome(ListNode head) {
        int[] nums = new int[110];
        int num =0;
        while (head != null) {
            nums[num++] = head.val;
            head = head.next;
        }
        int j;
        for (int i = 0; i < num; i++) {
            j = num - i - 1;
            if (nums[i] != nums[j]){
                return false;
            }
        }
        return true;
    }*/
    //-----------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------
    //较优版本：双指针加反转数组（不过这个是先找到中间位置，再反转数组）
    //双指针寻找链表的中间位置，quick指针的移动长度是slow的两倍，当quick到达null时，slow位置就是
    // 中间位置，如果quick不为null，说明链表节点个数为奇数，slow指在中间位置，但要前后两段对应得上，就让slow+1
    /*public boolean isPalindrome(ListNode head){
        ListNode newHead = null,point,slow = head,quick = head;
        //找到中间位置，顺便把前半部分的链表反转过来
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        //链表个数为奇数
        if (quick != null) {
            slow = slow.next;
        }
        //对后半部分反转数组
        while (slow != null) {
            point = slow;
            slow = slow.next;
            point.next = newHead;
            newHead = point;
        }
        while (newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }*/

    //-----------------------------------------------------------------------------------------

    //最优版本：
    //反转链表加双指针（找中间位置的同时反转指针）
    public boolean isPalindrome(ListNode head){
        ListNode newHead = null,point,slow = head,quick = head;
        //找到中间位置，顺便把前半部分的链表反转过来

        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            point = slow;
            slow = slow.next;
            point.next = newHead;
            newHead = point;
        }

        //链表个数为奇数
        if (quick != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != newHead.val) {
                return false;
            }
            slow = slow.next;
            newHead = newHead.next;
        }
        return true;
    }
}
