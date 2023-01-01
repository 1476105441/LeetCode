package senior.树和图;

import java.util.*;

public class No9 {
    /**
     *          计算右侧小于当前元素的个数
     * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。
     * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     * 提示：
     *     1 <= nums.length <= 105
     *     -104 <= nums[i] <= 104
     */

    /**
     * 第一种解法：归并排序
     * 需要用到之前写的题目的思路，也就是
     * 数组中的逆序对问题的思路，所以先复
     * 习数组中的逆序对问题
     */

    //求逆序对
    /*int[] temp;
    int res;
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        res = 0;
        recursion(nums,0,nums.length-1);
        return res;
    }

    public void recursion(int[] nums,int start,int end){
        if (start >= end) {
            return;
        }
        int center = start + (end - start)/2;
        recursion(nums,start,center);
        recursion(nums,center+1,end);
        merge(start,center,end,nums);
        for (int i = start; i <= end; i++) {
            nums[i] = temp[i];
        }
    }
    public void merge(int start,int center,int end,int[] nums){
        int i = start,j = center+1,loc = start;
        while (i <= center && j <= end) {
            if (nums[i] > nums[j]) {
                temp[loc++] = nums[j];
                j++;
            }else{
                res += j - center - 1;
                temp[loc++] = nums[i];
                i++;
            }
        }
        //前半部分中还有元素没有处理完，说明此部分剩下的所有元素都比后半部分的大
        int len = end - center;
        while (i <= center) {
            temp[loc++] = nums[i++];
            res += len;
        }
        while (j <= end) {
            temp[loc++] = nums[j++];
        }
    }*/

    //求每个元素右侧小于它的个数不就是求由它构成的逆序对吗？
    //成功，最快能够击败百分之80
    /*int[] res;
    int[] loc;
    int[] temp1;
    int[] temp2;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        res = new int[nums.length];
        loc = new int[nums.length];
        temp1 = new int[nums.length];
        temp2 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            loc[i] = i;
        }
        recursion(nums,0,nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            list.add(res[i]);
        }
        return list;
    }

    public void recursion(int[] nums,int start,int end){
        if (end <= start) {
            return;
        }
        int center = start+(end-start)/2;
        recursion(nums,start,center);
        recursion(nums,center+1,end);
        merge(nums,start,center,end);
        for (int i = start; i <= end; i++) {
            nums[i] = temp1[i];
            loc[i] = temp2[i];
        }
    }

    public void merge(int[] nums,int start,int center,int end){
        int i = start,j = center+1,k = start,len;
        while (i <= center && j <= end) {
            if (nums[i] > nums[j]) {
                temp1[k] = nums[j];
                temp2[k++] = loc[j++];
            }else{
                res[loc[i]] += j-center-1;
                temp1[k] = nums[i];
                temp2[k++] = loc[i++];
            }
        }
        len = end - center;
        while (i <= center) {
            res[loc[i]] += len;
            temp1[k] = nums[i];
            temp2[k++] = loc[i++];
        }
        while (j <= end) {
            temp1[k] = nums[j];
            temp2[k++] = loc[j++];
        }
    }*/


    /**
     * 第二种解法：离散化树状数组
     * 开始要准备的条件：
     *   使数组中的元素按照大小（去重）排成一个序
     *   列，新序列每个元素有其相对应的值，这个值
     *   是用来统计原序列中该元素出现的次数。
     * 思想：
     *   从后往前遍历原数组，遍历到当前元素时，将
     *   其对应的新序列位置上的值加一，然后统计新
     *   序列中在它之前的元素值之和，这个结果就是
     *   当前元素右侧比它小的元素个数。
     * 具体实现：
     *   先使用哈希表去重，然后将去重之后的数组排
     *   序，再使用哈希表记录排序后的元素值和对应
     *   的下标，维护一个与去重后元素个数相同的数
     *   组，用来存放遍历到的元素个数。
     */

    //2112ms，这也能过
    /*public List<Integer> countSmaller(int[] nums){
        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        int[] val,res = new int[nums.length];
        int k = 0;
        List<Integer> list = new ArrayList<>();
        //1、去重
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        //2、排序
        val = new int[set.size()];
        for (int num : set){
            val[k++] = num;
        }
        merge(val,val.length-1);
        //3、维护计数数组
        for (int i = 0; i < val.length; i++) {
            map.put(val[i],i);
            val[i] = 0;
        }
        //4、从后往前遍历
        for (int i = nums.length-1; i >= 0; i--) {
            int loc = map.get(nums[i]),count = 0;
            val[loc]++;
            for (int j = loc-1; j >= 0; j--) {
                count += val[j];
            }
            res[i] = count;
        }

        for (int i = 0; i < nums.length; i++) {
            list.add(res[i]);
        }
        return list;
    }

    //写一个堆排序用来进行排序
    public void shift(int[] val,int start,int n){
        int i = start,j = 2*i+1,temp;
        while (j <= n) {
            if(j <= n-1 && val[j+1] > val[j])
                j++;
            if (val[i] < val[j]) {
                temp = val[j];
                val[j] = val[i];
                val[i] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }

    public void merge(int[] val,int n){
        for (int i = (n-1)/2; i >= 0; i--) {
            shift(val,i,n);
        }

        int temp;
        for (int i = n; i > 0; i--) {
            temp = val[i];
            val[i] = val[0];
            val[0] = temp;
            shift(val,0,i-1);
        }
    }*/

