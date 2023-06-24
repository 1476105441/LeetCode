package algorithm.listTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 1521.找到最接近目标值的函数值
 *
 * @author wjs 2023/6/24
 */
public class No1521 {

    public int closestToTarget(int[] arr, int target) {
        int n = arr.length;
        List<int[]> list = new ArrayList<>(n);
        for(int i=0;i < n;i++) {
            list.add(null);
        }
        int size = 0;
        int res = Integer.MAX_VALUE;
        for(int i=0;i < n;i++) {
            list.set(size,new int[]{arr[i],i});
            size++;
            int j=0;
            for(int k=0;k < size;k++) {
                int[] tmp = list.get(k);
                tmp[0] = tmp[0] & arr[i];
                res = Math.min(res,Math.abs(tmp[0] - target));
                if(tmp[0] == list.get(j)[0]) {
                    tmp[1] = list.get(j)[1];
                } else {
                    j++;
                    list.set(j,tmp);
                }
            }
            size = j+1;
        }
        return res;
    }

}
