package contest.year2022.m9.m9d17;

public class No4 {
    /**
     *     完成所有交易的初始最小钱数
     */

    //失败
    /*public long minimumMoney(int[][] transactions) {
        Arrays.sort(transactions,(x,y) -> {
            int a = x[0] - x[1],b = y[0] - y[1];
            return a - b;
        });

        long res = 0,temp = 0;

        for (int i = 0; i < transactions.length; i++) {
            System.out.println();
            res += (transactions[i][0] - temp);
            temp = transactions[i][1];
        }

        return res;
    }*/


    //失败，排序的策略不能起效果
    /*public long minimumMoney(int[][] transactions) {
        Arrays.sort(transactions,(x,y) -> {
            if (x[1] == y[1]) {
                return x[0] - y[0];
            }
            return x[1] - y[1];
        });

        long res = 0,temp = 0;

        for (int i = 0; i < transactions.length; i++) {
            res += (transactions[i][0] - temp);
            temp = transactions[i][1];
        }

        return res;
    }*/


    //思想：
    // ①对于所有的cost <= cashback 情况来说，我们只需要找到cost 中最大的数
    //量，并且满足它的大小，就可以完成任意顺序的交易。
    // ②而对于 cost > cashback 的情况来说，我们亏掉的总数额是sum(cost-cashback)，
    //而为了满足任意顺序的初始时金额，我们需要在这一部分找出cashback中最大的值，因为
    //最大的cashback满足cost-cashback + max(cashback) >= cost，这样每一个交
    //易都可以满足初始金额了。

    /*public long minimumMoney(int[][] transactions){
        long max1 = 0,sum = 0,max2 = 0;
        int n = transactions.length;
        for (int i = 0; i < n; i++) {
            if (transactions[i][0] > transactions[i][1]) {
                sum += transactions[i][0] - transactions[i][1];
                max1 = Math.max(max1,transactions[i][1]);
            }else
                max2 = Math.max(max2,transactions[i][0]);
        }
        return sum + Math.max(max1,max2);
    }*/


    public long minimumMoney(int[][] transactions){
        long max = 0,temp = 0;
        for (int i = 0; i < transactions.length; i++) {
            temp += Math.max(transactions[i][0] - transactions[i][1],0);
            max = Math.max(max,Math.min(transactions[i][0],transactions[i][1]));
        }
        return temp + max;
    }
}
