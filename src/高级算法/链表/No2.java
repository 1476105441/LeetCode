package 高级算法.链表;

public class No2 {
    /**
     *          排序链表
     * 给你链表的头节点head，请将其按升序排列并返回排序后的链表
     */

    /**
     * 解法：使用递归模拟快速排序，但是失败了，栈溢出
     */
    /*public ListNode sortList(ListNode head){
        sort(head,head,null);
        return head;
    }

    public ListNode sort(ListNode left,ListNode right,ListNode end){
        if (right == end) {
            return left;
        }
        ListNode l1 = sort(left,right.next,end);

        if (l1.val > right.val) {
            int temp = l1.val;
            l1.val = right.val;
            right.val = temp;
            while (l1 != right) {
                l1 = l1.next;
                if (l1 != null && l1.val > right.val) {
                    temp = l1.val;
                    l1.val = right.val;
                    right.val = temp;
                    return l1;
                }
            }
        }
        if (l1 == right) {
            sort(left,left,right);
            sort(right.next,right.next,end);
            return left;
        }
        return l1;
    }*/

    //---------------------------------------------------------------------------------------------------
    /**
     *想法：归并排序能否达到最优解的效率
     * 归并排序可以达到最优解的效率
     */

    /**
     * 第一种归并排序：自顶向下递归的排序
     * 思想：将链表一分为二，使用快慢指针来进行一分为二的操作，
     * 然后分别对两半边进行上述操作的排序，递归终止条件是链表
     * 个数为1，当两边的链表分别排序完成之后，再将他们合并
     */
    /*public ListNode sortList(ListNode head){
        return recursion(head);
    }
    public ListNode recursion(ListNode node){
        if (node == null || node.next == null) {
            return node;
        }
        ListNode center=node,quick=node.next,right,left=node;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            center = center.next;
        }
        right = center.next;
        center.next = null;

        left = recursion(left);
        right = recursion(right);

        //合并左右子链表
        return mergeTwo(left,right);
    }

    public ListNode mergeTwo(ListNode node1,ListNode node2){
        ListNode l1,pre=node1,l2s=node2,l2e=node2,next2=l2e.next;

        //第一次判断时，第二个链表小于第一个链表的情况特殊处理
        if (pre.val > l2e.val) {
            while (next2 != null && next2.val < pre.val) {
                l2e = next2;
                next2 = next2.next;
            }
            l2e.next = pre;
            node1 = l2s;
            l2s = next2;
        }
        while (l2s != null) {
            l2e = l2s;
            next2 = l2e.next;
            l1 = pre.next;

            //正常处理
            if (l1 != null && l1.val > l2e.val) {
                while (next2 != null && next2.val < l1.val) {
                    l2e = next2;
                    next2 = next2.next;
                }
                pre.next = l2s;
                l2e.next = l1;
                l2s = next2;
                pre = l1;
            }else if (l1 == null && pre.val <= l2s.val) {
                //当第一个链表到了最后一个而且比第二个链表中的节点值要小的情况特殊处理
                pre.next = l2s;
                break;
            } else{
                pre = l1;
            }
        }

        return node1;
    }*/

    /**
     * 归并排序的第二种方式：自底向上
     * 和我一开始归并排序的思路一致，但是我分析时
     * 加上了其遍历的时间，所以认为这个方法不符合
     * 题目要求，但其实是符合的
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
