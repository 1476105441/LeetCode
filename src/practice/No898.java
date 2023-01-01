package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No898 {
    /**
     *  子数组按位或操作
     */

    //成功，179ms
    /*public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        List<int[]> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            list.add(new int[]{arr[i],i});

            int j = 0;
            for (int[] temp : list){
                set.add(temp[0]);
                temp[0] |= arr[i];
                if(list.get(j)[0] == temp[0])
                    list.get(j)[1] = temp[1];
                else
                    list.set(++j,temp);
            }
            list.subList(j+1,list.size()).clear();
        }
        for (int[] temp :list)
            set.add(temp[0]);

        return set.size();
    }*/

    //效果一样，只是去掉了不必要的代码
    public int subarrayBitwiseORs(int[] arr){
        int n = arr.length;
        List<int[]> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            list.add(new int[]{arr[i]});

            int j = 0;
            for (int[] temp : list){
                set.add(temp[0]);
                temp[0] |= arr[i];
                if(list.get(j)[0] != temp[0])
                    list.set(++j,temp);
            }
            list.subList(j+1,list.size()).clear();
        }
        for (int[] temp :list)
            set.add(temp[0]);

        return set.size();
    }
}
