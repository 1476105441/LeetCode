package contest.year2023.m2.d26;

public class No2 {
    //思维题
    //突破点在于求余之后不改变整个数字能否整除m的特性
    public int[] divisibilityArray(String word, int m) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int[] res = new int[n];
        long temp = 0;
        for(int i = 0;i < n;i++){
            int val = chars[i] - '0';
            temp = (temp * 10 + val) % m;
            if(temp == 0){
                res[i] = 1;
            }
        }
        return res;
    }
}
