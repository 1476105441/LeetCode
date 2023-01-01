package contest.year2022.m10.m10d1;

import java.util.Arrays;

public class No4 {
    /**
     * 满足不等式的数对数目
     */

    //预计会超时
    /*public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        long res = 0;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[i] = nums1[i] - nums2[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(temp[i] - temp[j] <= diff)
                    res++;
            }
        }

        return res;
    }*/


    //超时
    /*public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        long res = 0;
        int[] temp = new int[n],set = new int[60001];

        for (int i = 0; i < n; i++) {
            temp[i] = nums1[i] - nums2[i];
        }

        set[temp[n-1]+30000]++;
        for (int i = n-2; i >= 0; i--) {
            int val = temp[i] - diff + 30000;
            for (int j = val; j < 60001; j++) {
                res += set[j];
            }
            set[temp[i]+30000]++;
        }

        return res;
    }*/


    //似乎不应该用线段树？
    /*Node root;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        root = new Node();
        long res = 0;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[i] = nums1[i] - nums2[i];
        }

        update(root,0,60000,temp[n-1]-diff+30000,60001);
        for (int i = n - 2; i >= 0; i--) {
            int val = temp[i] - diff + 30000;
            res += query(root,0,60000,val,60000);
            update(root,0,60000,val,60000);
        }

        return res;
    }

    class Node {
        Node left, right;
        int val, add;
    }

    void update(Node node, int start, int end, int l, int r) {
        if (start >= l && end <= r) {
            node.val++;
            node.add++;
            return;
        }
        int c = start + ((end - start) >> 1);
        pushDown(node, c);
        if (c >= l) update(node.left, start, c, l, r);
        if (c < r) update(node.right, c + 1, end, l, r);
        node.val = node.left.val + node.right.val;
    }

    int query(Node node, int start, int end, int l, int r) {
        if (start >= l && end <= r) {
            return node.val;
        }
        int c = start + ((end - start) >> 1);
        int res = 0;
        pushDown(node, c);
        if (c >= l) res = query(node.left, start, c, l, r);
        if (c < r) res = res + query(node.right, c + 1, end, l, r);
        return res;
    }

    void pushDown(Node node, int c) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;
        node.right.val += node.add;
        node.left.val += node.add;
        node.right.add = node.add;
        node.left.add = node.add;
        node.add = 0;
    }*/


    //线段树，82ms
    /*Node root;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        root = new Node();
        long res = 0;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[i] = nums1[i] - nums2[i];
        }

        update(root,0,60000,temp[n-1]+30000,temp[n-1]+30000);
        for (int i = n - 2; i >= 0; i--) {
            int val = temp[i] - diff + 30000;
            res += query(root,0,60000,val,60000);
            update(root,0,60000,val+diff,val+diff);
        }

        return res;
    }

    class Node {
        Node left, right;
        int val, add;
    }

    void update(Node node, int start, int end, int l, int r) {
        if (start >= l && end <= r) {
            node.val++;
            node.add++;
            return;
        }
        int c = start + ((end - start) >> 1);
        pushDown(node);
        if (c >= l) update(node.left, start, c, l, r);
        if (c < r) update(node.right, c + 1, end, l, r);
        node.val = node.left.val + node.right.val;
    }

    int query(Node node, int start, int end, int l, int r) {
        if (start >= l && end <= r) {
            return node.val;
        }
        int c = start + ((end - start) >> 1);
        int res = 0;
        pushDown(node);
        if (c >= l) res = query(node.left, start, c, l, r);
        if (c < r) res = res + query(node.right, c + 1, end, l, r);
        return res;
    }

    void pushDown(Node node) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;
        node.right.val += node.add;
        node.left.val += node.add;
        node.right.add = node.add;
        node.left.add = node.add;
        node.add = 0;
    }*/

    //统计逆序对，为什么不对？问题是否出在排序不是按照第二个大小来排的？
    /*long res;
    int[] temp1,temp2,temp3,temp4;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff){
        int n = nums1.length;
        temp1 = new int[n];
        temp2 = new int[n];
        temp3 = new int[n];
        temp4 = new int[n];
        for (int i = 0; i < n; i++) {
            temp1[i] = nums1[i]-nums2[i];
            temp2[i] = temp1[i]+diff;
        }
        merge(0,n-1);
        return res;
    }
    public void merge(int l,int r){
        if(l >= r)
            return;

        int c = l + ((r-l)>>1);
        merge(l,c);
        merge(c+1,r);
        int i = l,j = c+1,k = l;
        while (i <= c && j <= r) {
            if (temp1[i] > temp2[j]) {
                temp3[k] = temp1[i];
                temp4[k] = temp2[i];
                res += j-c-1;
                i++;
            }else{
                temp3[k] = temp1[j];
                temp4[k] = temp2[j];
                j++;
            }
            k++;
        }
        int len = r-c;
        while (i <= c) {
            temp3[k] = temp1[i];
            temp4[k] = temp2[i];
            res += len;
            i++;
            k++;
        }
        while (j <= r) {
            temp3[k] = temp1[j];
            temp4[k] = temp2[j];
            j++;
            k++;
        }

        i = l;
        while (i <= r) {
            temp1[i] = temp3[i];
            temp2[i] = temp4[i];
            i++;
        }
    }*/

