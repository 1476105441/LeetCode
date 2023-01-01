package contest.year2022.m8.m8d20;

public class No1 {
    /**
     *      得到 K 个黑块的最少涂色次数
     */

    /*char[] c;
    int res,k;
    public int minimumRecolors(String blocks, int k) {
        c = blocks.toCharArray();
        this.k = k;
        res = 2147483647;
        find(0,0,0);

        return res;
    }

    public void find(int count,int temp,int loc){
        if (count == k) {
            if(temp < res)
                res = temp;
            return;
        }
        if (loc == c.length) {
            return;
        }
        if (c[loc] == 'B') {
            find(count+1,temp,loc+1);
        }else{
            find(count+1,temp+1,loc+1);
            find(0,0,loc+1);
        }
    }*/

    /*public int minimumRecolors(String blocks, int k){
        char[] c = blocks.toCharArray();
        int res,n = c.length,count = 0;

        for (int i = 0; i < k; i++) {
            if (c[i] == 'W') {
                count++;
            }
        }
        int l = 1,r = k;
        res = count;

        while (r < n) {
            if (c[l - 1] == 'W' && c[r] == 'B') {
                count--;
            } else if (c[l - 1] == 'B' && c[r] == 'W') {
                count++;
            }
            if (count < res) {
                res = count;
            }
            r++;
            l++;
        }

        return res;
    }*/

}
