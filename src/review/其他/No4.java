package review.其他;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    /**
     *      杨辉三角
     */

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            res.add(list);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                }else{
                    List<Integer> temp = res.get(i - 1);
                    list.add(temp.get(j-1)+temp.get(j));
                }
            }
        }

        return res;
    }
}
