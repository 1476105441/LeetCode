package 初级算法.动态规划;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeetCode118 {
    //动态规划之杨辉三角
    public static void main(String[] args) {
        List<List<Integer>> list = generate(6);
        Iterator<List<Integer>> it = list.iterator();
        while (it.hasNext()) {
            List<Integer> list1 = it.next();
            Iterator<Integer> it2 = list1.iterator();
            while (it2.hasNext()) {
                System.out.print(it2.next()+" ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> generate(int numRows){
        List<List<Integer>> list = new ArrayList<>();
        int i;
        long l1 = System.currentTimeMillis();
        for( i=1;i<=numRows;i++){
            List<Integer> integers = new ArrayList<>();
            for(int k =0 ;k<i;k++){
                integers.add(1);
            }
            list.add(integers);
        }
        //直接从第三行开始，若没有三行就直接return即为所要的答案
        for(i = 2;i<numRows;i++){
            int k;
            //最后一个值不修改
            for( k = 1;k<i;k++){
                list.get(i).set(k,list.get(i-1).get(k-1) + list.get(i-1).get(k)) ;
            }
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
        return list;
    }
}
