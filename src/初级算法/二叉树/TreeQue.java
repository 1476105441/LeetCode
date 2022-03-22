package 初级算法.二叉树;

//自己实现的链表队列，用于BFS
public class TreeQue {
    int size;
    //头指针
    Node front;
    Node rear;

    public TreeQue() {
        size = 0;
        front = new Node();
        rear = front;
    }

    //添加方法
    public void add(TreeNode t){
        rear.treeNode = t;
        rear.next = new Node();
        rear = rear.next;
        size++;
    }

    //出队列
    public TreeNode poll(){
        if (size == 0) {
            throw new RuntimeException("队列节点数已经为零，出队失败");
        }

        TreeNode node = front.treeNode;
        front = front.next;
        size--;
        return node;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
//链表中的一个节点
class Node{
    TreeNode treeNode;
    Node next;
}
