package contest.year2022.m9.m9d11;

public class No4 {
    /**
     *      最长递增子序列 II
     */

    //超时
    /*public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length,min,max = 1;
        int[] dp = new int[n];

        dp[0] = 1;
        min = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            if (min >= nums[i]) {
                min = nums[i];
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && nums[i] - nums[j] <= k) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(dp[i],max);
        }

        return max;
    }*/

    //如何优化？总时间超出了
    /*public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length,min,max = 1;
        int[] dp = new int[100001];

        dp[nums[0]] = 1;
        min = nums[0];
        for (int i = 1; i < n; i++) {
            int temp = 1;
            if (min >= nums[i]) {
                min = nums[i];
                dp[nums[i]] = Math.max(dp[nums[i]],temp);
                continue;
            }
            if(k < i){
                for (int j = Math.max(nums[i]-k,0); j < nums[i]; j++) {
                    temp = Math.max(temp,dp[j]+1);
                }
            }else{
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && nums[i] - nums[j] <= k) {
                        temp = Math.max(temp,dp[nums[j]] + 1);
                    }
                }
            }
            dp[nums[i]] = Math.max(temp,dp[nums[i]]);
            max = Math.max(dp[nums[i]],max);
        }

        return max;
    }*/

    /*public int lengthOfLIS(int[] nums, int k){
        int n = nums.length,len;
        int[] dp = new int[n];
        dp[0] = nums[0];
        len = 0;

        for (int i = 1; i < n; ++i) {
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                int l = 1, r = len, loc = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (dp[mid] < nums[i]) {
                        loc = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }

                dp[loc + 1] = nums[i];
            }
        }

        return len;
    }*/

    //使用数组的线段树搞不明白
    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length,N = 100000,res = 0;
        Node root = new Node();
        for(int i = 0;i < n;i++){
            int temp = query(root,0,N,Math.max(0,nums[i]-k),nums[i]-1)+1;
            update(root,0,N,nums[i],nums[i],temp);
            res = Math.max(res,temp);
        }
        return res;
    }
    class Node{
        Node left,right;
        int val,add;
    }
    public void update(Node node,int start,int end,int l,int r,int val){
        if(start >= l && end <= r){
            node.val = val;
            node.add = val;
            return;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        if(c >= l){
            update(node.left,start,c,l,r,val);
        }
        if(c < r){
            update(node.right,c+1,end,l,r,val);
        }
        node.val = Math.max(node.left.val,node.right.val);
    }
    public int query(Node node,int start,int end,int l,int r){
        if(start >= l && end <= r){
            return node.val;
        }
        pushDown(node);
        int c = start+((end-start)>>1),res = 0;
        if(c >= l){
            res = query(node.left,start,c,l,r);
        }
        if(c < r){
            res = Math.max(query(node.right,c+1,end,l,r),res);
        }
        return res;
    }
    public void pushDown(Node node){
        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();
        if(node.add == 0) return;
        node.left.val = node.add;
        node.left.add = node.add;
        node.right.val = node.add;
        node.right.add = node.add;
        node.add = 0;
    }
}
