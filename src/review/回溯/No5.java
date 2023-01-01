package review.回溯;

import java.util.ArrayList;
import java.util.List;

public class No5 {
    /**
     *      全排列
     */

    //使用迭代的方法实现回溯，注意实现中的一些细节
    /*public List<List<Integer>> permute(int[] nums){
        //flags用于标识有没有遍历过，temp用于存储第i个节点当前所对应的数组下标
        int[] flags = new int[nums.length],temp = new int[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            temp[i] = -1;
        }

        while (k >= 0) {
            if (temp[k] != -1) {
                flags[temp[k]] = 0;
            }
            temp[k] += 1;
            while (temp[k] < nums.length && flags[temp[k]] == 1) {
                temp[k] += 1;
            }
            //当前已经没有元素可以选择，回溯到前一个节点
            if (temp[k] == nums.length) {
                temp[k] = -1;
                k--;
                continue;
            }
            flags[temp[k]] = 1;
            if (k == nums.length - 1) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < nums.length; i++) {
                    list.add(nums[temp[i]]);
                }
                res.add(list);
                flags[temp[k]] = 0;
                temp[k] = -1;
                k--;
            }else{
                k++;
            }
        }

        return res;
    }*/

    //使用递归的方式回溯
    int[] flags,temp;
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums){
        res = new ArrayList<>();
        flags = new int[nums.length];
        temp = new int[nums.length];
        recursion(nums,0);
        return res;
    }
    public void recursion(int[] nums,int loc){
        if (loc == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < temp.length; i++) {
                list.add(temp[i]);
            }
            res.add(list);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (flags[i] == 0) {
                flags[i] = 1;
                temp[loc] = nums[i];
                recursion(nums,loc+1);
                flags[i] = 0;
            }
        }
    }
}
