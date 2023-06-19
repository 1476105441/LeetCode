package algorithm.listTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * No898.子数组按位操作
 *
 * @author wjs 2023/6/19
 */
public class No898 {
    /**
     * 我们有一个非负整数数组 arr 。
     * 对于每个（连续的）子数组 sub = [arr[i], arr[i + 1], ..., arr[j]] （ i <= j），
     * 我们对 sub 中的每个元素进行按位或操作，获得结果 arr[i] | arr[i + 1] | ... | arr[j] 。
     * 返回可能结果的数量。 多次出现的结果在最终答案中仅计算一次。
     */

    //由于或操作相当于加法，累计的或值一定比后面的或值大
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        int size = 0;
        List<int[]> list = new ArrayList<>(n);
        for(int i=0;i < n;i++) {
            list.add(null);
        }
        Set<Integer> set = new HashSet<>();
        for(int i=0;i < n;i++) {
            list.set(size,new int[]{arr[i],i});
            set.add(arr[i]);
            size++;
            int j=0;
            for(int k=0;k < size;k++) {
                int[] tmp = list.get(k);
                tmp[0] = tmp[0] | arr[i];
                set.add(tmp[0]);
                if(tmp[0] == list.get(j)[0]) {
                    list.get(j)[1] = tmp[1];
                } else {
                    j++;
                    list.set(j,tmp);
                }
            }
            size = j+1;
        }
        return set.size();
    }
}
