package 高级算法.数组和字符串;

public class No4 {
    /*                   盛水最多的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     */

    /*
     * 容器容积的关键因素：长和宽
     * 此问题是一个最优解问题，应该使用贪心或者动态规划的思想来试图解决
     *
     * 思想：使用双指针解决此问题
     */

    //失败品
    /*public int maxArea(int[] height){
        int temp,res,p1 = 0,p2 = 1,area1,area2;

        res = Math.min(height[0],height[1]);
        temp = res;
        for (int i = 2; i < height.length; i++) {
            if(height[p1] > height[i]){
                area1 = (i - p1) * height[i];
            }else{
                area1 = (i - p1) * height[p1];
            }
            if(height[p1] > height[i]){
                area2 = (i - p2) * height[i];
            }else{
                area2 = (i - p2) * height[p2];
            }
            if (area1 > temp && area2 > temp) {
                if (area1 > area2) {
                    p2 = i;
                    temp = area1;
                }else{
                    p1 = i;
                    temp = area2;
                }
            } else if (area1 > temp) {
                p2 = i;
                temp = area1;
            } else if (area2 > temp) {
                p1 = i;
                temp = area2;
            }

            if (temp > res) {
                res = temp;
            }
        }

        return res;
    }*/

    public int maxArea(int[] height){
        int left = 0,right = height.length - 1,res = 0,temp;

        while (left < right) {
            if (height[left] < height[right]) {
                temp = (right - left) * height[left];
                left++;
            }else{
                temp = (right - left) * height[right];
                right--;
            }
            if (res < temp) {
                res = temp;
            }
        }

        return res;
    }
}
