package contest.year2023.m7.d2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2763.所有子数组中不平衡数字之和
 *
 * @author wjs 2023/7/3
 */
public class No4 {
    //排序并且去重
    //失败，没有考虑到全部的情况
    /*public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int res = 0, cnt = 0;
        List<Integer> tmp = new ArrayList<>(1024);
        for(int i=0;i < n;i++) {
            int loc = findLoc(tmp,cnt,nums[i]);
            //System.out.println(loc);
            if(loc < cnt-1) {
                res += cnt-loc-1;
                if(tmp.get(loc+1)-1 == nums[i]) {
                    res--;
                }
            }
            if(loc != -1 && tmp.get(loc) == nums[i]) {
                if(loc > 0) {
                    res += loc;
                    if(tmp.get(loc-1) == tmp.get(loc)-1) {
                        res--;
                    }
                }
            } else {
                if(loc != -1) {
                    res += loc;
                    if(tmp.get(loc)+1 != nums[i]) {
                        res++;
                    }
                }
                tmp.add(loc+1,nums[i]);
                cnt++;
            }
        }
        return res;
    }

    //找到小于元素的第一个元素下标
    private int findLoc(List<Integer> tmp, int end, int val) {
        int l = 0, r = end-1;
        int res = -1;
        while(l <= r) {
            int c = l + ((r-l) >> 1);
            if(tmp.get(c) > val) {
                r = c-1;
            } else if(tmp.get(c) < val) {
                l = c+1;
                res = c;
            } else {
                res = c;
                break;
            }
        }
        return res;
    }*/

    //解法一：暴力，9ms
    /*public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int res = 0;
        for(int i=0;i < n;i++) {
            boolean[] set = new boolean[1002];
            set[nums[i]] = true;
            int cnt = 0;
            for(int j=i+1;j < n;j++) {
                if(!set[nums[j]]) {
                    boolean f1 = set[nums[j]-1];
                    boolean f2 = set[nums[j]+1];
                    if(!f1 && !f2) {
                        cnt++;
                    } else if(f1 && f2) {
                        cnt--;
                    }
                    set[nums[j]] = true;
                }
                res += cnt;
            }
        }
        return res;
    }*/

    //重写解法一，思考为何使用这种写法
    //其实就是类似于一个滑动窗口，遍历每个可能的子
    //数组，计算子数组中满足条件的数量，每个子循环
    //中通过固定左边界，移动右边界的形式增量式的计
    //算每个子数组中满足下标的数量
    /*public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int res = 0;
        for(int i=0;i < n;i++) {
            boolean[] set = new boolean[n+2];
            set[nums[i]] = true;
            int cnt = 0;
            for(int j=i+1;j < n;j++) {
                if(!set[nums[j]]) {
                    set[nums[j]] = true;
                    boolean f1 = set[nums[j]-1], f2 = set[nums[j]+1];
                    if(f1 && f2) {
                        cnt--;
                    } if(!f1 && !f2) {
                        cnt++;
                    }
                }
                res += cnt;
            }
        }
        return res;
    }*/

    //时隔多天再次重写解法一
    /*public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int res = 0;
        for(int i=0;i < n;i++) {
            boolean[] set = new boolean[1002];
            set[nums[i]] = true;
            int cnt = 0;
            for(int j=i+1;j < n;j++) {
                if(!set[nums[j]]) {
                    set[nums[j]] = true;
                    boolean f1 = set[nums[j]-1], f2 = set[nums[j]+1];
                    if(f1 && f2) {
                        cnt--;
                    } else if(!f1 && !f2) {
                        cnt++;
                    }
                }
                res += cnt;
            }
        }
        return res;
    }*/

    //时隔多天总算看懂了解法二
    //解法二：贡献法
    //对于一个元素，只计算没有比它更小的元素的子数组的个数作为贡献（将整个模型简单化）。
    //而整个问题又可以使用乘法原理进行简化，只计算包含当前元素的左右端点，而左右端点能够
    //唯一确定一个子数组
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        int[] idx = new int[n+1];
        int res = 0;
        Arrays.fill(idx,n);
        for(int i=n-1;i >= 0;i--) {
            int x = nums[i];
            //为什么右端点要把相同的值也排除掉？
            //看[1,3,3,3,5]的例子就能够明白，去掉右端点是因为如果不去掉，后续在遍历
            //到相同值的时候，就会产生重复的子数组，因为在计算左端点的时候没有排除相
            //同值的元素
            right[i] = Math.min(idx[x],idx[x-1]);
            idx[x] = i;
        }
        Arrays.fill(idx,-1);
        for(int i=0;i < n;i++) {
            int x = nums[i];
            //左端点的计算没有排除相同元素
            res += (i-idx[x-1]) * (right[i]-i);
            idx[x] = i;
        }
        return res - n*(n+1)/2;
    }
}
