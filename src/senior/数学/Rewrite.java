package senior.数学;

import java.util.HashMap;
import java.util.Map;

public class Rewrite {
    //最大数
    //其实就是转换成排序问题?
    /*Map<Integer,Node> map;
    public String largestNumber(int[] nums) {
        int n = nums.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            compute(nums[i]);
            update(nums,i);
        }
        if(nums[0] == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >-1 ; i--) {
            sb.append(nums[0]);
            nums[0] = nums[i];
            shift(nums,0,i);
        }

        return sb.toString();
    }
    //返回0是相等，正数是大于，负数是小于
    *//*public int compare(int[] nums,int i,int j){
        Node node1 = map.get(nums[i]);
        Node node2 = map.get(nums[j]);
        if(node1.max != node2.max)
            return node1.max - node2.max;

        //当数字相同时，越短的排在越前面
        if(node1.len != node2.len)
            return node2.len - node1.len;

        for (int k = node1.len-2; k > -1; k--) {
            int temp = node1.bits[k] - node2.bits[k];
            if(temp != 0)
                return temp;
        }
        return 0;
    }*//*
    //仍然是失败的
    *//*public int compare(int[] nums,int i,int j){
        Node node1 = map.get(nums[i]);
        Node node2 = map.get(nums[j]);
        if(node1.max != node2.max)
            return node1.max - node2.max;

        int k,l;
        for ( k = node1.len-1,l = node2.len-1; k > -1 && l > -1; k--,l--) {
            int temp = node1.bits[k] - node2.bits[l];
            if(temp != 0)
                return temp;
        }
        if(k != -1)
            return node1.bits[k]-node1.bits[k+1];
        if(l != -1)
            return node2.bits[l+1]-node2.bits[l];
        return 0;
    }*//*
    //就是排序问题，排序的比较算法不用搞得那么复杂
    public int compare(int[] nums,int i,int j){
        int t1 = 10,t2 = 10;
        while (t1 <= nums[i]) {
            t1 *= 10;
        }
        while (t2 <= nums[j]) {
            t2 *= 10;
        }
        return (nums[i]*t2)+nums[j]-nums[i]-(nums[j]*t1);
    }

    class Node{
        int max,len; //当前数字的首位元素和十进制数的位数
        int[] bits; //十进制
    }
    public void compute(int key){
        int value = key;
        Node node = map.get(value);
        if(node != null)
            return;
        int max = 0,len = 0;
        node = new Node();
        node.bits = new int[10];
        while (value > 0) {
            node.bits[len] = value % 10;
            max = Math.max(max,node.bits[len]);
            len++;
            value /= 10;
        }
        node.max = max;
        node.len = len;
        map.put(key,node);
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void shift(int[] nums,int loc,int n){
        int i = loc,j = (i<<1)+1;
        while (j < n) {
            if(j < n-1 && compare(nums,j,j+1) < 0)
                j++;
            if (compare(nums,i,j) < 0) {
                swap(nums,i,j);
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public void update(int[] nums,int loc) {
        int i = loc,p = (i-1) >> 1;
        while (i > 0) {
            if (compare(nums,i,p) > 0) {
                swap(nums,i,p);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }*/


    //核心在于排序的比较算法，重新写一遍
    public String largestNumber(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            update(nums,i);
        }
        if(nums[0] == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >-1 ; i--) {
            sb.append(nums[0]);
            nums[0] = nums[i];
            shift(nums,0,i);
        }

        return sb.toString();
    }

    //核心点在于此
    public int compare(int[] nums,int i,int j){
        int l1 = 10,l2 = 10,x = nums[i],y = nums[j];

        while (l1 <= x) {
            l1 *= 10;
        }
        while (l2 <= y) {
            l2 *= 10;
        }

        return (x*l2)+y-(y*l1)-x;
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void shift(int[] nums,int loc,int n){
        int i = loc,j = (i<<1)+1;
        while (j < n) {
            if(j < n-1 && compare(nums,j,j+1) < 0)
                j++;
            if (compare(nums,i,j) < 0) {
                swap(nums,i,j);
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public void update(int[] nums,int loc) {
        int i = loc,p = (i-1) >> 1;
        while (i > 0) {
            if (compare(nums,i,p) > 0) {
                swap(nums,i,p);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }



    //直线上最多的点数
    //解法：根据两点式构造直线方程式，其实就是暴力解法
    //成功，注意符号，因为这个卡了半天，76ms
    /*public int maxPoints(int[][] points) {
        //使用一个字符串代表一条直线，y = kx+b，其中key等于"k,b"
        Map<String,Integer> map = new HashMap<>();
        int res = 1;

        for (int i = 1; i < points.length; i++) {
            double x1 = points[i][0], y1 = points[i][1];
            Set<String> set = new HashSet<>();
            for (int j = 0; j < i; j++) {
                StringBuilder sb = new StringBuilder();
                double x2 = points[j][0], y2 = points[j][1];
                //直线的特殊情况，横坐标相同时
                if (x1 == x2) {
                    sb.append(x1);
                }
                //按照一般公式计算直线的表达式
                else{
                    double k = (y1-y2)/(x1-x2),b = -x2 * k + y2; //计算b的时候少了一个符号，导致我卡了半天
                    if(k == 0)
                        k = Math.abs(k);
                    sb.append(k).append(',').append(b);
                }
                String str = sb.toString();

                if(set.contains(str))
                    continue;

                Integer val = map.get(str);
                set.add(str);
                if (val == null) {
                    val = 1;
                }
                map.put(str, ++val);
                if (val > res) {
                    res = val;
                }
            }
        }

        return res;
    }*/


    /**
     * 其实仔细思考一下，也就是第一种解法的改进版？
     * 其实只要通过一个点判断其他点是否在同一直线上就可以了
     */
    //想一下有没有别的解法
    //似乎并没有优化的解法，基本上都是暴力解法
    //官方题解的解法，有很多细节需要注意
    public int maxPoints(int[][] points){
        int res = 1,n = points.length;
        if(n <=2)
            return n;

        for (int i = 0; i < n; i++) {
            //当前获得答案大于剩下的点数，没必要再遍历了
            if(res >= n-i || res > n/2)
                break;
            Map<Integer,Integer> map = new HashMap<>();
            int x1 = points[i][0],y1 = points[i][1];
            for (int j = i+1; j < n; j++) {
                int x2 = points[j][0],y2 = points[j][1],mx = x1-x2,my = y1-y2;
                //考虑因为负号导致结果不相同的情况
                if (my < 0) {
                    my = -my;
                    mx = -mx;
                }
                //考虑因为0值导致结果出错的情况
                if(mx == 0)
                    my = 1;
                if(my == 0)
                    mx = 1;
                int gcd = gcd(Math.abs(mx), Math.abs(my));
                mx /= gcd;
                my /= gcd;
                int key = mx*20001+my,val = map.getOrDefault(key,0)+1;
                map.put(key,val);

                if(val > res)
                    res = val;
            }
        }

        return res;
    }
    public int gcd(int x,int y){
        int temp;
        while (y != 0) {
            temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
}
