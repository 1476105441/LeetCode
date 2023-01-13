package contest.year2023.m1.d7;

public class No4 {
    //前缀和+差分数组+贪心+二分
    //必须注意细节！全部数据都要使用long类型
    //26ms
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        //1、使用前缀和计算出每个电厂应该有的电力
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stations[i];
        }
        long[] power = new long[n];
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            power[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(0, i - r)];
            min = Math.min(min, power[i]);
        }
        //2、二分找到最小值最大的情况
        long left = min, right = min + k, res = min;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (check(power, r, k, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean check(long[] power, int r, int k, long now) {
        int n = power.length;
        long sum = 0, need = 0;
        long[] diff = new long[n];
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            long x = now - power[i] - sum;
            if (x > 0) {
                need += x;
                sum += x;
                if (need > k) {
                    return false;
                }
                if (i + 2 * r + 1 < n) {
                    diff[i + 2 * r + 1] -= x;
                }
            }
        }
        return true;
    }
}
