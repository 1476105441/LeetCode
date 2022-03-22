package 中级算法.其他;

public class No3 {
    //              多数元素
    //给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    //你可以假设数组是非空的，并且给定的数组总是存在多数元素。

    //------------------------------------------------------------------------
    //  难点就是如何只用常数个变量来确定数组中出现次数最多的元素是什么
    //一个思路：排序+顺序记录元素的个数，只需要常数个变量，空间复杂度是O(n)，但是时间复杂
    // 度是 O(nlog n + n) = O(nlog n)
    //测试结果：8ms，很慢，相当于复习堆排序了

    /*public int majorityElement(int[] nums) {
        mergeSort(nums);
        int max = 0,res = 0,count = 1,temp = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != temp) {
                if (count > max) {
                    max = count;
                    res = temp;
                }
                temp = nums[i];
                count = 1;
            }else{
                count++;
            }
        }
        if (count > max) {
            max = count;
            res = temp;
        }

        return res;
    }

    //堆排序
    public void mergeSort(int[] nums){
        int n = nums.length,temp;

        for (int i = (n - 2) / 2; i >= 0; i--) {
            shift(nums,i,n);
        }

        for (int i = n-1; i > 0; i--) {
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i);
        }

    }
    //堆排序的调整函数
    public void shift(int[] nums,int n,int length){
        int i = n, j = 2 * i + 1, temp;
        while (j < length) {
            if (j < length - 1 && nums[j] < nums[j + 1]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
    }*/
    //------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    //摩尔投票法：
    //  从数组第一个元素开始遍历，使用一个常量temp存储当前记录的这个元素，另一个常量count记录该元素的个数
    //往后遍历，如果遇到相同的元素则count+1，如果不相同，则count-1，若果count为0了，则取下一个元素的值来
    //更新temp的值，并且将count设置为1；遍历完最后一个元素之后temp的值就是所要的结果
    public int majorityElement(int[] nums) {
        int temp = nums[0], count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                temp = nums[i];
            }
            if (nums[i] != temp) {
                count--;
            }else{
                count++;
            }
        }

        return temp;
    }
}