    /**
     * 官方题解的写法
      */
    /*//统计元素个数的数组
    private int[] c;
    //用于映射位置的数组
    private int[] a;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultList = new ArrayList<Integer>();
        discretization(nums);
        init(nums.length + 5);
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = getId(nums[i]);
            resultList.add(query(id - 1));
            update(id);
        }
        Collections.reverse(resultList);
        return resultList;
    }

    private void init(int length) {
        c = new int[length];
        Arrays.fill(c, 0);
    }

    //取一个数字的最低二进制位
    private int lowBit(int x) {
        return x & (-x);
    }

    //更新统计元素个数的数组
    private void update(int pos) {
        while (pos < c.length) {
            c[pos] += 1;
            pos += lowBit(pos);
        }
    }

    //查找小于当前元素的元素个数
    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += c[pos];
            pos -= lowBit(pos);
        }

        return ret;
    }

    //去重再排序
    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    private int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }*/

    //使用树状数组，自己重写版本，119ms
    /*int[] count,tmp;
    public List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        int n = nums.length,l = 0;
        for(int i = 0;i < n;i++){
            set.add(nums[i]);
        }
        tmp = new int[set.size()];
        count = new int[tmp.length];
        while(!set.isEmpty()){
            tmp[l++] = set.pollFirst();
        }
        List<Integer> res = new LinkedList<>();

        for(int i = n-1;i >= 0;i--){
            int loc = find(nums[i]);
            update(loc+1);
            res.add(0,sum(loc));
        }

        return res;
    }
    public int find(int num){
        int l = 0,r = tmp.length-1;
        while(l < r){
            int c = l+((r-l)>>1);
            if(tmp[c] > num){
                r = c-1;
            }else if(tmp[c] < num){
                l = c+1;
            }else
                return c;
        }
        return l;
    }
    public int getLowBit(int x){
        return x & -x;
    }
    public void update(int loc){
        int y = loc;
        while(y < count.length){
            count[y]++;
            y = getLowBit(y) + y;
        }
    }
    public int sum(int loc){
        int y = loc,res = 0;
        while(y > 0){
            res += count[y];
            y = y-getLowBit(y);
        }
        return res;
    }*/

    //解法二，求逆序对，归并排序，92ms，不太行啊
    /*int[] numTmp,loc,locTmp;
    List<Integer> res;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        numTmp = new int[n];
        loc = new int[n];
        locTmp = new int[n];
        res = new ArrayList<>(n);
        for(int i = 0;i < n;i++){
            res.add(0);
            loc[i] = i;
        }
        mergeSort(nums,0,n-1);
        return res;
    }
    public void mergeSort(int[] nums,int l,int r){
        if(l == r)
            return;
        int c = l+((r-l)>>1);
        mergeSort(nums,l,c);
        mergeSort(nums,c+1,r);

        int i = l,j = c+1,k = l;
        while(i < c+1 && j <= r){
            if(nums[i] > nums[j]){
                numTmp[k] = nums[j];
                locTmp[k++] = loc[j];
                j++;
            }else{
                numTmp[k] = nums[i];
                locTmp[k++] = loc[i];
                res.set(loc[i],res.get(loc[i])+(j-c-1));
                i++;
            }
        }
        while(i < c+1){
            numTmp[k] = nums[i];
            locTmp[k++] = loc[i];
            res.set(loc[i],res.get(loc[i])+(r-c));
            i++;
        }
        while(j <= r){
            numTmp[k] = nums[j];
            locTmp[k++] = loc[j];
            j++;
        }

        for(int x = l;x <= r;x++){
            nums[x] = numTmp[x];
            loc[x] = locTmp[x];
        }
    }*/

