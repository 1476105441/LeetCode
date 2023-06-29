package contest.year2023.m4.d29;

import java.util.Arrays;

/**
 * 将数组清空
 *
 * @author wjs 2023/6/26
 */
public class No4 {
    //树状数组能解，已经忘了树状数组怎么写了
    //成功，62ms
    /*int[] tree;
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length,k = 0;
        tree = new int[n+1];
        Integer[] arr = new Integer[n];
        for(int i=0;i < n;i++) {
            arr[i] = i;
        }
        Arrays.sort(arr,(x, y) -> nums[x] - nums[y]);

        int pre = 1;
        long res = n;
        for(int i=0;i < n;i++) {
            int v = arr[i]+1;
            if(v >= pre) {
                res += v-pre-count(pre,v);
            } else {
                res += n-pre+v-k+count(v,pre-1);
            }
            k++;
            update(v);
            pre = v;
        }
        //for(int i=0;i < tree.length;i++) System.out.println(tree[i]);
        return res;
    }
    //计算区间中被删除的元素个数
    private int count(int l, int r) {
        return getVal(r) - getVal(l-1);
    }
    private void update(int loc) {
        int n = tree.length;
        while(loc < n) {
            tree[loc]++;
            loc += lowbit(loc);
        }
    }
    private int getVal(int loc) {
        int res = 0;
        while(loc > 0) {
            res += tree[loc];
            loc -= lowbit(loc);
        }
        return res;
    }
    private int lowbit(int loc) {
        return loc & -loc;
    }*/

    //重写一遍，加深印象
    int[] tree;
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        long res = n;
        Integer[] indexs = new Integer[n];
        tree = new int[n+1];
        for(int i=0;i < n;i++) {
            indexs[i] = i;
        }
        Arrays.sort(indexs,(x,y) -> nums[x] - nums[y]);
        int pre = 1;
        for(int i=0;i < n;i++) {
            int v = indexs[i]+1;
            if(v >= pre) {
                res += v-pre-count(pre,v);
            } else {
                res += n-pre+v-i+count(v,pre-1);
            }
            update(v);
            pre = v;
        }
        return res;
    }

    private int count(int l, int r) {
        return getVal(r) - getVal(l-1);
    }
    private void update(int loc) {
        while(loc < tree.length) {
            tree[loc]++;
            loc += lowbit(loc);
        }
    }
    private int getVal(int loc) {
        int res = 0;
        while(loc > 0) {
            res += tree[loc];
            loc -= lowbit(loc);
        }
        return res;
    }
    private int lowbit(int loc) {
        return loc & -loc;
    }
}
