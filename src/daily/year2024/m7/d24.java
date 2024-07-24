package daily.year2024.m7;

public class d24 {

     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    //思路：使用快慢指针定位中间节点，然后进行链表合并
    public ListNode sortList(ListNode head) {
        return dfsSortNode(head);
    }

    private ListNode dfsSortNode(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode quick = head, slow = head, pre = null;
        while(quick != null) {
            pre = slow;
            slow = slow.next;
            quick = quick.next;
            if(quick == null) {
                break;
            }
            quick = quick.next;
        }
        ListNode node1 = head;
        pre.next = null;
        ListNode node2 = slow;
        ListNode left = dfsSortNode(node1);
        ListNode right = dfsSortNode(node2);
        //合并两个链表
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(left != null && right != null) {
            if(left.val < right.val) {
                cur.next = left;
                pre = left;
                left = left.next;
                pre.next = null;
            } else {
                cur.next = right;
                pre = right;
                right = right.next;
                pre.next = null;
            }
            cur = cur.next;
        }
        if(left != null) {
            cur.next = left;
        }
        if(right != null) {
            cur.next = right;
        }
        return dummy.next;
    }
}
