package 初级算法.链表;

public class No2 {
    //           删除链表的倒数第N个节点
    //给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
    //进阶：你能尝试使用一趟扫描实现吗？

    //-------------------------------------------------------------------------
    /*
    //非递归算法
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        int last = length(head) - n;
        //如果last等于0表示删除的是头结点
        if (last == 0)
            return head.next;
        //这里首先要找到要删除链表的前一个结点
        for (int i = 0; i < last - 1; i++) {
            pre = pre.next;
        }
        //然后让前一个结点的next指向要删除节点的next
        pre.next = pre.next.next;
        return head;
    }

    //求链表的长度
    private int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    */
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    /*
    //递归且逆序解决，这个想法很牛
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int pos = length(head, n);
        System.out.println(pos);
        // 说明删除的是头节点
        if (pos == n)
            return head.next;
        return head;
    }

    // 获取节点所在位置，逆序
    public int length(ListNode node, int n) {
    //这一步确定了如果pos等于1，node就是倒数第一个，pos等于n，node就是倒数第n个
        if (node == null)
            return 0;
        int pos = length(node.next, n) + 1;
        //获取要删除链表的前一个结点，就可以完成链表的删除
        if (pos == n + 1)
            node.next = node.next.next;
        return pos;
    }
    */
    //-------------------------------------------------------------------------

    //双指针,0ms
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode quick = head , slow = head;
        int k=0;
        //让快指针指向离slow指针n+1个节点的位置
        while (quick != null) {
            if (k == n+1) {
                break;
            }
            quick = quick.next;
            k++;
        }
        if (k != n+1) {
            return slow.next;
        }
        while (slow.next != null) {
            if (quick == null) {
                slow.next = slow.next.next;
                break;
            }
            slow = slow.next;
            quick = quick.next;
        }

        return head;
    }
}

