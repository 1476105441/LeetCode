package junior.数组;

public class No8 {
    //                移动零
    //  给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //
    //说明:
    //    必须在原数组上操作，不能拷贝额外的数组。
    //    尽量减少操作次数。

    //-------------------------------------------------------------------
    //简单问题复杂化，别人几行代码我要写几十行，是该反思一下自己了
//    public void moveZeroes(int[] nums){
//        //思路：采用双指针能否解决？设置一个计数的变量count记录0的数量，第一个指针one指向首先为0的位
//        // 置，计数器的值加一，当第二个指针two找到第二个为0的位置时，将one到two之间的元素往前移
//        // count个位置，然后count++，one移动到two-1位置，如此直到two等于nums.length
//        if(nums.length <= 1){
//            return;
//        }else if (nums.length == 2){
//            if(nums[0] == 0 && nums[1] !=0){
//                nums[0] = nums[1];
//                nums[1] = 0;
//            }
//        }
//        int one,two,count=0;
//        for ( int i = 0; i <nums.length ; i++) {
//            if (nums[i] == 0) {
//                //one指向第一个为零的位置
//                one = i;
//                count=1;
//                for(two = one+1;two<nums.length;two++){
//                    if (nums[two] == 0) {
//                        //让one+count到two-count的元素往前移count位
//                        int j = one + count;
//                        while (j < two) {
//                            nums[j-count] = nums[j];
//                            j++;
//                        }
//                        //如果两个0是相邻的，即两个0之间没有元素，之前的循环不会执行,one的位置也没有改变
//                        one = two - count;
//                        count++;
//                    }
//                }
//                //满足下列条件时，说明还有数值没有移动到前面去
//                if (one < nums.length - count||count > nums.length/2) {
//                    int j = one + count;
//                    while (j < nums.length&&nums[j]!=0) {
//                        nums[j-count] = nums[j];
//                        j++;
//                    }
//                    one = nums.length - count;
//                }
//                //非0的已经全部移动到前面了，现在将后面的0补上即可
//                while (one < nums.length) {
//                    nums[one] = 0;
//                    one++;
//                }
//                //到这里说明two已经循环一遍了，直接退出就可以了
//                break;
//            }
//        }
//    }
    //-------------------------------------------------------------------

    //-------------------------------------------------------------------
    //看看别人写的多简洁，自己怎么就写不出来？
    //把非0的往前挪
    // public void moveZeroes(int[] nums) {
    //        int i = 0;//统计前面0的个数
    //        for (int j = 0; j < nums.length; j++) {
    //            if (nums[j] == 0) {//如果当前数字是0就不操作
    //                i++;
    //            } else if (i != 0) {
    //                //否则，把当前数字放到最前面那个0的位置，然后再把
    //                //当前位置设为0
    //                nums[j - i] = nums[j];
    //                nums[j] = 0;
    //            }
    //        }
    //}
    //-------------------------------------------------------------------

    //精简的双指针（12行）
    public void moveZeroes(int[] nums) {
        int i = 0;//统计前面0的个数
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {//如果当前数字是0就不操作
                i++;
            } else if (i != 0) {
                //否则，把当前数字放到最前面那个0的位置，然后再把
                //当前位置设为0
                nums[j - i] = nums[j];
                nums[j] = 0;
            }
        }
    }

}
