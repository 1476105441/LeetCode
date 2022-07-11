package 复习.中级算法复习.排序和搜索;

public class No1 {
    //              颜色分类

    /**
     * 想法：使用双指针，一个指针指向0号元素的位置，一个指针指向
     * 2号元素的位置，因为这两个元素的位置一个是在前面一个是在后面
     * 而1号元素的位置是可能会改变的
     */
    public void sortColors(int[] nums){
        int loc0 = 0, loc2 = nums.length - 1,temp;

        for (int i = 0; i <= loc2; ) {
            while (loc2 > 0 && nums[loc2] == 2) {
                loc2--;
            }
            if(i > loc2){
                break;
            }
            if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[loc0];
                nums[loc0] = temp;
                loc0++;
                if (nums[i] != 2) {
                    i++;
                }
            } else if (nums[i] == 2) {
                temp = nums[i];
                nums[i] = nums[loc2];
                nums[loc2] = temp;
                loc2--;
                if (nums[i] != 0) {
                    i++;
                }
            }else {
                i++;
            }
        }
    }
}
