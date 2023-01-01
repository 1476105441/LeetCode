package senior.数学;

import java.util.*;

public class No2 {
    /**
     * 直线上最多的点数
     */

    //哈希表，直接使用字符串做区分
    //首先，两点确定一条直线
    //成功，不要忘记了特殊情况：x坐标值相等时
    /*public int maxPoints(int[][] points) {
        Map<String, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            int[] flag = new int[points.length];
            for (int j = i+1; j < points.length; j++) {
                if (flag[j] == 0) {
                    double a,b,c;
                    a = points[i][1]-points[j][1]; //y1-y2
                    b = points[i][0]-points[j][0]; //x1-x2
                    StringBuilder sb = new StringBuilder();
                    if (b == 0) {
                        sb.append(points[i][0]);
                    }else{
                        c = (points[i][0]*points[j][1]-points[i][1]*points[j][0])/b;
                        sb.append(a/b).append("+").append(c);
                    }
                    Set<Integer> list = map.get(sb.toString());
                    if(list == null){
                        list = new HashSet<>();
                        map.put(sb.toString(),list);
                    }else{
                        for (Integer loc : list) {
                            flag[loc] = 1;
                        }
                    }
                    list.add(j);
                    list.add(i);
                }
            }
        }
        int max = 1;
        Set<Map.Entry<String, Set<Integer>>> entries = map.entrySet();
        for (Map.Entry<String, Set<Integer>> node : entries) {
            int s = node.getValue().size();
            if (s > max) {
                max = s;
            }
        }

        return max;
    }*/


    //使用斜率唯一代表一条直线，使用斜率做key，由于每次
    //是在循环里做hashMap，所以可以直接使用斜率做key
    //使用欧几里得算法求最大公约数，需要做一些特判
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int res = 1;

        for (int i = 0; i < n; i++) {
            if(res > (n/2) || res >= n-i)
                return res;
            Map<Integer,Integer> map = new HashMap<>();
            for (int j = i+1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                }else{
                    //统一符号位
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                }
                int key = y + 20001*x;
                map.put(key,map.getOrDefault(key,0)+1);
            }
            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            for (Map.Entry<Integer, Integer> node : entries) {
                //注意要把i节点自己也算在内
                int s = node.getValue()+1;
                if (s > res) {
                    res = s;
                }
            }
        }

        return res;
    }

    public int gcd(int a,int b){
        return b != 0 ? gcd(b,(a%b)) : a;
    }

}
