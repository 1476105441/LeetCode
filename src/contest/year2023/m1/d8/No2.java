package contest.year2023.m1.d8;

public class No2 {
    public long maxKelements(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = nums[i];
            update(tmp,i);
        }
        for (int i = 0; i < k; i++) {
            res += tmp[0];
            tmp[0] = (tmp[0] + 2) / 3;
            shift(tmp,0,n);
        }
        return res;
    }

    public void shift(int[] tmp, int start, int n) {
        int i = start, j = (i << 1) + 1;
        while (j < n) {
            if (j + 1 < n && tmp[j] < tmp[j + 1]) {
                j++;
            }
            if (tmp[j] > tmp[i]) {
                int temp = tmp[j];
                tmp[j] = tmp[i];
                tmp[i] = temp;
                i = j;
                j = (i << 1) + 1;
            } else {
                break;
            }
        }
    }
    public void update(int[] tmp,int loc){
        int i = loc,p = (i-1) >> 1;
        while (i > 0) {
            if (tmp[i] > tmp[p]) {
                int temp = tmp[i];
                tmp[i] = tmp[p];
                tmp[p] = temp;
                i = p;
                p = (i - 1) >> 1;
            } else {
                break;
            }
        }
    }
}
