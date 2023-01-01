package middle.回溯算法;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    //                  子集
    //  给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    //解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

    //
    /*List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {

        res = new ArrayList<>();

        int n = nums.length;

        int[] temp = new int[n];
        reverser(nums,0,temp,0);

        return res;
    }

    //n用于定位当前所要加入的数字
    public void reverser(int[] nums,int n,int[] temp,int length){
        if (n > nums.length) {
            return;
        }

        List<Integer> list = new ArrayList<>();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                list.add(temp[i]);
            }
        }
        res.add(list);

        for (int i = n; i < nums.length; i++) {
            temp[length] = nums[i];
            reverser(nums,i+1,temp,length+1);
        }
    }*/


    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums){
        int n = nums.length;
        int[] temp = new int[n];
        res = new ArrayList<>();
        res.add(new ArrayList<>());
        recursion(temp,0,nums,0);

        return res;
    }
    public void recursion(int[] temp,int l,int[] nums,int loc){
        if (loc >= nums.length) {
            return;
        }
        temp[l] = nums[loc];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < l + 1; i++) {
            list.add(temp[i]);
        }
        res.add(list);

        recursion(temp,l+1,nums,loc+1);
        recursion(temp,l,nums,loc+1);
    }
}
