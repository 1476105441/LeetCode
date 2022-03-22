package 初级算法.数组;

public class No5 {
    //                  只出现一次的数字
    //  给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    //说明：
    //  你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？


    //-------------------------------------------------------------------
    //非最优解，时间用了6ms，被吊打
//    public int singleNumber(int[] nums) {
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        Arrays.sort(nums);
//        //排序之后相同的数都是连在一起的，所以每次加2
//        for (int i = 0; i < nums.length; ) {
//            if (i < nums.length-1 && nums[i] != nums[i+1]) {
//                return nums[i];
//            } else if (i == nums.length-1) {
//                if (nums[i] != nums[i-1]) {
//                    return nums[i];
//                }
//            }
//            i+=2;
//            if (i > nums.length) {
//                i=nums.length - 1;
//            }
//        }
//        return 0;
//    }
    //---------------------------------------------------------------------

    //---------------------------------------------------------------------
    //                  位运算（异或运算，最优解）
    // 异或运算^(相同为假，不同为真):
    //      a^a=0；自己和自己异或等于0
    //      a^0=a；任何数字和0异或还等于他自己
    //      a^b^c=a^c^b；异或运算具有交换律
    public int singleNumber(int nums[]) {
        int result = 0;
        for (int i = 0; i < nums.length; i++)
            result ^= nums[i];
        return result;
    }
    //------------------------------------------------------------------------

    //------------------------------------------------------------------------
    //                      哈希表解法
    /*
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                //如果添加失败，说明这个值
                //在集合Set中存在，我们要
                //把他给移除掉
                set.remove(num);
            }
        }
        //最终集合Set中只有一个元素，我们直接返回
        return (int) set.toArray()[0];
    }*/
    //--------------------------------------------------------------------------
}
