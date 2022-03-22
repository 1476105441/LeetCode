package 中级算法.二叉树和图;


public class TreeQue {
    int size;
    //头指针
    node front;
    node rear;

    public TreeQue() {
        size = 0;
        front = new node();
        rear = front;
    }

    //添加方法
    public void add(TreeNode t){
        rear.treeNode = t;
        rear.next = new node();
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
class node{
    TreeNode treeNode;

    node next;

    public node(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public node() {}
}
