package review.数组;

public class No21 {
    /**
     *      盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     */

    /*public int maxArea(int[] height){
        int left = 0,right = height.length-1,max = 0;

        while (left < right) {
            int temp = (right-left) * Math.min(height[left],height[right]);
            if (temp > max) {
                max = temp;
            }
            if (height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }

        return max;
    }*/


    //跳过了一些不必要的判断，时间快了1ms
    public int maxArea(int[] height){
        int left = 0,right = height.length-1,max = 0;

        while (left < right) {
            int temp = (right-left) * Math.min(height[left],height[right]);
            if (temp > max) {
                max = temp;
            }
            if (height[left] < height[right]) {
                int val = height[left];
                left++;
                while (left < right && height[left] < val) {
                    left++;
                }
            }else{
                int val = height[right];
                right--;
                while (left < right && height[right] < val) {
                    right--;
                }
            }
        }

        return max;
    }
}
