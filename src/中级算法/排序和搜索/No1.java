package 中级算法.排序和搜索;

public class No1 {
    //                      颜色分类
    //给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    //此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

    //                  想法：双指针
    public void sortColors(int[] nums) {
        int low = 0,high = nums.length - 1,temp,i = low;

        while (i <= high) {
            //让当前的高位指针指向非2的数，避免重复操作
            while (i < high && nums[high] == 2) {
                high--;
            }

            //让当前的低位指针指向非0的数，避免重复操作
            while (low < i && nums[low] == 0) {
                low++;
            }

            //如果当前遍历指针所指的值是2，将其调换至高位指针的位置上去
            if (nums[i] == 2) {
                temp = nums[i];
                nums[i] = nums[high];
                nums[high] = temp;
                high--;
            }
            //注意此处并非else if，因为将遍历指针所指的值调换至高位指针位置
            // 后，原高位指针的值可能是0，为了避免出现漏换的现象，不使用else if
            if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[low];
                nums[low] = temp;
                low++;
            }
            i++;
        }
    }
}
