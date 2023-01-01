package review.搜索和排序;

public class No3 {
    /**
     *      颜色分类
     */

    //想法：使用两个指针，一个0指针，一个2指针
    public static void sortColors(int[] nums) {
        int zero = 0,two = nums.length-1,i = 0,temp;

        while (i <= two) {
            if (nums[i] == 0) {
                temp = nums[zero];
                nums[zero++] = nums[i];
                nums[i] = temp;
                if (i < zero) {
                    i++;
                }
            } else if (nums[i] == 2) {
                temp = nums[two];
                nums[two--] = nums[i];
                nums[i] = temp;
            }else{
                i++;
            }
        }
    }

    public static void main(String[] args) {
        sortColors(new int[]{2,0,2,1,1,0});
    }
}
