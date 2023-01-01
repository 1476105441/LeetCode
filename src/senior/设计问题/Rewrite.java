package senior.设计问题;

import java.util.*;

public class Rewrite {

}

class Trie {
    Node root;

    class Node {
        Node[] next;
        boolean isEnd;

        public Node() {
            next = new Node[26];
        }
    }

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        char[] c = word.toCharArray();
        Node now = root;
        for (int i = 0; i < c.length; i++) {
            int loc = c[i] - 'a';
            if (now.next[loc] == null)
                now.next[loc] = new Node();
            now = now.next[loc];
        }
        now.isEnd = true;
    }

    public boolean search(String word) {
        char[] c = word.toCharArray();
        Node now = root;
        for (int i = 0; i < c.length; i++) {
            int loc = c[i] - 'a';
            if (now.next[loc] == null)
                return false;
            now = now.next[loc];
        }

        return now.isEnd;
    }

    public boolean startsWith(String prefix) {
        char[] c = prefix.toCharArray();
        Node now = root;
        for (int i = 0; i < c.length; i++) {
            int loc = c[i] - 'a';
            if (now.next[loc] == null)
                return false;
            now = now.next[loc];
        }

        return true;
    }
}

class LRUCache {
    Map<Integer, Node> map;
    int cap, size;
    Node head, tail;

    class Node {
        Node pre;
        Node next;
        int key, val;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        cap = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node res = map.get(key);
        if (res == null)
            return -1;
        Node pre = res.pre, next = res.next;
        pre.next = next;
        next.pre = pre;
        head.next.pre = res;
        res.next = head.next;
        res.pre = head;
        head.next = res;
        return res.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node();
            node.key = key;
            node.val = value;
            if (size == cap) {
                Node del = tail.pre;
                map.remove(del.key);
                del.pre.next = tail;
                tail.pre = del.pre;
                del.pre = null;
                del.next = null;
            } else
                size++;
            map.put(key, node);
        } else {
            node.val = value;
            Node pre = node.pre, next = node.next;
            pre.next = next;
            next.pre = pre;
        }
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
    }
}


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    Stack<Node> stack;
    Node now;

    class Node{
        List<NestedInteger> list;
        int index;
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        now = new Node();
        now.list = nestedList;
        stack = new Stack<>();
    }

    @Override
    public Integer next() {
        NestedInteger nestedInteger = now.list.get(now.index);
        now.index++;

        return nestedInteger.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && now.list.size() == now.index) {
            now = stack.pop();
        }
        if(now.list.size() == now.index)
            return false;

        NestedInteger nestedInteger = now.list.get(now.index);
        while (!nestedInteger.isInteger()) {
            now.index++;
            if (nestedInteger.getList().size() != 0) {
                stack.push(now);
                Node next = new Node();
                next.list = nestedInteger.getList();
                now = next;
            } else {
                while (!stack.isEmpty() && now.list.size() == now.index) {
                    now = stack.pop();
                }
                if(now.list.size() == now.index)
                    return false;
            }
            nestedInteger = now.list.get(now.index);
        }

        return true;
    }
}



//屎山代码，超时了
/*class MedianFinder {
    Node head,tail,mid;
    int c;

    class Node{
        Node pre,next;
        int val;
    }

    public MedianFinder() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public void addNum(int num) {
        Node node = new Node();
        node.val = num;
        if (mid == null) {
            node.pre = head;
            node.next = tail;
            head.next = node;
            tail.pre = node;
            mid = node;
        } else {
            //奇偶数分开判断
            if ((c & 1) == 1) {
                if (mid.val <= num) {
                    Node now;
                    for (now = mid.next; now != tail; now = now.next) {
                        if (now.val >= num) {
                            insertBefore(node,now);
                            break;
                        }
                    }

                    if (now == tail) {
                        insertBefore(node,now);
                    }
                } else {
                    Node now;
                    for (now = mid.pre; now != head; now = now.pre) {
                        if (now.val <= num) {
                            insertAfter(node,now);
                            break;
                        }
                    }

                    if (now == head) {
                        insertAfter(node,now);
                    }
                    mid = mid.pre;
                }
            } else {
                if (mid.val >= num) {
                    Node now;
                    for (now = mid.pre; now != head; now = now.pre) {
                        if (now.val <= num) {
                            insertAfter(node,now);
                            break;
                        }
                    }
                    if(now == head)
                        insertAfter(node,head);
                } else{
                    Node now;
                    for (now = mid.next; now != tail; now = now.next) {
                        if (now.val >= num) {
                            insertBefore(node,now);
                            break;
                        }
                    }
                    mid = mid.next;
                    if(now == tail)
                        insertBefore(node,now);
                }
            }
        }

        c++;
    }

    public double findMedian() {
        if((c & 1) == 1)
            return mid.val;

        return ((double)mid.val + mid.next.val) / 2;
    }

    private void insertBefore(Node node, Node now) {
        node.next = now;
        node.pre = now.pre;
        now.pre.next = node;
        now.pre = node;
    }
    private void insertAfter(Node node,Node now){
        node.next = now.next;
        now.next.pre = node;
        now.next = node;
        node.pre = now;
    }
}*/


