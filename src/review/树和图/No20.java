package review.树和图;

import java.util.*;

public class No20 {
    /**
     *      计算右侧小于当前元素的个数
     */

    //想使用桶排序，失败
    /*public List<Integer> countSmaller(int[] nums){
        int n = nums.length,count = 0;
        int[] bucket = new int[100001];
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            bucket[nums[i]]++;
        }
        for (int i = 1; i <= 100000; i++) {
            if (bucket[i] != 0) {
                map.put(i,count);
                count += bucket[i];
            }
        }

        for (int i = 0; i < n; i++) {
            res.add(map.get(nums[i]));
        }
        return res;
    }*/

    //转换思维，求右侧小于当前元素的个
    //数，就是求当前元素构成了多少个逆
    //序对，使用求逆序对的方法（归并排
    //序），统计当前元素构成了多少逆序
    //对，实质上就是求数组中逆序对的特
    //殊版本，要分别存储每个元素构成的
    //逆序对个数，使用额外的数组存放
    //***成功，60ms***
    /*int[] tmpNums,tmpMap,map,count,tmpC;
    public List<Integer> countSmaller(int[] nums){
        int n = nums.length;
        List<Integer> res = new ArrayList<>(n);
        tmpNums = new int[n];
        tmpMap = new int[n];
        map = new int[n];
        count = new int[n];
        tmpC = new int[n];
        for (int i = 0; i < n; i++) {
            map[i] =  i;
            res.add(0);
        }
        mergeSort(nums,0,n-1);

        for (int i = 0; i < n; i++) {
            res.set(map[i],count[i]);
        }
        return res;
    }

    public void mergeSort(int[] nums,int begin,int end){
        if (begin >= end) {
            return;
        }
        int center = begin+(end-begin)/2;
        mergeSort(nums,begin,center);
        mergeSort(nums,center+1,end);
        int i = begin,j = center+1,k = begin;
        while (i <= center && j <= end) {
            if (nums[i] > nums[j]) {
                tmpNums[k] = nums[j];
                tmpC[k] = count[j];
                tmpMap[k] = map[j];
                k++;
                j++;
            }else{
                tmpNums[k] = nums[i];
                //逆序对个数加上j之前的元素个数
                tmpC[k] = count[i]+j-center-1;
                tmpMap[k] = map[i];
                k++;
                i++;
            }
        }
        while (i <= center) {
            tmpNums[k] = nums[i];
            //逆序对个数加上j之前的元素个数
            tmpC[k] = count[i]+end-center;
            tmpMap[k] = map[i];
            k++;
            i++;
        }
        while (j <= end) {
            tmpNums[k] = nums[j];
            tmpC[k] = count[j];
            tmpMap[k] = map[j];
            k++;
            j++;
        }

        for (int l = begin; l <= end; l++) {
            nums[l] = tmpNums[l];
            map[l] = tmpMap[l];
            count[l] = tmpC[l];
        }
    }*/

    //上一解法可以省去tmpC到count的转
    //换，直接使用映射地址存储即可
    /*int[] tmpNums,tmpMap,map,count;
    public List<Integer> countSmaller(int[] nums){
        int n = nums.length;
        List<Integer> res = new ArrayList<>(n);
        tmpNums = new int[n];
        tmpMap = new int[n];
        map = new int[n];
        count = new int[n];
        for (int i = 0; i < n; i++) {
            map[i] =  i;
            res.add(0);
        }
        mergeSort(nums,0,n-1);

        for (int i = 0; i < n; i++) {
            res.set(map[i],count[map[i]]);
        }
        return res;
    }

    public void mergeSort(int[] nums,int begin,int end){
        if (begin >= end) {
            return;
        }
        int center = begin+(end-begin)/2;
        mergeSort(nums,begin,center);
        mergeSort(nums,center+1,end);
        int i = begin,j = center+1,k = begin;
        while (i <= center && j <= end) {
            if (nums[i] > nums[j]) {
                tmpNums[k] = nums[j];
                tmpMap[k] = map[j];
                k++;
                j++;
            }else{
                tmpNums[k] = nums[i];
                //逆序对个数加上j之前的元素个数
                count[map[i]] += j-center-1;
                tmpMap[k] = map[i];
                k++;
                i++;
            }
        }
        while (i <= center) {
            tmpNums[k] = nums[i];
            //逆序对个数加上j之前的元素个数
            count[map[i]] +=end-center;
            tmpMap[k] = map[i];
            k++;
            i++;
        }
        while (j <= end) {
            tmpNums[k] = nums[j];
            tmpMap[k] = map[j];
            k++;
            j++;
        }

        for (int l = begin; l <= end; l++) {
            nums[l] = tmpNums[l];
            map[l] = tmpMap[l];
        }
    }*/


    //离散化树状数组，使用哈希表做离散化
    //效率极低
    /*public List<Integer> countSmaller(int[] nums){
        int len = nums.length;
        c = new int[len+1];
        map = new HashMap<>();
        init(nums);
        List<Integer> res = new ArrayList<>();

        for (int i = len-1; i >= 0; i--) {
            int loc = map.get(nums[i]);
            res.add(0,query(loc-1));
            update(loc);
        }

        return res;
    }

    int[] c;
    Map<Integer,Integer> map;
    public int lowBit(int m){
        return m & (-m);
    }
    public int query(int loc){
        int res = 0;
        while (loc > 0) {
            res += c[loc];
            loc -= lowBit(loc);
        }
        return res;
    }
    public void update(int loc){
        int len = c.length;
        while (loc < len) {
            c[loc] += 1;
            loc += lowBit(loc);
        }
    }
    public void init(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int size = set.size(),j = 0;
        int[] a = new int[size];
        for (Integer i : set){
            a[j++] = i;
        }
        Arrays.sort(a);
        for (int i = 0; i < size; i++) {
            map.put(a[i],i+1);
        }
    }*/

    int[] c;
    int[] a;
    public List<Integer> countSmaller(int[] nums){
        c = new int[nums.length+1];
        init(nums);
        List<Integer> res = new ArrayList<>(nums.length);
        for (int i = nums.length-1;i >= 0;i--){
            int loc = getLoc(nums[i]);
            res.add(query(loc-1));
            update(loc);
        }
        //此处为关键因素，将数组反转远比向第一个位置插入元素要快
        Collections.reverse(res);
        return res;
    }
    public int lowBit(int m){
        return m & (-m);
    }
    public int query(int loc){
        int res = 0;
        while (loc > 0) {
            res += c[loc];
            loc -= lowBit(loc);
        }
        return res;
    }
    public void update(int loc){
        while (loc < c.length) {
            c[loc] += 1;
            loc += lowBit(loc);
        }
    }
    public void init(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int size = set.size(),j = 0;
        a = new int[size];
        for (Integer val : set) {
            a[j++] = val;
        }
        Arrays.sort(a);
    }
    public int getLoc(int val){
        return search(0,a.length-1,val) + 1;
    }
    public int search(int left,int right,int val){
        if (left >= right) {
            return left;
        }
        int center = left + (right-left)/2;
        if (val == a[center]) {
            return center;
        } else if (val > a[center]) {
            return search(center+1,right,val);
        } else{
            return search(left,center,val);
        }
    }
}
