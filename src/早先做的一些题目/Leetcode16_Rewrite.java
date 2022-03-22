package 早先做的一些题目;

public class Leetcode16_Rewrite {
    public int threeSumClosest(int[] nums, int target){
        int res,i,j,k,n,sum;
        n=nums.length;
        LeetCode16.quickSort(nums,0,n-1);
        res=nums[0]+nums[1]+nums[2];
        if(res==target)
            return res;
        for(i=0;i<n;i++){
            if(i!=0&&nums[i]==nums[i-1])
                continue;
            //左指针
            j=i+1;
            //右指针
            k=n-1;
            while(j<k){
                //去重
                while(j!=i+1&&nums[j]==nums[j-1]) j++;
                while(k!=n-1&&nums[k]==nums[k+1]) k--;
                sum=nums[i]+nums[j]+nums[k];
                if(sum==target)
                    return sum;
                if(Math.abs(sum-target)<Math.abs(res-target))
                    res=sum;
                if(j<k&&sum<target)
                    j++;
                else if(j<k&&sum>target)
                    k--;
            }
        }
        return res;
    }
}
