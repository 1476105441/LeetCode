package contest.year2023.m3.d5;

import java.util.*;

public class No3 {
    //超时了
    /*public int findValidSplit(int[] nums) {
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0;i < n;i++){
            if(list.isEmpty()){
                List<Integer> temp = new LinkedList<>();
                temp.add(i);
                list.add(temp);
            } else {
                boolean flag = false;
                for(List<Integer> temp : list){
                    int y = nums[temp.get(0)],x = nums[i];
                    if(gcd(y,x) != 1){
                        temp.add(i);
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    List<Integer> temp = new LinkedList<>();
                    temp.add(i);
                    list.add(temp);
                }
            }
        }
        //System.out.println(list.size());
        //合并区间
        List<Integer> t = list.get(0);
        int s = t.get(0),e = t.get(t.size()-1);
        List<List<Integer>> newList = new ArrayList<>();
        for(int i = 1;i < list.size();i++){
            List<Integer> t2 = list.get(i);
            if(t2.get(0) <= e){
                e = Math.max(e,t2.get(t2.size()-1));
                t.add(e);
                continue;
            }
            s = t2.get(0);
            e = t2.get(t2.size()-1);
            newList.add(t);
            t = t2;
        }
        newList.add(t);
        int res = 2147483647;
        for(List<Integer> temp : newList){
            int loc = temp.get(temp.size()-1);
            if(res > loc && loc != n-1){
                res = loc;
            }
        }
        return res == 2147483647 ? -1 : res;
    }
    private int gcd(int x,int y){
        while(y != 0){
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }*/

    //本质上，只要含有相同质因数的两个数字，就必须放到同一个区间内
    /*public int findValidSplit(int[] nums) {
        int n = nums.length;
        //left的key为质因数，value为最早出现质因数的下标
        Map<Integer,Integer> left = new HashMap<>();
        //right存放：和当前下标元素拥有相同质因数的最右边的下标
        int[] right = new int[n];
        for(int i = 0;i < n;i++){
            int val = nums[i];
            //进行质因数分解
            for(int j = 2;j * j <= val;j++){
                if(val % j == 0){
                    Integer loc = left.get(j);
                    if(loc == null){
                        left.put(j,i);
                    } else {
                        right[loc] = i;
                    }
                    //除掉所有相同的质因数
                    for(val /= j;val % j == 0;val /= j){}
                }
            }
            if(val != 1){
                Integer loc = left.get(val);
                if(loc == null){
                    left.put(val,i);
                } else {
                    right[loc] = i;
                }
            }
        }
        int l = 0,max = 0;
        for(;l < n;l++){
            if(l > max){
                return max;
            }
            max = Math.max(max,right[l]);
        }
        return -1;
    }*/

    //质因数分解+遍历
    //记录每个和当前质因数相同的最右边的下标
    public int findValidSplit(int[] nums) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>(n);
        int[] right = new int[n];
        for(int i = 0;i < n;i++){
            int val = nums[i];
            for(int j = 2;j * j <= val;j++){
                if(val % j == 0){
                    Integer loc = map.get(j);
                    if(loc == null) map.put(j,i);
                    else right[loc] = i;
                    for(val /= j;val % j == 0;val /= j){}
                }
            }
            if(val != 1){
                Integer loc = map.get(val);
                if(loc == null) map.put(val,i);
                else right[loc] = i;
            }
        }
        int l = 0,max = 0;
        for( ;l < n;l++){
            //已经走到拥有最右边质因数的右边，说明max是安全的位置（满足题目条件）
            if(l > max) return max;
            max = Math.max(max,right[l]);
        }
        return -1;
    }
}