//果然，换个搜索方向就能过，400多ms
/*
class MedianFinder {
    Node head,tail,mid;
    int c;

    class Node{
        Node pre,next;
        int val;
    }

    public MedianFinder() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public void addNum(int num) {
        Node node = new Node();
        node.val = num;
        if (mid == null) {
            node.pre = head;
            node.next = tail;
            head.next = node;
            tail.pre = node;
            mid = node;
        } else {
            //奇偶数分开判断
            if (mid.val <= num) {
                addTail(node);
                if((c & 1) == 0)
                    mid = mid.next;
            } else {
                addHead(node);
                if((c&1) == 1)
                    mid = mid.pre;
            }
        }

        c++;
    }

    public double findMedian() {
        if((c & 1) == 1)
            return mid.val;

        return ((double)mid.val + mid.next.val) / 2;
    }

    private void insertBefore(Node node, Node now) {
        node.next = now;
        node.pre = now.pre;
        now.pre.next = node;
        now.pre = node;
    }
    private void insertAfter(Node node,Node now){
        node.next = now.next;
        now.next.pre = node;
        now.next = node;
        node.pre = now;
    }
    private void addHead(Node node){
        Node now = head.next;
        while (now.val < node.val) {
            now = now.next;
        }
        insertBefore(node,now);
    }
    private void addTail(Node node){
        Node now = tail.pre;
        while (now.val > node.val) {
            now = now.pre;
        }
        insertAfter(node,now);
    }
}*/


//双堆解法，一个大堆，一个小堆
//完美解决：77ms
/*class MedianFinder {
    int[] maxDump,minDump;
    //分别为：总数量、中位数、maxDump的长度，minDump的长度
    int count,mxl,mnl;
    double mid;
    public MedianFinder() {
        maxDump = new int[128];
        minDump = new int[128];
    }

    public void addNum(int num) {
        //添加到堆中
        if (count == 0) {
            minDump[mnl++] = num;
            mid = num;
        } else if(mid > num) {
            maxDump[mxl++] = num;
            maxUpdate();
        } else{
            minDump[mnl++] = num;
            minUpdate();
        }
        count++;

        //调整为相差不多的状态
        if (Math.abs(mxl - mnl) > 1) {
            if (mxl > mnl) {
                int temp = maxDump[0];
                maxDump[0] = maxDump[mxl-1];
                mxl--;
                maxShift(0);
                minDump[mnl] = temp;
                mnl++;
                minUpdate();
            } else {
                int temp = minDump[0];
                maxDump[mxl] = temp;
                mxl++;
                maxUpdate();
                minDump[0] = minDump[mnl-1];
                mnl--;
                minShift(0);
            }
        }

        //实现扩容机制
        if (mxl >= maxDump.length * 0.75) {
            maxDump = expand(maxDump,mxl);
        }
        if (mnl >= minDump.length * 0.75) {
            minDump = expand(minDump,mnl);
        }

        //计算mid
        if ((count & 1) == 0) {
            mid = ((double)maxDump[0] + (double) minDump[0]) / 2;
        } else
            mid = mxl > mnl ? maxDump[0] : minDump[0];
    }

    public double findMedian() {
        return mid;
    }

    private int[] expand(int[] nums,int l){
        int nl = nums.length << 1;
        int[] temp = new int[nl];

        for (int i = 0; i < l; i++) {
            temp[i] = nums[i];
        }

        return temp;
    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void maxShift(int loc){
        int i = loc,j = (i<<1)+1;
        while (j < mxl) {
            if (j + 1 < mxl && maxDump[j] < maxDump[j + 1])
                j++;
            if (maxDump[i] < maxDump[j]) {
                swap(maxDump,i,j);
                i = j;
                j = (i << 1) + 1;
            } else
                break;
        }
    }

    private void maxUpdate() {
        int i = mxl-1,p = (i-1) >> 1;
        while (i > 0) {
            if (maxDump[i] > maxDump[p]) {
                swap(maxDump,i,p);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }
    private void minUpdate(){
        int i = mnl-1,p = (i-1) >> 1;
        while (i > 0) {
            if (minDump[i] < minDump[p]) {
                swap(minDump,i,p);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }
    private void minShift(int loc){
        int i = loc,j = (i<<1) + 1;
        while (j < mnl) {
            if(j+1 < mnl && minDump[j] > minDump[j+1])
                j++;
            if (minDump[i] > minDump[j]) {
                swap(minDump,i,j);
                i = j;
                j = (i<<1) + 1;
            }else
                break;
        }
    }
}*/

