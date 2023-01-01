package middle.回溯算法;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    //                      全排列

    //------------------------------------------------------------------------
    //                      成功：0ms
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();

        recursion(nums,0,new int[nums.length]);

        return res;
    }

    public void recursion(int[] nums,int size,int[] temp){
        if (size == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < temp.length; i++) {
                list.add(temp[i]);
            }
            res.add(list);
            return;
        }

        boolean flag;
        for (int i = 0; i < nums.length; i++) {
            flag = true;
            for (int j = 0; j < size; j++) {
                if(temp[j] == nums[i]){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                temp[size] = nums[i];
                recursion(nums,size+1,temp);
            }
        }
    }
}
