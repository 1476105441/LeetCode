package 初级算法.其他;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    //                  杨辉三角
    //给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。

    //想法：杨辉三角的每一行的第一个和最后一个都是1而其余的中间的数如下标为j的元素是前一行下表为j和下标为j+1的元素之和
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();

            for (int j = 0; j < i+1; j++) {
                //j等于第一个或者j等于最后一个
                if (j == 0 || j == numRows - 1) {
                    list.add(1);
                    continue;
                }

                //为中间的数时
                list.add(res.get(i-1).get(j) + res.get(i-1).get(j+1));
            }

            res.add(list);
        }

        return res;
    }
}
