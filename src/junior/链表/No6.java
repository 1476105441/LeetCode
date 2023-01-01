package junior.链表;

public class No6 {
    //                     环形链表
    //给定一个链表，判断链表中是否有环。
    //如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    //如果链表中存在环，则返回 true 。 否则，返回 false 。
    //
    //进阶：
    //你能用 O(1)（即，常量）内存解决此问题吗？
    //提示：
    //
    //    链表中节点的数目范围是 [0, 104]
    //    -105 <= Node.val <= 105
    //    pos 为 -1 或者链表中的一个 有效索引 。

    //其实就是在链表中查重，用哈希表
    //6ms，被吊打
    //-------------------------------------------------------------------------
    /*public boolean hasCycle(ListNode head) {
        //哈希表中存放节点和下标值
        Map<ListNode,Integer> map = new HashMap();
        ListNode point = head;
        int pos = 0;
        while (point != null) {
            if (map.containsKey(point)){
                return true;
            }
            map.put(point,pos++);
            point = point.next;
        }
        return false;
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //快慢指针：快指针一次移动两个节点，慢指针一次移动一个节点，如果有环，快指针必定会追
    // 上慢指针，反之，快指针会为空（自己想死活没想出来啊，之前的题目也有这种快慢指针，是
    // 寻找数组的中间位置，下次遇到类似的一定要想到这种一快一慢的指针解法）
    /*public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        //快慢两个指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            //慢指针每次走一步
            slow = slow.next;
            //快指针每次走两步
            fast = fast.next.next;
            //如果相遇，说明有环，直接返回true
            if (slow == fast)
                return true;
        }
        //否则就是没环
        return false;
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //  要具有一题多解的想法，一定要把多种解法看明白，这样你的解题思维就会得到拓展
    //
    //删除链表法：每遍历一个节点，先设置一个临时指针指向它的下一个节点，然后将该节点删除，实
    // 际上是让该节点的next等于它自己，然后删除temp节点，重复上述步骤，若没有环的话，最终
    // 遍历用的指针会为null，否则会出现节点的next等于它本身
    /*public boolean hasCycle(ListNode head) {
        //如果head为空，或者他的next指向为空，直接返回false
        if (head == null || head.next == null)
            return false;
        //如果出现head.next = head表示有环
        if (head.next == head)
            return true;
        ListNode nextNode = head.next;
        //当前节点的next指向他自己，相当于把它删除了
        head.next = head;
        //然后递归，查看下一个节点
        return hasCycle(nextNode);
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //反转比较法：将链表反转，再比较反转之后的头节点与之前的，如果头结点相同，说明有环（
    // 具体分析可以明白，在反转过程中节点的顺序会发生改变，有环的话最终反转后的头结点就是原来的头结点）
    public ListNode reverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            //留着下一步访问的
            ListNode temp = head.next;
            //每次访问的原链表节点都会成为新链表的头结点，
            //其实就是把新链表挂到访问的原链表节点的
            //后面就行了
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }

    public boolean hasCycle(ListNode head) {
        ListNode rev = reverseList(head);
        if (head != null && head.next != null && rev == head) {
            return true;
        }
        return false;
    }
    //-------------------------------------------------------------------------
}
