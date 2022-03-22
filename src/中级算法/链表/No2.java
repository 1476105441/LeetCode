package 中级算法.链表;

public class No2 {
    //                  奇偶链表
    //给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
    //请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
    //
    //说明:
    //    应当保持奇数节点和偶数节点的相对顺序。
    //    链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

    //想法：一个奇指针，一个偶指针，分别指向奇数节点串的末尾和偶数节点串的末尾
    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        //on是奇数指针串的末尾位置，en是偶数指针串的末尾位置
        ListNode on = head,en = head.next,p;

        while (en != null && en.next != null) {
            p = en.next;
            en.next = p.next;
            p.next = on.next;
            on.next = p;
            on = on.next;
            en = en.next;
        }

        return head;
    }
}
