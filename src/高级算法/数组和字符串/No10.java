package 高级算法.数组和字符串;

import java.util.*;

public class No10 {
    /**
     *          滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     */

    /**
     * 离谱中的离谱，2000多毫秒
     */
    /*public int[] maxSlidingWindow(int[] nums,int k){
        int left = 0,right = k-1,n = nums.length,loc = -1,max = -2147483648;
        int[] res;
        if (k > n) {
            res = new int[1];
            for (int i = 0; i < n; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            res[0] = max;
        }else{
            res = new int[n - k + 1];
        }

        while (right < n) {
            if (loc < left) {
                max = -2147483648;
                for (int i = left; i <= right; i++) {
                    if (nums[i] > max) {
                        max = nums[i];
                        loc = i;
                    }
                }
            }else if(nums[right] > max){
                max = nums[right];
                loc = right;
            }

            res[left] = max;
            right++;
            left++;
        }

        return res;
    }*/


    /**
     * 使用最大堆的方法来寻找每次滑动窗口的最大值，其中存储于堆中的
     * 元素是数组中元素的值和其下标，通过下标来判断该值是否还在滑动
     * 窗口中
     * 自己写的失败，超时，数组在使用时要注意指针指向的问题
     */
    /*public int[] maxSlidingWindow(int[] nums,int k){
        int left = 0,right = k - 1,n = nums.length,length = k;
        int[][] temp = new int[n][2];
        int[] res = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            temp[i][0] = nums[i];
            temp[i][1] = i;
        }
        merge(temp,length);

        while(right < n){
            while(temp[0][1] < left){
                //移除顶部的元素
                temp[0][0] = temp[length-1][0];
                temp[0][1] = temp[length-1][1];
                length--;
                shift(temp,length,0);
            }
            res[left] = temp[0][0];

            left++;
            right++;
            if(right < n){
                //将新元素加入堆中
                temp[length][0] = nums[right];
                temp[length][1] = right;
                length++;
                merge(temp,length);
            }
        }

        return res;
    }*/
    //堆排序的调整函数
    /*public void shift(int[][] nums,int n,int loc){
        int i = loc,j = 2 * i + 1;
        int[] temp;
        while(j < n){
            if (j + 1 < n && nums[j + 1][0] > nums[j][0]) {
                j++;
            }
            if (nums[i][0] < nums[j][0]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2*i + 1;
            }else{
                break;
            }
        }
    }*/
    //堆排序，使整个堆变成最大堆
    /*public void merge(int[][] nums,int n){
        for (int i = (n-2)/2; i >= 0; i--) {
            shift(nums,n,i);
        }
    }*/


    /**
     *      解法二：使用双向队列
     *   思想：使用队列来存储元素的下标，但是
     * 存储的条件是只存储在其后没有元素值比它
     * 大的元素下标，这是因为我们要寻找每次滑
     * 动窗口的最大值，如果一个元素在滑动窗口
     * 内，其后面还有元素比它大，那么这个元素
     * 一定不会加入到最大值中，可以直接去除掉，
     * 永无出头之日，而由于队列中的元素会在其
     * 后面的元素之前掉出滑动窗口，所以其后面
     * 的比它小的元素也可以加入到队列中（比它
     * 大的话，当前元素就会被排出队列了）
     */

    /*public int[] maxSlidingWindow(int[] nums, int k){
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!list.isEmpty() && nums[i] >= nums[list.getLast()]) {
                list.pollLast();
            }
            list.offerLast(i);
        }
        res[0] = nums[list.getFirst()];

        for (int i = k; i < n; i++) {
            while (!list.isEmpty() && nums[i] >= nums[list.getLast()]) {
                list.pollLast();
            }
            list.offerLast(i);
            if (list.getFirst() < i - k + 1) {
                list.pollFirst();
            }
            res[i - k + 1] = nums[list.getFirst()];
        }

        return res;
    }*/

    /**
     *          解法三：分块+预处理（动态规划）
     * 思想：
     *   将数组划分为一个个长度为k的区间进行处理，在每个
     * 区间中寻找最大值，对于当前遍历到的滑动窗口[i,i+k-1]
     * 如果i是k的倍数，则刚好处在一个区间内，最大值就是这
     * 个区间的最大值，如果i不是k的倍数，则滑动窗口跨越两
     * 个区间，前半段是前一个区间的后缀，后半段是后一个区
     * 间的前缀，可以通过动态规划来确定前缀和后缀中的最大
     * 值，prefixMax数组存放当前下标为i的元素为结尾的前
     * 缀的最大值，suffixMax数组存放当前下标为i的元素为
     * 开始的前缀的最大值
     *
     * 则两个数组的结果满足表达式：
     *                 nums[i]                      当i为k的倍数时（也就是说i是一个区间的第一个元素，以它为结尾的前缀就只有它自己）
     *  prefixMax[i] =
     *                 Max{prefixMax[i-1],nums[i]}   i不是k的倍数时
     *
     *                 nums[i]                     当i+1是k的倍数时
     *  suffixMax[i] =
     *                 Max{suffix[i+1],nums[i]}    当i+1不是k的倍数时
     *                 注意：处理边界n-1时要单独处理，因为n-1是数组中的最大下标，没有下标为n的元素
     */

    public int[] maxSlidingWindow(int[] nums,int k){
        int n = nums.length,j;
        int[] prefixMax = new int[n],suffixMax = new int[n],res = new int[n-k+1];

        for (int i = 0; i < n; i++) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }else{
                prefixMax[i] = Math.max(nums[i],prefixMax[i-1]);
            }

            j = n-1-i;
            if ((j + 1) % k == 0 || j == n-1) {
                suffixMax[j] = nums[j];
            }else{
                suffixMax[j] = Math.max(suffixMax[j+1],nums[j]);
            }
        }

        for (int i = k-1; i < n; i++) {
            res[i-k+1] = Math.max(suffixMax[i-k+1],prefixMax[i]);
        }

        return res;
    }
}




























