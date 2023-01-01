package review.中级算法复习.回溯算法;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    //          全排列

    /*public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] a = new int[nums.length];
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = -1;
        }

        while (k >= 0) {
            a[k]++;
            while (a[k] < a.length && !ok(a, k)) {
                a[k]++;
            }
            if (a[k] < a.length && k < a.length - 1) {
                k++;
            } else {
                if (k == a.length - 1 && a[k] < a.length) {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < a.length; i++) {
                        list.add(nums[a[i]]);
                    }
                    res.add(list);
                }
                a[k] = -1;
                k--;
            }
        }

        return res;
    }
    public boolean ok(int[] nums, int i) {
        for (int j = 0; j < i; j++) {
            if (nums[j] == nums[i]) {
                return false;
            }
        }
        return true;
    }*/


    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums){
        res = new ArrayList<>();

        recursion(nums,new int[nums.length],0);

        return res;
    }

    public void recursion(int[] nums,int[] temp,int length){
        //当取到结果时，将结果放入结果集中
        if (length == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                list.add(temp[i]);
            }
            res.add(list);
            return;
        }

        boolean flag;

        for (int i = 0; i < nums.length; i++) {
            flag = true;
            temp[length] = nums[i];
            for (int j = 0; j < length; j++) {
                if (temp[j] == temp[length]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                recursion(nums,temp,length+1);
            }
        }
    }
}
