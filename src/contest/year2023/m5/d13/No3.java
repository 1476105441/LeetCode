package contest.year2023.m5.d13;

import java.util.LinkedList;
import java.util.List;

/**
 * 2680.最大或值
 *
 * @author wjs 2023/8/6
 */
public class No3 {
    //假设一：选取移动的元素只在拥有最高位的元素中选择（显然的，选择的元素如果不是包含最高位的元素，收益不如拥有最高位的元素）
    //假设二：拥有最高位的元素如果不止一个，选择移动后重合元素最少的一个
    //假设三：拥有最高位的元素不止一个，并且移动后重合位数一样，选择未重合位最大的一个元素
    //失败
    /*public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        int[] globalBit = new int[32];
        for(int i=0;i < n;i++) {
            int val = nums[i];
            for(int j=0;j < 32 && val > 0;j++) {
                if((val & 1) == 1) {
                    globalBit[j]++;
                }
                val >>= 1;
            }
        }
        //maxBit记录最高位，max记录选定的元素的次高未重叠的位
        int maxBit = -1;
        List<Integer> bitList = new LinkedList<>();
        long res = 0;
        //loc表示选中移动的下标
        int loc = -1;
        for(int i=0;i < n;i++) {
            int val = nums[i],mb = -1;
            List<Integer> list = new LinkedList<>();
            for(int j=0;j < 32 && val > 0;j++) {
                if((val & 1) == 1) {
                    //记录没有重合的第二高位
                    if(mb != -1 && ((mb + k) >= 32 || globalBit[mb+k] == 0)) {
                        list.add(0,mb);
                    }
                    mb = j;
                }
                val >>= 1;
            }
            if(mb > maxBit) {
                loc = i;
                maxBit = mb;
                bitList = list;
            } else if(mb == maxBit) {
                if(compareList(list,bitList)) {
                    loc = i;
                    bitList = list;
                }
            }
        }
        res = (long)nums[loc] << k;
        for(int i=0;i < n;i++) {
            if(i != loc) {
                res |= nums[i];
            }
        }
        return res;
    }

    private boolean compareList(List<Integer> source, List<Integer> target) {
        int i = 0,m = source.size(),n = target.size();
        for(;i < m && i < n;i++) {
            if(source.get(i) != target.get(i)) {
                return source.get(i) > target.get(i);
            }
        }
        return i == n;
    }*/

    //失败，方向应该错了
    /*public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        int[] globalBit = new int[32];
        for(int i=0;i < n;i++) {
            int val = nums[i];
            for(int j=0;j < 32 && val > 0;j++) {
                if((val & 1) == 1) {
                    globalBit[j]++;
                }
                val >>= 1;
            }
        }
        //maxBit记录最高位，max记录选定的元素的次高未重叠的位
        int maxBit = -1,max = 0;
        List<Integer> bitList = new LinkedList<>();
        long res = 0;
        //loc表示选中移动的下标
        int loc = -1;
        for(int i=0;i < n;i++) {
            int val = nums[i],mb = -1;
            List<Integer> list = new LinkedList<>();
            for(int j=0;j < 32 && val > 0;j++) {
                if((val & 1) == 1) {
                    //记录没有重合的第二高位
                    if(mb != -1 && ((mb + k) >= 32 || globalBit[mb+k] == 0)) {
                        list.add(0,mb);
                    }
                    mb = j;
                }
                val >>= 1;
            }
            if(mb > maxBit) {
                loc = i;
                maxBit = mb;
                bitList = list;
                max = nums[i];
            } else if(mb == maxBit) {
                int r = compareList(list,bitList);
                if(r > 0) {
                    loc = i;
                    bitList = list;
                    max = nums[i];
                } else if(r == 0 && nums[i] > max) {
                    loc = i;
                    max = nums[i];
                }
            }
        }
        //System.out.println(loc);
        res = (long)nums[loc] << k;
        for(int i=0;i < n;i++) {
            if(i != loc) {
                res |= nums[i];
            }
        }
        return res;
    }

    private int compareList(List<Integer> source, List<Integer> target) {
        int i = 0,m = source.size(),n = target.size();
        for(;i < m && i < n;i++) {
            if(source.get(i) != target.get(i)) {
                return source.get(i) > target.get(i) ? 1 : -1;
            }
        }
        if(m == n) {
            return 0;
        } else {
            return i != m ? 1 : -1;
        }
    }*/

    //贪心+前后缀
    //成功，2ms
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        int[] prefix = new int[n], suffix = new int[n];
        int pre = 0, suf = 0;
        for(int i=0;i < n;i++) {
            prefix[i] = pre;
            suffix[n-1-i] = suf;
            pre |= nums[i];
            suf |= nums[n-1-i];
        }
        for(int i=0;i < n;i++) {
            long tmp = prefix[i] | suffix[i];
            tmp |= (long) nums[i] << k;
            res = Math.max(tmp,res);
        }
        return res;
    }
}