    //换成数组快了很多，59ms
    /*int[] numTmp,loc,locTmp;
    int[] cnt;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        numTmp = new int[n];
        loc = new int[n];
        locTmp = new int[n];
        cnt = new int[n];
        List<Integer> res = new ArrayList<>(n);
        for(int i = 0;i < n;i++){
            loc[i] = i;
        }
        mergeSort(nums,0,n-1);
        for(int i = 0;i < n;i++){
            res.add(cnt[i]);
        }
        return res;
    }
    public void mergeSort(int[] nums,int l,int r){
        if(l == r)
            return;
        int c = l+((r-l)>>1);
        mergeSort(nums,l,c);
        mergeSort(nums,c+1,r);

        int i = l,j = c+1,k = l;
        while(i < c+1 && j <= r){
            if(nums[i] > nums[j]){
                numTmp[k] = nums[j];
                locTmp[k++] = loc[j];
                j++;
            }else{
                numTmp[k] = nums[i];
                locTmp[k++] = loc[i];
                cnt[loc[i]] += j-c-1;
                i++;
            }
        }
        while(i < c+1){
            numTmp[k] = nums[i];
            locTmp[k++] = loc[i];
            cnt[loc[i]] += r-c;
            i++;
        }
        while(j <= r){
            numTmp[k] = nums[j];
            locTmp[k++] = loc[j];
            j++;
        }

        for(int x = l;x <= r;x++){
            nums[x] = numTmp[x];
            loc[x] = locTmp[x];
        }
    }*/

    //再优化，50ms
    /*int[] numTmp,loc,locTmp;
    int[] cnt;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        numTmp = new int[n];
        loc = new int[n];
        locTmp = new int[n];
        cnt = new int[n];
        List<Integer> res = new ArrayList<>(n);
        for(int i = 0;i < n;i++){
            loc[i] = i;
        }
        mergeSort(nums,0,n-1);
        for(int i = 0;i < n;i++){
            res.add(cnt[i]);
        }
        return res;
    }
    public void mergeSort(int[] nums,int l,int r){
        if(l == r)
            return;
        int c = l+((r-l)>>1);
        mergeSort(nums,l,c);
        mergeSort(nums,c+1,r);

        merge(nums,l,c,r);

        for(int x = l;x <= r;x++){
            nums[x] = numTmp[x];
            loc[x] = locTmp[x];
        }
    }
    //将函数拆分开，时间增加了2ms，感觉不是提升的因素
    public void merge(int[] nums,int l,int c,int r){
        int i = l,j = c+1,k = l;
        while(i < c+1 && j <= r){
            if(nums[i] > nums[j]){
                numTmp[k] = nums[j];
                locTmp[k++] = loc[j];
                j++;
            }else{
                numTmp[k] = nums[i];
                locTmp[k++] = loc[i];
                cnt[loc[i]] += j-c-1;
                i++;
            }
        }
        int len = r-c; //细节，将这里提前计算出来，时间快了7ms
        while(i < c+1){
            numTmp[k] = nums[i];
            locTmp[k++] = loc[i];
            cnt[loc[i]] += len;
            i++;
        }
        while(j <= r){
            numTmp[k] = nums[j];
            locTmp[k++] = loc[j];
            j++;
        }
    }*/

    //再再次优化，加入了剪枝策略，44ms
    int[] numTmp,loc,locTmp;
    int[] cnt;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        numTmp = new int[n];
        loc = new int[n];
        locTmp = new int[n];
        cnt = new int[n];
        List<Integer> res = new ArrayList<>(n);
        for(int i = 0;i < n;i++){
            loc[i] = i;
        }
        mergeSort(nums,0,n-1);
        for(int i = 0;i < n;i++){
            res.add(cnt[i]);
        }
        return res;
    }
    public void mergeSort(int[] nums,int l,int r){
        if(l == r)
            return;
        int c = l+((r-l)>>1);
        mergeSort(nums,l,c);
        mergeSort(nums,c+1,r);

        //重点在这里
        if(nums[c] < nums[c+1])
            return;
        merge(nums,l,c,r);

        for(int x = l;x <= r;x++){
            nums[x] = numTmp[x];
            loc[x] = locTmp[x];
        }
    }
    public void merge(int[] nums,int l,int c,int r){
        int i = l,j = c+1,k = l;
        while(i < c+1 && j <= r){
            if(nums[i] > nums[j]){
                numTmp[k] = nums[j];
                locTmp[k++] = loc[j];
                j++;
            }else{
                numTmp[k] = nums[i];
                locTmp[k++] = loc[i];
                cnt[loc[i]] += j-c-1;
                i++;
            }
        }
        int len = r-c;
        while(i < c+1){
            numTmp[k] = nums[i];
            locTmp[k++] = loc[i];
            cnt[loc[i]] += len;
            i++;
        }
        while(j <= r){
            numTmp[k] = nums[j];
            locTmp[k++] = loc[j];
            j++;
        }
    }
}
