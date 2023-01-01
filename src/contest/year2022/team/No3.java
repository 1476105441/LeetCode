package contest.year2022.team;

public class No3 {
    //用公式计算会重复
    //成功，只要减去剩余的那部分就可以去重了
    public int beautifulBouquet(int[] flowers, int cnt) {
        int n = flowers.length,mod = 1000000007;
        long res = 0;
        if(cnt > n)
            return (n * (n+1) /2) % mod;

        int[] temp = new int[100000];
        int l = 0,r = 0;
        while (r < n) {
            temp[flowers[r]]++;
            if(temp[flowers[r]] > cnt){
                res += (r-l)*(r-l+1)/2 % mod;
                while (temp[flowers[r]] > cnt) {
                    temp[flowers[l]]--;
                    l++;
                }
                res -= (r-l)*(r-l+1)/2 % mod;
            }
            r++;
        }
        if(l < r)
            res += (r-l)*(r-l+1)/2 % mod;

        return (int) res;
    }


    //滑动窗口
    /*public int beautifulBouquet(int[] flowers, int cnt){
        int n = flowers.length,MOD = 1000000007,l = 0,r = 0;
        long res = 0;
        int[] bucket = new int[100001];

        while (r < n) {

        }
    }*/
}
