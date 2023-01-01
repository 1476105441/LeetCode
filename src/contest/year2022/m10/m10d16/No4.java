package contest.year2022.m10.m10d16;

public class No4 {
    /*public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length,l = 0,r = 0,res = 0;
        int[] bucket = new int[1000001];

        while (r < n) {
            if (nums[r] < minK || nums[r] > maxK) {
                while (l < r) {
                    bucket[nums[l]]--;
                    if (bucket[minK] > 0 && bucket[maxK] > 0) {
                        res++;
                        l++;
                    }else{
                        l = r;
                    }
                }
                l++;
            }else{
                bucket[nums[r]]++;
                if (bucket[minK] > 0 && bucket[maxK] > 0) {
                    res++;
                }
            }
            r++;
        }

        while (l < r) {
            bucket[nums[l]]--;
            if (bucket[minK] > 0 && bucket[maxK] > 0) {
                res++;
                l++;
            }else{
                l = r;
            }
        }

        return res;
    }*/

    //失败，没整出来
    /*public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length,l = 0,r = 0,res = 0;
        int[] bucket = new int[1000001];

        while (r < n) {
            if (nums[r] < minK || nums[r] > maxK) {

                while (l < r) {
                    bucket[nums[l]]--;
                    if (bucket[minK] > 0 && bucket[maxK] > 0) {
                        res++;
                        l++;
                    }else{
                        l = r;
                    }
                }
                l++;
            }else{
                bucket[nums[r]]++;
                if (bucket[minK] > 0 && bucket[maxK] > 0) {
                    res++;
                }
            }
            r++;
        }

        while (l < r) {
            bucket[nums[l]]--;
            if (bucket[minK] > 0 && bucket[maxK] > 0) {
                res++;
                l++;
            }else{
                l = r;
            }
        }

        return res;
    }*/


    //转换思路，只需要考虑子数组的右端点，将最近出现的minK和maxK的下标
    //记录下来，如果两个下标都存在，则说明在右端点之前有出现符合条件的元
    //素值
    /*public long countSubarrays(int[] nums, int minK, int maxK){
        int n = nums.length,minL = -1,maxL = -1,l = -1;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                l = i;
            }
            if (nums[i] == minK) {
                minL = i;
            }
            if (nums[i] == maxK) {
                maxL = i;
            }
            if (minL != -1 && maxL != -1) {
                res += Math.max(0,Math.min(minL,maxL) - l);
            }
        }
        return res;
    }*/


    //解法二：分段计算
   /* long res;
    int[] nums;
    int min,max;
    public long countSubarrays(int[] nums, int minK, int maxK){
        int n = nums.length,c = 0;
        min = minK;
        max = maxK;
        this.nums = nums;

        for (int i = 0; i < n; i++) {
            if (nums[i] < min || nums[i] > max) {
                compute(i-c,i-1);
                c = 0;
            }else
                c++;
        }
        compute(n-c,n-1);

        return res;
    }

    public void compute(int l,int r){
        int i = l,j = l,minC = 0,maxC = 0;
        for (; i <= r; i++) {
            if(nums[i] == min)
                minC++;
            if(nums[i] == max)
                maxC++;
            while (j <= i && minC > 0 && maxC > 0) {
                if(nums[j] == min)
                    minC--;
                if(nums[j] == max)
                    maxC--;
                j++;
            }

            res += (i-l+1) - (i-j+1);
        }
    }*/


    //重新写一遍
    public long countSubarrays(int[] nums, int min, int max){
        int n = nums.length;
        int minLoc = -1,maxLoc = -1,tL = -1;
        long res = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < min || nums[i] > max) {
                tL = i;
                continue;
            }
            if(nums[i] == min)
                minLoc = i;
            if(nums[i] == max)
                maxLoc = i;

            res += Math.max(Math.min(minLoc,maxLoc)-tL,0);
        }

        return res;
    }
}
