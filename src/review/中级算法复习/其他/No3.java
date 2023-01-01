package review.中级算法复习.其他;

public class No3 {
    //              多数元素

    /**
     * 类似于率兵打仗，相同的元素就是自己人加一，不同的元素就是
     * 敌军自己人减一，到最后幸存下来的就是多数元素
     */
    public int majorityElement(int[] nums){
        int temp = nums[0],count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                temp = nums[i];
                count++;
            } else if (nums[i] != temp) {
                count--;
            } else{
                count++;
            }
        }

        return temp;
    }
}
