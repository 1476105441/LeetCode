package senior.链表;

public class No1 {
    /**
     *          合并k个排序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */

    /**
     * 想法1：使用多指针
     * 缺陷：太慢，300多ms，被别人吊打
     */
    /*public ListNode mergeKLists(ListNode[] lists){
        ListNode[] points = new ListNode[lists.length];
        ListNode head = new ListNode(),res = head;
        boolean flag = true;
        int min,loc = -1;
        for (int i = 0; i < lists.length; i++) {
            points[i] = lists[i];
        }

        while (flag) {
            min = Integer.MAX_VALUE;
            flag = false;
            for (int i = 0; i < lists.length; i++) {
                if(!flag && points[i] != null){
                    flag = true;
                }

                if (points[i] != null && points[i].val < min){
                    min = points[i].val;
                    loc = i;
                }
            }
            if (flag) {
                res.next = new ListNode();
                res = res.next;
                res.val = points[loc].val;
                points[loc] = points[loc].next;
            }
        }

        return head.next;
    }*/


    /**
     * 想法2：分治
     * 两两合并链表，知道只有一条链表为止
     */
    /*public ListNode mergeKLists(ListNode[] lists) {
        int span = 2,ispan;
        if (lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        while (span < lists.length) {
            ispan = span /2;
            for (int i = 0; i < lists.length - ispan; i+=span) {
                lists[i] = mergeTwo(lists[i],lists[i+ispan]);
            }
            span *= 2;
        }
        span /= 2;

        return mergeTwo(lists[0],lists[span]);
    }

    public ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode p1 = l1, p2 = l2, next1, next2, head = l1;
        if (p2 != null) {
            next2 = p2.next;
            if (p1.val > p2.val) {
                p2.next = p1;
                head = p2;
                p1 = head;
                p2 = next2;
            }
        }

        while (p2 != null) {
            next1 = p1.next;
            next2 = p2.next;

            if (next1 != null && next1.val > p2.val) {
                p2.next = next1;
                p1.next = p2;
                p1 = p1.next;
                p2 = next2;
            } else if (next1 == null) {
                if (p1.val <= p2.val) {
                    p1.next = p2;
                    break;
                }
            }else {
                p1 = next1;
            }
        }

        return head;
    }*/

    /**
     * 想法3：堆
     * 与第一种解法相似，只是在选取最小值
     * 时使用堆来缩短选的时间
     */
    public ListNode mergeKLists(ListNode[] lists){
        if (lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        int n = lists.length,count = n;
        ListNode[] temp = new ListNode[n];
        ListNode res,node;
        for (int i = 0; i < n; i++) {
            temp[i] = lists[i];
        }
        merge(temp,temp.length);
        res = temp[0];
        node = res;
        if (temp[0] != null) {
            temp[0] = temp[0].next;
        }
        shift(temp,0,count);
        while (count > 0) {
            if (temp[0] == null) {
                temp[0] = temp[count-1];
                count--;
            }else{
                node.next = temp[0];
                temp[0] = temp[0].next;
                node = node.next;
            }
            if (temp[0] != null) {
                shift(temp,0,count);
            }
        }
        return res;
    }

    public void shift(ListNode[] list,int loc,int n){
        int i = loc,j = 2 * i + 1;
        ListNode temp;
        while (j < n) {
            if (j + 1 < n && (list[j] == null || list[j+1] != null && list[j + 1].val < list[j].val)) {
                j++;
            }
            if (list[i] == null || (list[j] != null && list[i].val > list[j].val)) {
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i = j;
                j = 2*i+1;
            }else{
                break;
            }
        }
    }

    public void merge(ListNode[] list,int n){
        for (int i = (n-2)/2; i > -1; i--) {
            shift(list,i,n);
        }
    }
}
