package contest.year2023.m5.d27;

import java.util.Arrays;

/**
 * 2709.最大公约数遍历
 *
 * @author wjs 2023/9/18
 */
public class No4 {

    //失败，考虑的不全面
    //并查集，从大往小遍历
    /*private int[] parent;

    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        parent = new int[n];
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = n-1; i > 0; i--) {
            int x = findParent(i), y = findParent(i-1);
            int xv = nums[x], yv = nums[y];
            if (gcd(xv, yv) == 1) {
                return false;
            }
            parent[y] = x;
        }
        return true;
    }

    private int findParent(int loc) {
        if (loc == parent[loc]) {
            return loc;
        }
        parent[loc] = findParent(parent[loc]);
        return parent[loc];
    }

    private int gcd(int x,int y) {
        while(y != 0) {
            int tmp = x % y;
            x = y;
            y = tmp;
        }
        return x;
    }*/
}
