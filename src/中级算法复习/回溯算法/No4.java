package 中级算法复习.回溯算法;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    //              子集

    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums){
        res = new ArrayList<>();

        res.add(new ArrayList<>());

        recursion(nums,0,new int[nums.length],0);

        return res;
    }

    public void recursion(int[] nums,int k,int[] temp,int length){
        if (k == nums.length) {
            return;
        }
        for(;k < nums.length;k++){
            temp[length] = nums[k];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < length + 1; i++) {
                list.add(temp[i]);
            }
            res.add(list);
            recursion(nums,k+1,temp,length+1);
        }
    }
}
