package contest.year2023.m4.d29;

/**
 * 找到两个数组的前缀公共数组
 *
 * @author wjs 2023/6/26
 */
public class No2 {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        int[] count = new int[n+1];
        int c = 0;
        for(int i=0;i < n;i++) {
            count[A[i]]++;
            count[B[i]]--;
            if(A[i] == B[i]) {
                c++;
            } else {
                if(count[A[i]] <= 0) {
                    c++;
                }
                if(count[B[i]] >= 0) {
                    c++;
                }
            }
            res[i] = c;
        }
        return res;
    }
}