    //重新写一遍线段树解法，86ms
    /*public long numberOfPairs(int[] nums1,int[] nums2,int diff){
        long res = 0;
        int n = nums1.length;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[i] = nums1[i] - nums2[i] + 30000;
        }

        Node root = new Node();
        update(root,0,60000,temp[n-1],temp[n-1]);
        for (int i = n-2; i >= 0; i--) {
            res += query(root,0,60000,temp[i]-diff,60000);
            //后更新，不然会出问题
            update(root,0,60000,temp[i],temp[i]);
        }

        return res;
    }
    class Node{
        int val,add;
        Node left,right;
    }
    public int query(Node node,int l,int r,int start,int end){
        if (l >= start && r <= end) {
            return node.val;
        }
        pushDown(node);
        int c = l + ((r-l) >> 1),res = 0;
        if(c >= start) res = query(node.left,l,c,start,end);
        if(c < end) res += query(node.right,c+1,r,start,end);

        return res;
    }
    public void update(Node node,int l,int r,int start,int end){
        if (l >= start && r <= end) {
            node.val += (l-r+1);
            node.add++;
            return;
        }
        pushDown(node);

        int c = l + ((r-l) >> 1);
        if(c >= start) update(node.left,l,c,start,end);
        if(c < end) update(node.right,c+1,r,start,end);
        node.val = node.right.val + node.left.val;
    }
    public void pushDown(Node node){
        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();
        if(node.add == 0) return;
        node.left.val += node.add;
        node.right.val += node.add;
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }*/

    //使用树状数组，需要进行离散化
    int[] c,temp;
    //一开始想要离散化，但是那样是错的，因为离散化后本来应该有的元素没有了，导致结果肯定不正确
    public long numberOfPairs(int[] nums1,int[] nums2,int diff){
        long res = 0;
        int n = nums1.length;

        temp = new int[n];
        int l = 0;
        int[] temp2 = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = nums1[i] - nums2[i];
            temp2[i] = temp[i];
        }
        c = new int[n+1];

        Arrays.sort(temp);

        for (int i = 0; i < n; i++) {
            int loc = find(temp2[i]+diff);
            res += query(loc);
            update(find(temp2[i]));
        }
        return res;
    }
    //我找的是第一个比当前元素小的值，这个查找还有一些问题
    /*public int find(int val){
        int res = 0,l = 0,r = temp.length-1;
        while (l <= r) {
            int c = l + ((r-l)>>1);
            if (temp[c] > val) {
                r = c-1;
            } else{
                res = c;
                l = c+1;
            }
        }
        return res+1;
    }*/
    //修改上面我自己犯的错误，因为在找不到合适的位置时（即小于所有元素），本应该返回索引为0，也
    //就是不做增加或者减小，但是我返回的确是索引1，导致会加上第一个元素的数量
    public int find(int val){
        int res = -1,l = 0,r = temp.length-1;
        while (l <= r) {
            int c = l + ((r-l)>>1);
            if (temp[c] > val) {
                r = c-1;
            } else{
                res = c;
                l = c+1;
            }
        }
        return res+1;
    }
    /*public int find(int val){
        int l = 0,r = temp.length;
        while (l < r) {
            int c = l+((r-l)>>1);
            if(temp[c] < val) l = c+1;
            else r = c;
        }
        return l;
    }*/
    public void update(int loc){
        while (loc < c.length) {
            c[loc]++;
            loc += lowBit(loc);
        }
    }
    public int query(int loc){
        int res = 0;
        while (loc > 0) {
            res += c[loc];
            loc -= lowBit(loc);
        }
        return res;
    }
    public int lowBit(int x){
        return x & (-x);
    }

    /*public long numberOfPairs(int[] nums1,int[] nums2,int diff){
        int n = nums1.length;
        long res = 0;
        temp = new int[n];
        c = new int[n+1];
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
            temp[i] = nums1[i];
        }
        Arrays.sort(temp);

        for (int i = 0; i < n; i++) {
            res += query(find(nums1[i]+diff+1));
            update(find(nums1[i])+1);
        }
        return res;
    }*/
}
