package 复习.回溯;

import java.util.ArrayList;
import java.util.List;

public class No6 {
    /**
     *      子集
     */

    //递归
    /*List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums){
        res = new ArrayList<>();
        res.add(new ArrayList<>());
        recursion(nums,0,null);
        return res;
    }
    public void recursion(int[] nums,int loc,List<Integer> pre){
        if (loc == nums.length) {
            return;
        }
        for (int i = loc; i < nums.length; i++) {
            List<Integer> temp = new ArrayList<>();
            if(pre != null){
                for (Integer j : pre) {
                    temp.add(j);
                }
            }
            temp.add(nums[i]);
            res.add(temp);
            recursion(nums,i+1,temp);
        }
    }*/

    //迭代，此方法失败，考虑不周
    /*public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int[] temp = new int[nums.length];
        int k = 0,pre = -1;
        for (int i = 0; i < temp.length; i++) {
            temp[i] = -1;
        }
        while (k >= 0) {
            if (temp[k] != -1) {
                temp[k]++;
            }else{
                temp[k] = pre+1;
            }
            if (k == nums.length-1 || temp[k] == nums.length) {
                if (temp[k] == nums.length) {
                    temp[k] = -1;
                    k--;
                    continue;
                }
                List<Integer> list = new ArrayList<>(k);
                for (int i = 0; i <= k; i++) {
                    list.add(nums[temp[i]]);
                }
                res.add(list);
            }else{
                pre = temp[k];
                k++;
            }
        }

        return res;
    }*/

    //使用二进制标识的迭代法，有点类似于状态压缩数组
    /*public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length,max = 1 << n;

        for (int i = 0; i < max; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    temp.add(nums[j]);
                }
            }
            res.add(temp);
        }
        return res;
    }*/

    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int m = 1 << (nums.length);

        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }

        return res;
    }
}
