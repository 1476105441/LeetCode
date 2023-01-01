package review.数组;

public class No27 {
    /**
     *      滑动窗口最大值
     *  给你一个整数数组nums，有一个大小为k的滑动窗口从数组的最左侧
     *  移动到数组的最右侧。你只可以看到在滑动窗口内的k个数字。滑动窗
     *  口每次只向右移动一位。
     */

    //使用优先队列，先不急着把不在窗口内的元素移出队列，因为那样太耗时
    //每次调整优先队列，如果队列头已经不在窗口中，则移除即可，这样就不
    //会很耗时
    //*********************超时*****************
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length,left = 0,right = k-1,length = k;
        int[][] temp = new int[n][2];
        int[] res = new int[n-k+1];

        //先调整第一个滑动窗口的值
        for (int i = 0; i < k; i++) {
            temp[i][0] = nums[i];
            temp[i][1] = i;
        }
        merge(temp,k);
        res[0] = temp[0][0];
        right++;
        left++;
        while (right < n) {
            temp[length][0] = nums[right];
            temp[length][1] = right;
            length++;
            merge(temp,length);
            while (temp[0][1] < left) {
                temp[0][0] = temp[length-1][0];
                temp[0][1] = temp[length-1][1];
                length--;
                shift(temp,length,0);
            }
            res[right-k+1] = temp[0][0];
            right++;
            left++;
        }

        return res;
    }

    public void shift(int[][] nums,int n,int begin){
        int i = begin ,j = 2 * i + 1;
        while (j < n) {
            if (j < n - 1 && nums[j + 1][0] > nums[j][0]) {
                j++;
            }
            if (nums[i][0] < nums[j][0]) {
                int[] temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else
                break;
        }
    }

    public void merge(int[][] nums,int n){
        int i = (n-2)/2;
        for (; i >= 0; i--) {
            shift(nums,n,i);
        }
    }*/

    //双向队列，观察滑动窗口的特点，窗口是向后滑动的，所以后面的进来，
    //前面的出去，同时注意观察，如果队列中一个元素的后面有一个比它大的
    //数，那么它可以直接排出队列了，因为它肯定不会是当前队列中最大的数
    //它比后面的元素先出队列
    /*public int[] maxSlidingWindow(int[] nums,int k){
        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        int right = k;

        //初始化单调队列
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.getLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        res[0] = nums[queue.getFirst()];

        while(right < nums.length) {
            while (!queue.isEmpty() && nums[right] >= nums[queue.getLast()]) {
                queue.pollLast();
            }
            queue.offerLast(right);
            if (queue.getFirst() < right - k + 1) {
                queue.pollFirst();
            }
            res[right-k+1] = nums[queue.getFirst()];
            right++;
        }

        return res;
    }*/

    //第三种解法：分区+预处理（动态规划）
    //将数组进行分块，每k个为一块，最后一个块可能不满k个
    /*public int[] maxSlidingWindow(int[] nums,int k){
        int[] prefix = new int[nums.length],suffix = new int[nums.length];
        int sufIndex;
        int[] res = new int[nums.length-k+1];

        for (int i = 0; i < nums.length; i++) {
            if (i % k == 0) {
                prefix[i] = nums[i];
            }else
                prefix[i] = Math.max(prefix[i-1],nums[i]);

            sufIndex = nums.length-1-i;
            if ((sufIndex+1) % k == 0 || sufIndex == nums.length-1) {
                suffix[sufIndex] = nums[sufIndex];
            }else
                suffix[sufIndex] = Math.max(suffix[sufIndex+1],nums[sufIndex]);
        }

        for (int i = k-1; i < nums.length; i++) {
            int start = i-k+1;
            res[start] = Math.max(suffix[start],prefix[i]);
        }

        return res;
    }*/

    //第三题的思想：将每k个元素划分为一组，最后一组可能不足k个，不过没有关系
    //然后在窗口滑动时，如果起始位置是k的倍数，则是一个组的起始位置，当前滑动
    //窗口刚好与一组重合，若是提前计算好一组的最大值，则可以直接得出结果，如果
    //起始位置不是k的倍数，则当前滑动窗口必然会跨过两个


    //看了源码之后写第一种解法：
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length,left = 0,right = k-1,length = k;
        int[][] temp = new int[n][2];
        int[] res = new int[n-k+1];

        //先调整第一个滑动窗口的值
        for (int i = 0; i < k; i++) {
            temp[i][0] = nums[i];
            temp[i][1] = i;
        }
        merge(temp,k);
        res[0] = temp[0][0];
        right++;
        left++;
        while (right < n) {
            temp[length][0] = nums[right];
            temp[length][1] = right;
            shiftUp(temp,length);
            length++;
            while (temp[0][1] < left) {
                temp[0][0] = temp[length-1][0];
                temp[0][1] = temp[length-1][1];
                length--;
                shiftDown(temp,length);
            }
            res[right-k+1] = temp[0][0];
            right++;
            left++;
        }

        return res;
    }

    //移除堆顶元素调用的调整函数
    public void shiftDown(int[][] nums,int n){
        //half是第一个叶子结点的下标，注意n是移除堆顶之后的长度
        int half = n >>> 1,i = 0;
        int[] temp = nums[i];

        while (i < half) {
            int j = (i << 1) + 1;
            if (j + 1 < n && nums[j][0] < nums[j + 1][0]) {
                j++;
            }
            if (temp[0] > nums[j][0]) {
                break;
            }
            nums[i] = nums[j];
            i = j;
        }

        nums[i] = temp;
    }

    //加入新元素调用的调整函数，n是加入之前的长度
    public void shiftUp(int[][] nums,int n){
        int[] temp = nums[n];
        while (n > 0) {
            int parent = (n-1)>>>1;
            if (temp[0] < nums[parent][0]) {
                break;
            }
            nums[n] = nums[parent];
            n = parent;
        }
        nums[n] = temp;
    }

    public void shift(int[][] nums,int n,int begin){
        int i = begin ,j = 2 * i + 1;
        while (j < n) {
            if (j < n - 1 && nums[j + 1][0] > nums[j][0]) {
                j++;
            }
            if (nums[i][0] < nums[j][0]) {
                int[] temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else
                break;
        }
    }

    public void merge(int[][] nums,int n){
        int i = (n-2)/2;
        for (; i >= 0; i--) {
            shift(nums,n,i);
        }
    }*/
}
