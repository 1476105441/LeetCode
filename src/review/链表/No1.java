package review.链表;

import senior.链表.ListNode;

public class No1 {
    /**
     *      删除链表中的节点
     * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
     * 题目数据保证需要删除的节点 不是末尾节点 。
     *
     * 提示：
     *     链表中节点的数目范围是 [2, 1000]
     *     -1000 <= Node.val <= 1000
     *     链表中每个节点的值都是 唯一 的
     *     需要删除的节点 node 是 链表中的节点 ，且 不是末尾节点
     */

    //这题目描述太坑人，其实就是删除当前节点，并且当前节点不是最后一个节点
    public void delete(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
