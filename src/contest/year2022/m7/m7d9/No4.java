package contest.year2022.m7.m7d9;

import java.util.ArrayDeque;

public class No4 {
    /**
     *      元素值大于变化阈值的子数组
     */

    //大佬的代码，一时间难以理解
    /*int[] fa, sz;
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) fa[i] = i;
        sz = new int[n + 1];
        Arrays.fill(sz, 1);

        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, (i, j) -> nums[j] - nums[i]);
        for (Integer i : ids) {
            Integer j = find(i + 1);
            fa[i] = j; // 合并 i 和 i+1
            sz[j] += sz[i];
            if (nums[i] > threshold / (sz[j] - 1)) return sz[j] - 1;
        }
        return -1;
    }

    // 非递归并查集
    int find(int x) {
        Integer f = x;
        while (fa[f] != f) f = find(fa[f]);
        Integer tmp = x;
        while (fa[tmp] != f) {
            Integer t = tmp;
            tmp = fa[tmp];
            fa[t] = f;
        }
        return fa[x];
    }*/

    /**
     *         解法一：并查集
     * 为什么使用并查集？并查集有什么作用？
     *
     * 1、因为在这里我们需要从最大的元素开始，维护一个逐渐
     * 扩散的集合，为了标识哪些元素已经连接起来，所以要
     * 使用并查集
     * 2、并查集的作用是将元素划分为不同的集合，不同的元素
     * 可以属于同一个集合
     */
    //并查集的思想，将数组的下标进行从大到小的排序，然
    //后遍历这个下标数组，大的元素必然被先遍历到，每次
    //遍历时将当前节点和原顺序的下一个节点合并到一个集
    //合里，这样子，当遍历到它原顺序的下一个元素时，这
    //条链就维持起来了，而当前遍历到的元素一定是这条链
    //上最小的元素，只需要判断当前元素是否满足条件即可，
    //每次都将链条长度减一是因为链条长度中必定有一个是
    //多出来的
    /*int[] parent;
    int[] size;
    public int validSubarraySize(int[] nums, int threshold){
        int n = nums.length;
        //设置成n+1是为了方便处理边界
        parent = new int[n+1];
        size = new int[n+1];
        //存放下标的数组
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
            size[i] = 1;
            if (i < n) {
                ids[i] = i;
            }
        }

        //将ids元素按照对应的值的大小排序
        Arrays.sort(ids,(i,j)->nums[j]-nums[i]);
        for (Integer i : ids) {
            int j = find(i+1);
            parent[i] = j;
            size[j] += size[i];
            //减一是因为必然有一个元素是多出来的，是当前还没遍历到的
            if(nums[i] > threshold/(size[j]-1))
                return size[j]-1;
        }

        return -1;
    }
    public int find(int i){
        if (parent[i] != i) {
            return find(parent[i]);
        }
        return parent[i];
    }*/


    /**
     *         解法二：单调栈
     * 什么是单调栈？为什么使用单调栈？
     * 1、单调栈是一种元素单调递增或者单调递减的
     * 数据结构，底层是优先队列（不确定）。
     * 2、可以使用单调栈维护一些信息，比如存放递
     * 增的元素，可以统计出i元素之前比i小的元素
     * 有哪些，就可以计算出以i为最小元素的左边界，
     * 同理，可以计算出右边界，只要将遍历顺序反
     * 过来
     */

    //注意，要使用单调栈而不是单调队列
    //细节：由于不存在比它小的元素的时候，为整个序列的长
    //度，所以左边界不存在的时候为-1，右边界不存在的时候
    //为n
    /*int[] val;
    public int validSubarraySize(int[] nums, int threshold){
        int n = nums.length,size = 0;
        val = nums;
        int[] stack = new int[n],left = new int[n],right = new int[n];
        for (int i = 0; i < n; i++) {
            while (size > 0 && nums[stack[0]] >= nums[i]) {
                stack[0] = stack[size-1];
                size--;
                shift(stack,0,size);
            }
            left[i] = size == 0? -1 : stack[0];
            stack[size] = i;
            update(stack,size);
            size++;
        }

        size = 0;
        for (int i = n-1; i >= 0; i--) {
            while (size > 0 && nums[stack[0]] >= nums[i]) {
                stack[0] = stack[size-1];
                size--;
                shift(stack,0,size);
            }
            right[i] = size == 0? n : stack[0];
            stack[size] = i;
            update(stack,size);
            size++;
        }

        for (int i = 0; i < n; i++) {
            int k;
            k = right[i]-left[i]-1;
            if(nums[i] > threshold/k)
                return k;
        }
        return -1;
    }
    public void shift(int[] nums,int start,int n){
        int i = start,j = (i<<1)+1;
        while (j < n) {
            if (j < n - 1 && val[nums[j]] > val[nums[j + 1]]) {
                j++;
            }
            if (val[nums[i]] > val[nums[j]]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }
    public void update(int[] nums,int start){
        int i = start,p = (i-1)>>1;
        while (i > 0) {
            if (val[nums[p]] > val[nums[i]]) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }*/

    //真正的单调栈
    public int validSubarraySize(int[] nums, int threshold){
        int n = nums.length,size = 0;
        int[] left = new int[n],right = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1:stack.peek();
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for (int i = n-1; i > -1; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            int k = right[i] - left[i] -1;
            if(nums[i] > threshold/k)
                return k;
        }

        return -1;
    }
}
