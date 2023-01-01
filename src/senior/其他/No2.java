package senior.其他;

public class No2 {
    /**
     * 接雨水
     */

    //解法一：使用单调栈，成功，3ms
    /*int res,loc;
    public int trap(int[] height) {
        int n = height.length;
        res = 0;
        loc = 0;
        int[][] stack = new int[n][2];

        for (int i = 0; i < n; i++) {
            int[] temp = new int[]{height[i],i};
            if (loc == 0 || stack[loc - 1][0] > temp[0]) {
                stack[loc++] = temp;
            }else
                count(stack,temp);
        }

        return res;
    }
    public void count(int[][] stack,int[] num){
        int x;
        while (loc > 0) {
            if (stack[loc - 1][0] <= num[0]) {
                x = stack[loc-1][0];
                stack[loc-1] = null;
                loc--;
                if(loc > 0)
                    res += (Math.min(stack[loc-1][0],num[0])-x) * (num[1]-stack[loc-1][1]-1);
                else{
                    stack[loc++] = num;
                    break;
                }
            }else{
                stack[loc++] = num;
                break;
            }
        }
    }*/

    //精简了结构，但还是3ms
    /*int res, loc;
    public int trap(int[] height) {
        int n = height.length;
        res = 0;
        loc = 0;
        int[][] stack = new int[n][2];

        for (int i = 0; i < n; i++) {
            int[] temp = new int[]{height[i], i};
            count(stack,temp);
        }

        return res;
    }

    public void count(int[][] stack, int[] num) {
        int x;

        while (loc > 0 && stack[loc - 1][0] <= num[0]) {
            x = stack[loc - 1][0];
            stack[loc - 1] = null;
            loc--;
            if (loc > 0)
                res += (Math.min(stack[loc - 1][0], num[0]) - x) * (num[1] - stack[loc - 1][1] - 1);
        }
        if (loc == 0 || stack[loc - 1][0] > num[0]) {
            stack[loc++] = num;
        }
    }*/

    //解法二：使用动态规划，1ms
    //思想，以每个元素为单位统计其所能接的雨水
    /*public int trap(int[] height){
        int n = height.length,res = 0;
        int[] left = new int[n],right = new int[n];
        left[0] = height[0];
        right[n-1] = height[n-1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i-1],height[i]);
            right[n-i-1] = Math.max(right[n-i],height[n-i-1]);
        }

        for (int i = 0; i < n; i++) {
            res += Math.min(left[i],right[i]) - height[i];
        }

        return res;
    }*/

    //解法三：双指针，0ms
    //移原则，保持右指针的元素值要大于左指针的
    //元素值，如果不大于，就移动右指针，反之移
    //动左指针
    public int trap(int[] height){
        int n = height.length,l=0,lMax=height[l],r=n-1,rMax=height[r];
        int res = 0;

        while(l < r) {
            if (height[l] >= height[r]) {
                res += rMax-height[r];
                r--;
                if(l < r && rMax < height[r])
                    rMax = height[r];
            }else{
                res += lMax-height[l];
                l++;
                if(l < r && lMax < height[l])
                    lMax = height[l];
            }
        }

        return res;
    }
}
