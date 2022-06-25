package 复习.链表;

import 高级算法.链表.ListNode;

public class No10 {
    /**
     *      合并k个排序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 提示：
     *     k == lists.length
     *     0 <= k <= 10^4
     *     0 <= lists[i].length <= 500
     *     -10^4 <= lists[i][j] <= 10^4
     *     lists[i] 按 升序 排列
     *     lists[i].length 的总和不超过 10^4
     */

    //想法：维护一个小根堆，每次取出堆顶，调整堆顶
    /*public ListNode mergeKLists(ListNode[] lists){
        if (lists.length == 0) {
            return null;
        }
        int count = 0,k = lists.length;
        ListNode[] temp = new ListNode[k];
        ListNode res = new ListNode(),cur = res;
        for (int i = 0; i < k; i++) {
            //只有不为null时才算进个数里
            if (lists[i] != null) {
                temp[count++] = lists[i];
            }
        }

        //调整为小根堆
        for (int i = (count-2)/2; i >= 0; i--) {
            shift(temp,i,count);
        }

        while (count > 0) {
            cur.next = temp[0];
            cur = cur.next;
            temp[0] = temp[0].next;
            if (temp[0] == null) {
                count--;
                temp[0] = temp[count];
                temp[count] = null;
            }
            shift(temp,0,count);
        }

        return res.next;
    }

    public void shift(ListNode[] nodes,int cur,int n){
        int i = cur,j = 2*i+1;
        while (j < n) {
            if (j < n - 1 && nodes[j].val > nodes[j + 1].val) {
                j++;
            }

            if (nodes[i].val > nodes[j].val) {
                ListNode temp = nodes[i];
                nodes[i] = nodes[j];
                nodes[j] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }*/

    //想法：分治法，两两合并,没有使用递归的分治，而是采用希尔排序类似的以组为单位
    public ListNode mergeKLists(ListNode[] lists){
        if (lists.length == 0) {
            return null;
        }

        int k = lists.length,span = 2,l = span >> 1;
        while (l < k) {
            for (int i = 0; i < k - l; i+=span) {
                lists[i] = mergeTwo(lists[i],lists[i+l]);
            }
            l = span;
            span = span << 1;
        }

        return lists[0];
    }

    public ListNode mergeTwo(ListNode l1,ListNode l2){
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode cur1 = l1,cur2 = l2,newHead = new ListNode(),pre = newHead,pre2;
        pre.next = l1;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                pre2 = l2.next;
                l2.next = l1;
                pre.next = l2;
                l2 = pre2;
            }else {
                l1 = l1.next;
            }
            pre = pre.next;
        }

        if (l1 == null && l2 != null) {
            pre.next = l2;
        }
        return newHead.next;
    }
}
