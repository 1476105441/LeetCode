package junior.动态规划;

public class LeetCode70 {
    //思想：第n个台阶只能从第n-1或者第n-2个台阶走上来，进而转换成f[n]=f[n-1]+f[n-2]
    public static int climbStairs(int n){
        //f[]数组存放的是第i个台阶的方法数
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < n; i++) {
            f[i] = f[i-1] + f[i-2] ;
        }
        return f[n-1];
    }

    public static void main(String[] args) {
        /*System.out.println(climbStairs(5))*/
        LeetCode53.main(new String[10]);
    }
}
