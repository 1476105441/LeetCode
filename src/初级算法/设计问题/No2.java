package 初级算法.设计问题;

public class No2 {
    //                最小栈
    //设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
    //    push(x) —— 将元素 x 推入栈中。
    //    pop() —— 删除栈顶的元素。
    //    top() —— 获取栈顶元素。
    //    getMin() —— 检索栈中的最小元素。
}

class MinStack {
    //堆栈使用数组实现
    int[] stack;
    int rear;
    int size;
    int min;

    public MinStack() {
        stack = new int[10000];
        rear = 0;
        size = 0;
        min = 2147483647;
    }

    public void push(int val) {
        if (size != 0 && 0 == rear) {
            System.out.println("栈已满，无法加入！");
            return;
        }
        if (val < min) {
            min = val;
        }
        stack[rear++] = val;
        size++;
    }

    public void pop() {
        if (size == 0) {
            System.out.println("栈已空，无法删除栈中元素！");
            return;
        }
        rear--;
        size--;
        if (stack[rear] == min && size != 0) {
            min = stack[0];
            for (int i = 1; i < rear; i++) {
                if (stack[i] < min) {
                    min = stack[i];
                }
            }
        } else if (size == 0) {
            min = 2147483647;
        }
    }

    public int top() {
        if (size == 0) {
            System.out.println("栈已空，无法删除栈中元素！");
            return -1;
        }
        int res = stack[rear-1];
        return res;
    }

    public int getMin() {
        if (size == 0) {
            System.out.println("栈已空！");
            return -1;
        }
        return min;
    }
}
