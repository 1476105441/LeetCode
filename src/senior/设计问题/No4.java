package senior.设计问题;

import java.util.*;

public class No4 {
    /**
     * 数据流的中位数
     */

    //非常的慢，404ms
    /*class MedianFinder {
        int size;
        Node mid, front, rear;

        public MedianFinder() {
            size = 0;
            front = new Node(-1);
            rear = new Node(-1);
            front.next = rear;
            rear.pre = front;
        }

        public void addNum(int num) {
            size++;
            Node node = new Node(num);
            if (size == 1) {
                node.pre = front;
                node.next = rear;
                front.next = node;
                rear.pre = node;
                mid = node;
                return;
            }
            if (num > mid.val) {
                addFromRear(node);
                if ((size & 1) == 1)
                    mid = mid.next;
            } else if (num == mid.val) {
                addAfterMid(node);
                if ((size & 1) == 1)
                    mid = mid.next;
            } else {
                addFromHead(node);
                if ((size & 1) != 1)
                    mid = mid.pre;
            }
        }

        public void addAfterMid(Node node) {
            node.pre = mid;
            node.next = mid.next;
            mid.next.pre = node;
            mid.next = node;
        }

        public void addFromHead(Node node) {
            Node p = front.next;
            while (p.val < node.val) {
                p = p.next;
            }
            node.pre = p.pre;
            node.next = p;
            p.pre.next = node;
            p.pre = node;
        }

        public void addFromRear(Node node) {
            Node p = rear.pre;
            while (p.val > node.val) {
                p = p.pre;
            }
            node.pre = p;
            node.next = p.next;
            p.next.pre = node;
            p.next = node;
        }

        public double findMedian() {
            if((size&1) == 1)
                return mid.val;

            return (double)(mid.val + mid.next.val) / 2;
        }
    }

    class Node {
        Node pre;
        Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }*/

    //使用两个堆来实现
    class MedianFinder {
        int[] left,right;
        int ls,rs;
        public MedianFinder() {
            left = new int[100];
            right = new int[100];
            ls = 0;
            rs = 0;
        }

        public void addNum(int num) {
            if (ls == 0 && rs == 0) {
                right[rs] = num;
                rs++;
                return;
            }
            if (num > right[0]) {
                left[ls] = num;
                minUpdate(left,ls);
                ls++;
            }else{
                right[rs] = num;
                maxUpdate(right,rs);
                rs++;
            }
            check();
        }
        public void check(){
            //实现扩容，一定要注意不能判断为等于的时候扩容，因为数量可能发生跳变
            if (ls >= 0.7 * left.length) {
                left = Arrays.copyOf(left,2*left.length);
            }
            if (rs >= 0.7 * right.length) {
                right = Arrays.copyOf(right,2*right.length);
            }

            //判断两边是否保持平衡
            if (ls - 1 > rs) {
                int val = left[0];
                left[0] = left[ls-1];
                ls--;
                minShift(left,0,ls);
                right[rs] = val;
                maxUpdate(right,rs);
                rs++;
            } else if (rs - 1 > ls) {
                int val = right[0];
                right[0] = right[rs-1];
                rs--;
                maxShift(right,0,rs);
                left[ls] = val;
                minUpdate(left,ls);
                ls++;
            }
        }

        public double findMedian() {
            if (ls == rs)
                return (double) (left[0] + right[0]) / 2;
            else if(ls < rs)
                return right[0];
            else
                return left[0];
        }

        public void maxShift(int[] nums,int s,int n){
            int i = s,j = (i << 1)+1;
            while (j < n) {
                if(j < n-1 && nums[j] < nums[j+1])
                    j++;

                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i = j;
                    j = (i << 1) + 1;
                }else
                    break;
            }
        }
        public void minShift(int[] nums,int s,int n){
            int i = s,j = (i << 1)+1;
            while (j < n) {
                if(j < n-1 && nums[j] > nums[j+1])
                    j++;

                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i = j;
                    j = (i << 1) + 1;
                }else
                    break;
            }
        }
        public void maxUpdate(int[] nums,int s){
            int p = (s-1) >> 1;
            while (s != 0) {
                if (nums[s] > nums[p]) {
                    int temp = nums[s];
                    nums[s] = nums[p];
                    nums[p] = temp;
                    s = p;
                    p = (s-1)>>1;
                }else
                    break;
            }
        }
        public void minUpdate(int[] nums,int s){
            int p = (s-1) >> 1;
            while (s != 0) {
                if (nums[s] < nums[p]) {
                    int temp = nums[s];
                    nums[s] = nums[p];
                    nums[p] = temp;
                    s = p;
                    p = (s-1)>>1;
                }else
                    break;
            }
        }
    }
}
