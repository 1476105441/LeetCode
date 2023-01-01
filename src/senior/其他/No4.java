package senior.其他;

public class No4 {
    /**
     *      柱状图中最大的矩形
     */

    //单调栈，771ms，非常的慢
    /*public int largestRectangleArea(int[] heights) {
        int n = heights.length,loc = 0,max = 0;
        int[][] stack = new int[n][2];

        for (int i = 0; i < n; i++) {
            int s = 0;
            while(loc != 0 && stack[loc-1][0] >= heights[i]){
                s = stack[loc-1][1];
                loc--;
            }

            if (heights[i] != 0) {
                stack[loc][0] = heights[i];
                stack[loc][1] = s+1;
                for (int j = 0; j < loc; j++) {
                    stack[j][1]++;
                    max = Math.max(max,stack[j][0]*stack[j][1]);
                }
                max = Math.max(stack[loc][0]*stack[loc][1],max);
                loc++;
                max = Math.max(heights[i],max);
            }
        }

        return max;
    }*/

    //没什么两样，还是复杂度报表，679ms
    /*public int largestRectangleArea(int[] heights) {
        int n = heights.length,loc = 0,max = 0;
        int[][] stack = new int[n][2];

        for (int i = 0; i < n; i++) {
            int s = 0;
            while(loc != 0 && stack[loc-1][0] >= heights[i]){
                s = stack[loc-1][1]/stack[loc-1][0];
                loc--;
            }

            if (heights[i] != 0) {
                stack[loc][0] = heights[i];
                stack[loc][1] = (s+1) * heights[i];
                for (int j = 0; j < loc; j++) {
                    stack[j][1] += stack[j][0];
                    max = Math.max(max,stack[j][1]);
                }
                max = Math.max(stack[loc][1],max);
                loc++;
                max = Math.max(heights[i],max);
            }
        }

        return max;
    }*/

    //单调栈
    //思想：统计每个元素为中心，所能够取得的最大宽度，也就是
    //说，找到大于等于当前元素值的范围（即矩形的宽）
    /*public int largestRectangleArea(int[] heights){
        int n = heights.length,loc=0;
        int[] left = new int[n],right = new int[n],stack = new int[n];
        for (int i = 0; i < n; i++) {
            while (loc != 0 && heights[stack[loc - 1]] >= heights[i]) {
                loc--;
            }
            if (loc == 0)
                left[i] = -1;
            else
                left[i] = stack[loc-1];
            stack[loc] = i;
            loc++;
        }
        loc = 0;
        for (int i = n-1; i > -1; i--) {
            while (loc != 0 && heights[stack[loc - 1]] >= heights[i]) {
                loc--;
            }
            if (loc == 0)
                right[i] = n;
            else
                right[i] = stack[loc-1];
            stack[loc] = i;
            loc++;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res,(right[i]-left[i]-1) * heights[i]);
        }
        return res;
    }*/

    //对上述解法进行改进，只遍历一次得出结果
    public int largestRectangleArea(int[] heights){
        int n = heights.length,loc=0;
        int[] left = new int[n],right = new int[n],stack = new int[n];
        for (int i = 0; i < n; i++) {
            while (loc != 0 && heights[stack[loc - 1]] >= heights[i]) {
                right[stack[loc - 1]] = i;
                loc--;
            }
            if (loc == 0)
                left[i] = -1;
            else
                left[i] = stack[loc-1];
            stack[loc] = i;
            loc++;
        }
        while (loc != 0) {
            right[stack[loc - 1]] = n;
            loc--;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res,(right[i]-left[i]-1) * heights[i]);
        }
        return res;
    }
}
