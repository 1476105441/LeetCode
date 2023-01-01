package senior.链表;

import java.util.HashMap;
import java.util.Map;

public class No3 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        int size = 0,count = 0;
        Node p1 = head,res,p2;
        Map<Node,Integer> map = new HashMap<>();

        while (p1 != null) {
            map.put(p1,size);
            p1 = p1.next;
            size++;
        }
        p1 = head;

        Node[] nodes = new Node[size];

        //res相当于是链表的头节点
        res = new Node(-1);
        p2 = res;
        while (p1 != null) {
            //先判断下一个节点是否存在于数组中
            if (p2.next == null) {
                if (nodes[count] != null) {
                    p2.next = nodes[count];
                }else{
                    p2.next = new Node(p1.val);
                }
            }

            //设置下一个节点的值
            p2 = p2.next;
            nodes[count] = p2;
            Integer loc = map.get(p1.random);
            if (loc != null) {
                if (nodes[loc] != null) {
                    p2.random = nodes[loc];
                }else{
                    p2.random = new Node(p1.random.val);
                    nodes[loc] = p2.random;
                }
            }else{
                p2.random = null;
            }
            count++;
            p1 = p1.next;
        }

        return res.next;
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
