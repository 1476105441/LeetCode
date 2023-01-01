package contest.year2022.m11.d6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No3 {
    //想法：链表+最小堆
    /*public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        long res = 0;
        Node root = new Node(costs[0],0),now = root,prefix,pn,suffix,sn; //prefix和suffix 分别代表前缀和后缀，pn是前缀的下一个元素，sn是后缀的下一个元素

        for (int i = 1; i < n; i++) {
            now.next = new Node(costs[i],i);
            now.next.pre = now;
            now = now.next;
        }
        prefix = root;
        suffix = now;
        int c = 1;
        Comparator<Node> comparator = (x,y)->{
            if(x.val != y.val)
                return x.val - y.val;
            return x.index - y.index;
        };
        PriorityQueue<Node> dump1 = new PriorityQueue<>(comparator),dump2 = new PriorityQueue<>(comparator);
        dump1.offer(prefix);
        dump2.offer(suffix);
        while (c < candidates && prefix.next != null) {
            prefix = prefix.next;
            suffix = suffix.pre;

            dump1.offer(prefix);
            dump2.offer(suffix);

            c++;
        }

        for (int i = 0; i < k; i++) {
            boolean choose1 = false,flag;
            Node peek1 = dump1.peek(),peek2 = dump2.peek();
            flag = peek1 == peek2;
            //System.out.println(flag);
            if (peek1.val == peek2.val) {
                if (peek1.index < peek2.index) {
                    choose1 = true;
                    res += peek1.val;
                } else {
                    res += peek2.val;
                }
            }else{
                if (peek1.val < peek2.val) {
                    choose1 = true;
                    res += peek1.val;
                } else {
                    res += peek2.val;
                }
            }

            if (choose1) {
                dump1.poll();
                if (prefix.next != null) {
                    dump1.offer(prefix.next);
                    prefix = prefix.next;
                }
                //断掉堆顶节点
                if (peek1.next != null) {
                    pn = peek1.next;
                    if(peek1.pre != null)
                        peek1.pre.next = pn;
                    pn.pre = peek1.pre;
                    peek1.next = null;
                    peek1.pre = null;
                    peek1 = pn;
                }else{
                    if(peek1.pre != null)
                        peek1 = peek1.pre;
                    peek1.next = null;
                }

            }else{
                dump2.poll();
                if(flag)
                    dump1.poll();
                if (suffix.pre != null) {
                    dump2.offer(suffix.pre);
                    suffix = suffix.pre;
                }
                //断掉堆顶节点
                if (peek2.next != null) {
                    pn = peek2.next;
                    if(peek2.pre != null)
                        peek2.pre.next = pn;
                    pn.pre = peek2.pre;
                    peek2.next = null;
                    peek2.pre = null;
                    peek2 = pn;
                }else{
                    if(peek2.pre != null)
                        peek2 = peek2.pre;
                    peek2.next = null;
                }

            }
        }

        return res;
    }
    class Node{
        Node next,pre;
        int val,index;

        public Node(int val,int index) {
            this.val = val;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        No3 no3 = new No3();
        no3.totalCost(new int[]{1,2,4,1},3,3);
    }*/


    //最小堆，思绪乱的一批，没成功
    /*public long totalCost(int[] costs, int k, int candidates){
        int n = costs.length;
        long res = 0;

        Node[] nodes = new Node[n];
        boolean[] set = new boolean[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(costs[i],i);
        }
        Comparator<Node> comparator = (x,y)->{
            if(x.val != y.val)
                return x.val - y.val;
            return x.index - y.index;
        };
        PriorityQueue<Node> dump1 = new PriorityQueue<>(comparator),dump2 = new PriorityQueue<>(comparator);

        int l = 0,r = n-1;
        while (l < candidates) {
            dump1.offer(nodes[l]);
            dump2.offer(nodes[r]);
            l++;
            r--;
        }

        for (int i = 0; i < k; i++) {
            Node peek1 = null,peek2 = null;
            while (!dump1.isEmpty() && set[(peek1 = dump1.peek()).index]) {
                dump1.poll();
            }
            while (!dump2.isEmpty() && set[(peek2 = dump2.peek()).index]) {
                dump2.poll();
            }
            boolean choose1 = false;
            if (peek1 == null) {
                res += peek2.val;
            }else if(peek2 == null){
                choose1 = true;
                res += peek1.val;
            }else {
                if (peek1.val == peek2.val) {
                    if (peek1.index < peek2.index) {
                        choose1 = true;
                        res += peek1.val;
                    } else {
                        res += peek2.val;
                    }
                }else{
                    if (peek1.val < peek2.val) {
                        choose1 = true;
                        res += peek1.val;
                    } else {
                        res += peek2.val;
                    }
                }
            }
            if (choose1) {
                dump1.poll();
                set[peek1.index] = true;
                while (l < n && set[l])
                    l++;
                if (l < n) {
                    dump1.offer(nodes[l]);
                    l++;
                }
            }else{
                dump2.poll();
                set[peek2.index] = true;
                while (r > -1 && set[r]) {
                    r--;
                }
                if (r > -1) {
                    dump2.offer(nodes[r]);
                    r--;
                }
            }
            System.out.println(res);
        }

        return res;
    }*/

    class Node{
        int val,index;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    //使用两个最小堆来存储范围内的元素
    public long totalCost(int[] costs, int k, int candidates){
        int n = costs.length;
        long res = 0;
        boolean[] set = new boolean[n];
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(costs[i],i);
        }

        //比较规则：值不相等时，选值较小的。值相等时，选下标较小的
        Comparator<Node> comparator = (x,y)->{
            if(x.val != y.val)
                return x.val - y.val;
            return x.index - y.index;
        };
        //左右各一个边
        PriorityQueue<Node> left = new PriorityQueue<>(comparator),right = new PriorityQueue<>(comparator);
        int l = 0,r = n-1;

        //要保证两个堆中的元素不重叠
        while (l < candidates && l <= r) {
            left.offer(nodes[l]);
            l++;
            if (r >= l) {
                right.offer(nodes[r]);
                r--;
            }
        }

        for (int i = 0; i < k; i++) {
            //从左右两个堆中选出值较小的元素
            Node leftNode = left.peek(),rightNode = right.peek();

            boolean flag = false; //flag 表示是否选择了右边的堆
            if (leftNode == null) {
                flag = true;
            } else if (rightNode != null) {
                if(compare(leftNode,rightNode) >= 0)
                    flag = true;
            }

            //分两种情况处理
            if (flag) {
                res += rightNode.val;
                //去掉一个元素，再加入一个元素
                right.poll();
                //保证两个堆中的元素不重叠
                if (r >= l) {
                    right.offer(nodes[r]);
                    r--;
                }
            } else {
                res += leftNode.val;
                left.poll();
                //保证两个堆中的元素不重叠
                if (l <= r) {
                    left.offer(nodes[l]);
                    l++;
                }
            }
        }

        return res;
    }
    public int compare(Node x,Node y){
        if(x.val != y.val)
            return x.val - y.val;
        return x.index - y.index;
    }
}