//重新写一遍，数据流的中位数，成功，一次过
class MedianFinder {
    int l1,l2,size;
    int[] maxDump,minDump;
    double mid;

    public MedianFinder() {
        maxDump = new int[128];
        minDump = new int[128];
    }

    public void addNum(int num) {
        //先将数据添加进堆中
        if (size++ == 0) {
            maxDump[l1++] = num;
        }else{
            //分两路存放，大于中位数时存到右边的小堆中
            if (num > mid) {
                minDump[l2] = num;
                minUpdate(l2++);
            }
            //小于等于中位数时存到左边的大堆中
            else{
                maxDump[l1] = num;
                maxUpdate(l1++);
            }
        }
        //检查是否需要重新平衡
        if (Math.abs(l1 - l2) > 1) {
            //左边的堆数量过多，移除堆顶，加入小堆中
            if (l1 > l2) {
                int temp = maxDump[0];
                maxDump[0] = maxDump[--l1];
                maxShift(0,l1);
                minDump[l2] = temp;
                minUpdate(l2++);
            }
            //右边的堆数量多
            else{
                int temp = minDump[0];
                minDump[0] = minDump[--l2];
                minShift(0,l2);
                maxDump[l1] = temp;
                maxUpdate(l1++);
            }
        }
        //重新计算mid的值
        if ((size & 1) == 0) {
            mid = (double)(maxDump[0] + minDump[0]) / 2;
        }else{
            mid = l1 > l2 ? maxDump[0] : minDump[0];
        }
        //检查是否需要扩容
        if (l1 >= maxDump.length * 0.75) {
            maxDump = Arrays.copyOf(maxDump,maxDump.length<<1);
        }
        if (l2 >= minDump.length * 0.75) {
            minDump = Arrays.copyOf(minDump,minDump.length<<1);
        }
    }

    public double findMedian() {
        return mid;
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void maxShift(int loc,int n){
        int i = loc,j = (i<<1)+1;
        while (j < n) {
            if(j < n-1 && maxDump[j] < maxDump[j+1])
                j++;

            if (maxDump[i] < maxDump[j]) {
                swap(maxDump,i,j);
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public void maxUpdate(int loc){
        int i = loc,p = (i-1)>>1;
        while (i > 0) {
            if (maxDump[i] > maxDump[p]) {
                swap(maxDump,i,p);

                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }
    public void minShift(int loc,int n){
        int i = loc,j = (i<<1)+1;
        while (j < n) {
            if(j < n-1 && minDump[j] > minDump[j+1])
                j++;

            if (minDump[i] > minDump[j]) {
                swap(minDump,i,j);
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public void minUpdate(int loc){
        int i = loc,p = (i-1)>>1;
        while (i > 0) {
            if (minDump[i] < minDump[p]) {
                swap(minDump,i,p);

                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }
}


