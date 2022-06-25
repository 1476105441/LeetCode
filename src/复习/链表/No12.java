package 复习.链表;

import java.util.HashMap;
import java.util.Map;

public class No12 {
    /**
     *      复制带随机指针的链表
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random
     * ，该指针可以指向链表中的任何节点或空节点。构造这个链表的 深拷贝。 深
     * 拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的
     * 原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
     * 并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表
     * 中的指针都不应指向原链表中的节点 。
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
     * 那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * 返回复制链表的头节点。
     *
     * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     *     val：一个表示 Node.val 的整数。
     *     random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     *
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     *
     * 提示：
     *     0 <= n <= 1000
     *     -104 <= Node.val <= 104
     *     Node.random 为 null 或指向链表中的节点
     */

    /*public Node copyRandomList(Node head){
        if(head == null){
            return null;
        }
        Map<Node,Integer> map1 = new HashMap<>();
        Map<Integer,Node> map2 = new HashMap<>();
        int count = 0;
        Node temp = head,newHead = new Node(0),pre = newHead;
        while (temp != null) {
            map1.put(temp,count++);
            temp = temp.next;
        }

        temp = head;
        count = 0;
        while (temp != null) {
            Node node = map2.get(count);
            if (node == null) {
                node = new Node(temp.val);
                map2.put(count,node);
            }
            pre.next = node;
            pre = pre.next;

            Integer randomNum = map1.get(temp.random);
            Node random = map2.get(randomNum);
            if (random == null && temp.random != null) {
                random = new Node(temp.random.val);
                map2.put(randomNum,random);
            }
            node.random = random;

            count++;
            temp = temp.next;
        }

        return newHead.next;
    }*/


    public Node copyRandomList(Node head){
        Map<Node,Integer> map = new HashMap<>();
        Node cur = head,newHead = new Node(-1),next = newHead;
        int count = 0;

        while (cur != null) {
            map.put(cur,count);
            cur = cur.next;
            count++;
        }
        Node[] temp = new Node[count];

        cur = head;
        count = 0;
        while (cur != null) {
            Node node = temp[count];
            if (node == null) {
                node = new Node(cur.val);
                temp[count] = node;
            }
            Integer randomNum = map.get(cur.random);
            if (randomNum != null ) {
                if (temp[randomNum] != null) {
                    node.random = temp[randomNum];
                }else{
                    node.random = new Node(cur.random.val);
                    temp[randomNum] = node.random;
                }
            }else
                node.random = null;

            count++;
            next.next = node;
            next = next.next;
            cur = cur.next;
        }

        return newHead.next;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
