package contest.year2023.m1.d1;

public class No3 {
    public int minimumPartition(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length,res = 1;
        long pre = chars[0] - '0';
        for (int i = 1; i < n; i++) {
            int val = chars[i] - '0';//当前元素的值
            long temp = pre * 10 + val;
            if(val > k) return -1;
            if (temp <= k){
                pre = temp;
            } else{
                res++;
                pre = val;
            }
        }
        return res;
    }
}
