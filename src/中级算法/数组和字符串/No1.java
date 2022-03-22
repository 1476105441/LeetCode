package 中级算法.数组和字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class No1 {
    //                      三数之和
    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    //
    //注意：答案中不可以包含重复的三元组。


    //--------------------------------------------------------------------------------
    //                      失败品：无法保证不会有重复的
    //想法：差值法的类似，用一个哈希表存放a + b 的值，由于要满足a + b + c == 0 ，则即为 a + b = -c
    //如果哈希表中关键字为-c的元素存在，即为所要的三元表
    /*public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();

        //存放-c的哈希表，后面一个是对是否已经取过的判断
        HashMap<Integer,Integer> map = new HashMap<>();

        Arrays.sort(nums);

        //双指针进行遍历
        int one, two, three;

        for (one = 0;one < nums.length - 1 && nums[one] < 0;one++){
            for (two = one + 1; two < nums.length; two++){
                map.put((nums[one] + nums[two]) * -1,one * 10 + two);
            }
        }

        Integer num;
        for (three = 0;three < nums.length;three++){
            if ((num = map.get(nums[three])) != null && num != 0) {
                List<Integer> list = new ArrayList<>();

                two = num % 10;
                one = num / 10;

                if (three == one || three == two) {
                    continue;
                }
                list.add(nums[three]);
                list.add(nums[two]);
                list.add(nums[one]);

                resList.add(list);
                map.put(nums[three],0);
            }
        }

        return resList;
    }*/
    //-------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------
    //                  失败：简单问题复杂化，想的越多，结果越多
    //换个思路：先排序，再设置头尾双指针来寻找
    /*
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> resList = new ArrayList<>();

        if (nums.length < 3) {
            return resList;
        }

        //先排序
        Arrays.sort(nums);

        int one,two=0,three,flag;

        for (one = 0;one < nums.length;){
            two = nums.length - 1;
            if (nums[one] == 0 && nums[two] == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(0);
                list.add(0);
                list.add(0);
                resList.add(list);
                return resList;
            }
            if (nums[one] >= 0 || nums[two] < 0) {
                break;
            }

            if (nums[one] + nums[two] > 0) {
                flag = 0;
                three = one + 1;
            }else{
                if (nums[one] + nums[two] < 0) {
                    flag = 1;
                }else{
                    flag = 2;
                }
                three = two - 1;
            }

            while ((flag == 0 && nums[three] < 0) || (flag == 1 && nums[three] >= 0)) {
                if (nums[one] + nums[two] + nums[three] == 0) {
                    List<Integer>list = new ArrayList<>();
                    list.add(nums[one]);
                    list.add(nums[two]);
                    list.add(nums[three]);
                    resList.add(list);
                }
                if (flag == 2) {
                    three++;
                    //去重
                    while( three < nums.length - 1 && nums[three] == nums[three-1]){
                        three++;
                    }
                    two--;
                    while(three > 0 && nums[three] == nums[three+1]){
                        three--;
                    }
                }
                if (flag == 0) {
                    if (nums[one] + nums[two] + nums[three] > 0 || three == nums.length -1) {
                        break;
                    }
                    three++;

                }else{
                    if (nums[one] + nums[two] + nums[three] < 0 || three == 0) {
                        break;
                    }
                    three--;

                }


            }

            if (flag == 0) {
                two--;
                while( two > 0 && nums[two] == nums[two+1]){
                    two--;
                }
            }else{
                one++;
                while(one < nums.length - 1 && nums[one] == nums[one-1]){
                    one++;
                }
            }
        }

        return resList;
    }*/
    //-------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------
    //                      双指针法：成功
    //此题是寻找三个数，如果要一次寻找三个数的话就需要三个指针，但是这样做的话就需要三重循环，效率
    // 太低，此时，思考一下，此题是要做什么？在一个数组中进行特殊的查找，是否可以使用双指针来进行
    // 查找呢？那么要如何将三个指针的问题变成双指针问题呢？
    //
    //          先固定一个指针，将问题转换成双指针问题
    //
    //          *关键还是在于去重
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> resList = new ArrayList<>();

        if (nums.length < 3) {
            return resList;
        }

        Arrays.sort(nums);

        int one,two,three;

        for (one = 0;one < nums.length - 2;one++) {
            if (nums[one] > 0) {
                break;
            }

            if (one > 0 && nums[one] == nums[one-1]) {
                continue;
            }

            two = one + 1;
            three = nums.length - 1;

            while (three > two) {
                if (nums[one] + nums[two] + nums[three] == 0) {
                    List<Integer> list = new ArrayList<>();

                    list.add(nums[one]);
                    list.add(nums[two]);
                    list.add(nums[three]);

                    resList.add(list);

                    //三数和为0后，直接两个指针同时缩减范围，不必担心会有数漏掉，因为外层还有一层循环可以把此
                    // 次循环错过的组合补上，并且还能避免重复
                    two++;
                    three--;
                    while (three > two && nums[two] == nums[two - 1] ) {
                        two++;
                    }
                    while (three > two && nums[three] == nums[three + 1] ) {
                        three--;
                    }
                } else if (nums[one] + nums[two] + nums[three] < 0) {
                    two++;
                }else{
                    three--;
                }
            }
        }

        return resList;
    }
}
