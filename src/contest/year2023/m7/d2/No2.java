package contest.year2023.m7.d2;

import java.util.ArrayList;
import java.util.List;

/**
 * 2761. 和等于目标值的质数对
 *
 * @author wjs 2023/7/4
 */
public class No2 {
    boolean[] notPrime;
    public List<List<Integer>> findPrimePairs(int n) {
        notPrime = new boolean[n+1];
        countPrime(n);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=2;i < n;i++) {
            if(!notPrime[i]) {
                int y = n-i;
                if(!notPrime[y]) {
                    List<Integer> tmp = new ArrayList<>();
                    if(y >= i) {
                        tmp.add(i);
                        tmp.add(y);
                        res.add(tmp);
                    } else {
                        break;
                    }
                }
            }
        }
        return res;
    }
    private void countPrime(int n) {
        notPrime[0] = true;
        notPrime[1] = true;
        for(int i=2;i*i <= n;i++) {
            if(!notPrime[i]){
                for(int j=i;i * j <= n;j++) {
                    notPrime[i*j] = true;
                }
            }
        }
    }
}
