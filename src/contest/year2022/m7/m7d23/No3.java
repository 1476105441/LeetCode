package contest.year2022.m7.m7d23;

import java.util.*;

public class No3 {
    /**
     *      设计数字容器系统
     */


    //一个值对应多个下标，一个下标对应一个值
    class NumberContainers {
        Map<Integer,Integer> ind;
        Map<Integer, List<Integer>> val;
        public NumberContainers() {
            ind = new HashMap<>();
            val = new HashMap<>();
        }

        public void change(int index, int number) {
            //把原来这个位置的删除掉
            Integer v = ind.get(index);
            if (v != null) {
                if(v.equals(number))
                    return;
                ind.remove(index);
                update(v,index);
            }
            ind.put(index,number);
            List<Integer> list = val.get(number);
            if (list == null) {
                list = new ArrayList<>();
                val.put(number,list);
            }
            list.add(index);
            minUpdate(list,list.size()-1);
        }

        public int find(int number) {
            List<Integer> list = val.get(number);
            if(list == null || list.isEmpty())
                return -1;
            return list.get(0);
        }

        public void update(int v,int index){
            List<Integer> list = val.get(v);
            int i,n = list.size();
            for ( i = 0; i < n; i++) {
                if (list.get(i).equals(index)) {
                    break;
                }
            }
            list.set(i,list.get(n-1));
            list.remove(n-1);
            minShift(list,i,n-1);
        }

        public void minUpdate(List<Integer> nums,int s){
            int p = (s-1) >> 1;
            while (s != 0) {
                if (nums.get(s) < nums.get(p)) {
                    int temp = nums.get(s);
                    nums.set(s,nums.get(p));
                    nums.set(p,temp);
                    s = p;
                    p = (s-1)>>1;
                }else
                    break;
            }
        }

        public void minShift(List<Integer> nums,int s,int n){
            int i = s,j = (i << 1)+1;
            while (j < n) {
                if(j < n-1 && nums.get(j) > nums.get(j+1))
                    j++;

                if (nums.get(i) > nums.get(j)) {
                    int temp = nums.get(i);
                    nums.set(i,nums.get(j));
                    nums.set(j,temp);
                    i = j;
                    j = (i << 1) + 1;
                }else
                    break;
            }
        }
    }
}
