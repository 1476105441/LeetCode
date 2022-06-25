package 复习.链表;

import 高级算法.链表.ListNode;

public class No11 {
    /**
     *      排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     *
     * 提示：
     *
     *     链表中节点的数目在范围 [0, 5 * 104] 内
     *     -105 <= Node.val <= 105
     *
     * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     */

    //目前一种思路：归并排序，完成，这个写法真是太耗脑子了
    /*public ListNode sortList(ListNode head){
        if (head == null) {
            return null;
        }
        //span是组距，每组之间的距离，l是组内元素相隔的距离
        int span = 2,l = 1,length = 0,count;
        ListNode cur = head,l1,l2,pre = null,p2 = null,temp,newHead = new ListNode();
        newHead.next = head;
        while (cur != null) {
            cur = cur.next;
            length++;
        }

        while (l < length) {
            cur = newHead.next;
            temp = newHead;
            while (cur != null) {
                count = 0;
                l1 = cur;
                l2 = cur;
                //限定条件还有cur即下一组起始不能为空
                while (count < l && l2 != null) {
                    p2 = l2;
                    l2 = l2.next;
                    if (cur != null) {
                        cur = cur.next;
                    }
                    pre = cur;
                    if (cur != null) {
                        cur = cur.next;
                    }
                    count++;
                }
                if (l2 != null) {
                    p2.next = null;
                    if (pre != null) {
                        pre.next = null;
                    }
                    temp.next = mergeTwo(l1,l2);
                }else
                    temp.next = l1;
                while (temp.next != null) {
                    temp = temp.next;
                }
            }
            l = l << 1;
            span = span << 1;
        }

        return newHead.next;
    }

    public ListNode mergeTwo(ListNode l1,ListNode l2){
        ListNode newHead = new ListNode(),cur1 = l1,cur2 = l2,pre1 = newHead,next;
        newHead.next = l1;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                next = l2.next;
                l2.next = l1;
                pre1.next = l2;
                pre1 = pre1.next;
                l2 = next;
            }else{
                pre1 = l1;
                l1 = l1.next;
            }
        }

        if (l2 != null) {
            pre1.next = l2;
        }

        return newHead.next;
    }*/

    /*public ListNode sortList(ListNode head){
        ListNode newHead = new ListNode(),cur1,cur2,pre1,pre2,next,temp;
        int l = 1,len = 0,count = 0;

        next = head;
        pre1 = null;
        pre2 = null;
        while (next != null) {
            next = next.next;
            len++;
        }

        newHead.next = head;
        while (l < len) {
            temp = newHead;
            next = newHead.next;
            while (next != null) {
                cur1 = next;
                cur2 = next;
                count = 0;
                while (count < l && cur2 != null) {
                    pre1 = cur2;
                    cur2 = cur2.next;
                    count++;
                    if (next != null) {
                        next = next.next;
                    }
                    pre2 = next;
                    if (next != null) {
                        next = next.next;
                    }
                }

                pre1.next = null;
                if (pre2 != null) {
                    pre2.next = null;
                }else
                    temp.next = cur1;
                temp.next = mergeTwo(cur1,cur2);
                while (temp.next != null) {
                    temp = temp.next;
                }
            }
            l <<= 1;
        }

        return newHead.next;
    }

    public ListNode mergeTwo(ListNode l1,ListNode l2){
        ListNode newHead = new ListNode(),pre = newHead,next;
        newHead.next = l1;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                next = l2.next;
                l2.next = l1;
                pre.next = l2;
                l2 = next;
                pre = pre.next;
            }else{
                pre = l1;
                l1 = l1.next;
            }
        }

        if (l2 != null) {
            pre.next = l2;
        }

        return newHead.next;
    }*/

    //分治法来归并排序
    public ListNode sortList(ListNode head){
        if (head == null) {
            return null;
        }
        int len = 0,i = 0;
        ListNode temp = head;

        while (temp != null) {
            temp = temp.next;
            len++;
        }

        ListNode[] listNodes = new ListNode[len];
        temp = head;

        //注意细节，每个节点要切割开来
        while (temp != null) {
            listNodes[i] = temp;
            temp = temp.next;
            listNodes[i].next = null;
            i++;
        }
        sort(listNodes,0,len-1);

        return listNodes[0];
    }

    public void sort(ListNode[] listNodes,int left,int right){
        if(left >= right){
            return;
        }
        int center = left + (right-left)/2;
        sort(listNodes,left,center);
        sort(listNodes,center+1,right);

        listNodes[left] = mergeTwo(listNodes[left],listNodes[center+1]);
    }

    public ListNode mergeTwo(ListNode l1,ListNode l2){
        ListNode newHead = new ListNode(),pre,next;
        pre = newHead;
        newHead.next = l1;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                next = l2.next;
                l2.next = l1;
                pre.next = l2;
                pre = pre.next;
                l2 = next;
            }else{
                pre = l1;
                l1 = l1.next;
            }
        }

        if (l2 != null) {
            pre.next = l2;
        }

        return newHead.next;
    }
}
