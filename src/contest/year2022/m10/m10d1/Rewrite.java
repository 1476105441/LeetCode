package contest.year2022.m10.m10d1;

public class Rewrite {

    //重新写一遍树状数组，成功，45ms
    /*int[] temp,c;
    public long numberOfPairs(int[] nums1,int[] nums2,int diff){
        int n = nums1.length;
        int[] temp2 = new int[n];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            temp2[i] = nums1[i] - nums2[i];
            set.add(temp2[i]);
        }
        temp = new int[set.size()];
        c = new int[temp.length+1];
        int l = 0;
        for (Integer i : set) temp[l++] = i;
        Arrays.sort(temp);

        long res = 0;
        for (int i = 0; i < n; i++) {
            res += query(find(temp2[i]+diff));
            update(temp2[i]);
        }
        return res;
    }

    public int find(int val){
        int l = 0,r = temp.length-1,res = -1;
        while (l <= r) {
            int c = l + ((r-l)>>1);
            if (temp[c] > val) {
                r = c-1;
            }else{
                l = c+1;
                res = c;
            }
        }
        return res+1;
    }
    public void update(int loc){
        while (loc < c.length) {
            c[loc]++;
            loc += (loc & -loc);
        }
    }
    public int query(int loc){
        int res = 0;
        while (loc > 0) {
            res += c[loc];
            loc -= loc&-loc;
        }
        return res;
    }*/

    //归并排序，究竟要怎么排序？
    //想复杂了，31ms
    /*int[] temp;
    int diff;
    long res;
    public long numberOfPairs(int[] nums1,int[] nums2,int diff){
        int n = nums1.length;
        this.diff = diff;
        temp = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        merge(nums1,0,n-1);

        return res;
    }
    public void merge(int[] nums,int l,int r){
        if(l >= r) return;

        int c = l + ((r-l)>>1);
        merge(nums,l,c);
        merge(nums,c+1,r);

        for (int i = l; i < r + 1; i++) {
            temp[i] = nums[i];
        }
        int j = l,i;
        for (i = c+1; i < r + 1; i++) {
            //找到第一个不符合条件的情况，那么它前面的元素都比它小，所以肯定都符合情况
            while(j < c+1 && temp[j] - diff <= temp[i]) j++;
            res += j - l;
        }

        //接下来是合并过程
        i = l;
        j = c+1;
        int k = l;
        while (i <= c && j <= r) {
            if (temp[i] > temp[j]) {
                nums[k] = temp[j];
                j++;
            }else{
                nums[k] = temp[i];
                i++;
            }
            k++;
        }
        while (i <= c) {
            nums[k] = temp[i];
            i++;
            k++;
        }
        while (j <= r) {
            nums[k] = temp[j];
            j++;
            k++;
        }
    }*/


    //再次重新写一遍归并排序
    int[] temp;
    long res;
    int diff,n;
    public long numberOfPairs(int[] nums1,int[] nums2,int diff){
         n = nums1.length;
         this.diff = diff;
         temp = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        merge(nums1,0,n-1);

        return res;
    }
    public void merge(int[] nums,int l,int r){
        if(l >= r)
            return;

        int c = l + ((r-l)>>1);
        merge(nums,l,c);
        merge(nums,c+1,r);

        for (int i = l; i <= r; i++) {
            temp[i] = nums[i];
        }
        int i = l,j = c+1;
        for(;i <= c;i++){
            while(j <= r && temp[i] > temp[j]+diff)
                j++;
            if(j > r)
                break;
            res += r-j+1;
        }

        i = l;
        j = c+1;
        int k = l;
        while (i <= c && j <= r) {
            if (temp[i] > temp[j]) {
                nums[k] = temp[j++];
            }else{
                nums[k] = temp[i++];
            }
            k++;
        }
        while (i <= c) {
            nums[k++] = temp[i++];
        }
        while (j <= r) {
            nums[k++] = temp[j++];
        }
    }
}
