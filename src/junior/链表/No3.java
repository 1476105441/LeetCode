package junior.链表;


public class No3 {
    //                 反转链表
    //  给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
    //有一个递归的思路：先递归找到最后一个节点，然后再递归把前面的节点接到最后一个节点上,不知道会不会栈溢出

    //----------------------------------------------------------------------------------------
    //0ms，击败百分之百，但是内存消耗不太理想（我的递归解法）
    /*
    ListNode res = null;
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        recursion(head,head.next);
        return res;
    }

    public ListNode recursion(ListNode now , ListNode next){
        if (next == null) {
            res = now;
            return now;
        }
        //t节点是新拼接的链表的最后一个节点
        ListNode t = recursion(now.next,now.next.next);
        //t.next就等于null，直接赋值null就可以
        now.next = null;
        t.next = now;
        return now;
    }*/
    //--------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //利用堆栈解决，内存消耗还行，1ms才击败百分之5
    /*
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        //把链表节点全部摘掉放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty())
            return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        //栈中的结点全部出栈，然后重新连成一个新的链表
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        //最后一个结点就是反转前的头结点，一定要让他的next
        //等于空，否则会构成环
        node.next = null;
        return dummy;
    }*/
    //--------------------------------------------------------------------------------------------
    /*
    //我的双链表解法
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        //双指针解决链表翻转问题
        ListNode newNode = null, point1 = head, point2;
        while (point1 != null) {
            point2 = point1;
            point1 = point1.next;
            point2.next = newNode;
            newNode = point2;
        }
        return newNode;
    }*/

    //--------------------------------------------------------------------------------------------
    //双链表解决，就是建立一个新链表，0ms，内存消耗击败百分之80
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
    //--------------------------------------------------------------------------------------------
}
