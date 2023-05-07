package contest.year2023.m4.d9;

import java.util.HashSet;
import java.util.Set;

public class No1 {
    // 2614、对角线上的质数

    //解法一：埃式筛，效率堪忧，44ms
    /*static Set<Integer> primes;
    static {
        primes = new HashSet<>();
        boolean[] set = new boolean[4000001];
        for(int i=2;i < 4000001;i++){
            if(!set[i]){
                primes.add(i);
                for(int j=i;j < 4000001/i;j++){
                    set[i*j] = true;
                }
            }
        }
    }
    private boolean isPrime(int val){
        return primes.contains(val);
    }
    public int diagonalPrime(int[][] nums) {
        int res = 0,n = nums.length;
        //System.out.println(n);
        int i1=0,j1=0,i2=0,j2=n-1,c=0;
        while(c < n){
            if(isPrime(nums[i1][j1])){
                //System.out.println(nums[i1][j1]);
                res = Math.max(res,nums[i1][j1]);
            }
            if(isPrime(nums[i2][j2])){
                //System.out.println(nums[i2][j2]);
                res = Math.max(res,nums[i2][j2]);
            }
            i1++;
            j1++;
            i2++;
            j2--;
            c++;
        }
        return res;
    }*/

    //解法二：暴力判断，4ms
    /*public int diagonalPrime(int[][] nums) {
        int res = 0,n = nums.length;
        int i1=0,j1=0,i2=0,j2=n-1,c=0;
        while(c < n){
            if(isPrime(nums[i1][j1])){
                res = Math.max(res,nums[i1][j1]);
            }
            if(isPrime(nums[i2][j2])){
                res = Math.max(res,nums[i2][j2]);
            }
            i1++;j1++;
            i2++;j2--;
            c++;
        }
        return res;
    }
    private boolean isPrime(int val){
        if(val == 1){ return false; }
        for(int i=2;i * i <= val;i++){
            if(val % i == 0){
                return false;
            }
        }
        return true;
    }*/

    //这种写法效率最高，0ms，原因在于筛选掉了不必要的质数判断
    public int diagonalPrime(int[][] nums) {
        int res = 0,n = nums.length;
        int c=0;
        while(c < n){
            int val = nums[c][c];
            if(val > res && isPrime(val)){
                res = val;
            }
            val = nums[c][n-1-c];
            if(val > res && isPrime(val)){
                res = val;
            }
            c++;
        }
        return res;
    }
    private boolean isPrime(int val){
        if(val == 1){ return false; }
        for(int i=2;i * i <= val;i++){
            if(val % i == 0){
                return false;
            }
        }
        return true;
    }
}
