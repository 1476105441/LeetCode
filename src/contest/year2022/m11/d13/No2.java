package contest.year2022.m11.d13;

public class No2 {
    /*public int subarrayLCM(int[] nums, int k) {
        int n = nums.length,res = 0,l = 0,r = 0,flag = 0;
        int[] set = new int[10000];

        while (r < n) {
            if (k % nums[r] == 0) {
                if (k == nums[r]) {
                    flag++;
                    res++;
                }
                //有可以相乘的数存在
                if (set[k / nums[r]] > 0) {
                    flag++;
                    res++;
                }else if(flag > 0 && l + 1 <= r)
                    res++;
                set[nums[r]]++;
            }else{
                while (l < r) {
                    if(set[k/nums[l]] > 0 || k == nums[l])
                        flag--;
                    set[nums[l]]--;
                    l++;
                    if(flag > 0 && l + 1 < r)
                        res++;
                }
                flag = 0;
                l = r+1;
            }
            r++;
        }
        while (l < r) {
            if(set[k/nums[l]] > 0 || k == nums[l])
                flag--;
            set[nums[l]]--;
            l++;
            if(flag > 0 && l + 1 < r)
                res++;
        }

        return res;
    }*/

    /*public int subarrayLCM(int[] nums, int k){
        int n = nums.length,res = 0,l = 0,r = 0,flag = 0;
        int[] set = new int[10000];

        while (r < n) {
            if (k % nums[r] == 0) {
                if (k == nums[r]) {
                    flag++;
                    res++;
                }
                //有可以相乘的数存在
                int loc = k / nums[r];
                if (set[loc] > 0) {
                    flag++;
                    res += set[loc];
                }else if(flag > 0 && l + 1 <= r)
                    res++;
                set[nums[r]]++;
            }else{
                int loc = k/nums[l];
                if(set[loc] > 0 || k == nums[l])
                    flag--;
                set[nums[l]]--;
                l++;
                while (l < r) {
                    if(flag > 0 && nums[r-1] != loc)
                        res++;
                    loc = k/nums[l];
                    if(set[loc] > 0 || k == nums[l])
                        flag--;
                    set[nums[l]]--;
                    l++;
                }
                flag = 0;
                l = r+1;
            }
            r++;
        }
        int loc = k/nums[l];
        if(set[loc] > 0 || k == nums[l])
            flag--;
        set[nums[l]]--;
        l++;
        while (l < r) {
            if(flag > 0 && nums[r-1] != loc)
                res++;
            loc = k/nums[l];
            if(set[loc] > 0 || k == nums[l])
                flag--;
            set[nums[l]]--;
            l++;
        }

        return res;
    }*/

    //暴力解法，草，暴力就能解
    public int subarrayLCM(int[] nums, int k){
        int n = nums.length,res = 0;

        for (int i = 0; i < n; i++) {
            if (k % nums[i] == 0) {
                boolean flag = false;
                if (nums[i] == k) {
                    flag = true;
                    res++;
                }
                for (int j = i+1; j < n; j++) {
                    if (k % nums[j] == 0) {
                        if(nums[j] * nums[i] == k || nums[j] == k)
                            flag = true;
                    }else
                        break;
                    if(flag)
                        res++;
                }
            }
        }

        return res;
    }
}
